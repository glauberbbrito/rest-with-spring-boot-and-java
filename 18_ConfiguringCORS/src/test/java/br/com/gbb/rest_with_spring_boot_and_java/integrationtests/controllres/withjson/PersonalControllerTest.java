package br.com.gbb.rest_with_spring_boot_and_java.integrationtests.controllres.withjson;

import br.com.gbb.rest_with_spring_boot_and_java.config.TestConfigs;
import br.com.gbb.rest_with_spring_boot_and_java.integrationtests.dto.PersonDTO;
import br.com.gbb.rest_with_spring_boot_and_java.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonalControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonDTO personDTO;
    @LocalServerPort
    private int port;

    //A inicialização das variaveis devem ser realizadas neste método, pois, ele é chamado antes da execução dos testes.
    //Se inicializar na declaração da variável, o teste não vai funcionar porque a variável estará null.
    //@BeforeEach executa para cada teste
    @BeforeAll //vai executar uma vez para todos os testes.
    static void setUp() {
        objectMapper = new ObjectMapper();
        // ignora informações no JSON que nao devem ser considerados no retorno, assim as informções de _links no JOSON
        // de retorno não serão considerados.
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);1

        personDTO = new PersonDTO();
    }

    @Test
    @Order(1)
    void create() throws JsonProcessingException {
        mockPersonDTO();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_TRUE)
                    .setBasePath("/api/person/v1")
                    .setPort(port)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))   //LOGAR O QUE ESTÁ SENDO ENVIADO NA REQ
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))  //LOGAR O QUE FOR RECEBIDO NA RES
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(personDTO)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        PersonDTO createdPerson =  objectMapper.readValue(content, PersonDTO.class);
        personDTO =  createdPerson;

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);
        assertTrue(createdPerson.getGender().equals("Male"));

        assertEquals("Richard", createdPerson.getFirstName());
        assertEquals("Stallman", createdPerson.getLastName());
        assertEquals("New York City - New York - USA", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());


/*

        // 1. Usamos um AtomicReference para "tirar" o valor de dentro do lambda
        java.util.concurrent.atomic.AtomicReference<String> contentRef = new java.util.concurrent.atomic.AtomicReference<>("");

        Awaitility.await()
                .atMost(10, java.util.concurrent.TimeUnit.SECONDS)
                .pollInterval(500, java.util.concurrent.TimeUnit.MILLISECONDS)
                .untilAsserted(() -> {
                    String result = given()
                            .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_TRUE)
                            .basePath("/api/person/v1")
                            .port(port)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(personDTO)
                            .when()
                            .post()
                            .then()
                            .statusCode(200)
                            .extract()
                            .asString();

                    contentRef.set(result); // Armazena o resultado na referência externa
                });


        String content = contentRef.get();

        PersonDTO createdPerson = objectMapper.readValue(content,PersonDTO.class);

        personDTO = createdPerson;

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);
        assertTrue(createdPerson.getGender().equals("Male"));

        assertEquals("Richard", createdPerson.getFirstName());
        assertEquals("Stallman", createdPerson.getLastName());
        assertEquals("New York City - New York - USA", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
*/
    }


    @Test
    @Order(2)
    void createWithWrongOrigin() throws JsonProcessingException {
//        mockPersonDTO();   não precisa mais pq o setUp foi configurado com @BeforeAll

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_FALSE)
                .setBasePath("/api/person/v1")
                .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))   //LOGAR O QUE ESTÁ SENDO ENVIADO NA REQ
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))  //LOGAR O QUE FOR RECEBIDO NA RES
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(personDTO)
                .when()
                    .post()
                .then()
                    .statusCode(403)
                .extract()
                    .body()
                        .asString();

        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    void findById() throws JsonProcessingException {
//        mockPersonDTO();   não precisa mais pq o setUp foi configurado com @BeforeAll

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_TRUE)
                .setBasePath("/api/person/v1")
                .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))   //LOGAR O QUE ESTÁ SENDO ENVIADO NA REQ
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))  //LOGAR O QUE FOR RECEBIDO NA RES
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", personDTO.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        personDTO =  createdPerson;
 //       assertEquals("Invalid CORS request", content);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);
        assertTrue(createdPerson.getGender().equals("Male"));

        assertEquals("Richard", createdPerson.getFirstName());
        assertEquals("Stallman", createdPerson.getLastName());
        assertEquals("New York City - New York - USA", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(4)
    void findByIdWithWrongOrigin() throws JsonProcessingException {
//        mockPersonDTO();   não precisa mais pq o setUp foi configurado com @BeforeAll

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST_FALSE)
                .setBasePath("/api/person/v1")
                .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))   //LOGAR O QUE ESTÁ SENDO ENVIADO NA REQ
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))  //LOGAR O QUE FOR RECEBIDO NA RES
                .build();

        var content = given(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", personDTO.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();
        assertEquals("Invalid CORS request", content);

    }

    private void mockPersonDTO() {
        personDTO.setId(1L);
        personDTO.setFirstName("Richard");
        personDTO.setLastName("Stallman");
        personDTO.setAddress("New York City - New York - USA");
        personDTO.setGender("Male");
    }

}