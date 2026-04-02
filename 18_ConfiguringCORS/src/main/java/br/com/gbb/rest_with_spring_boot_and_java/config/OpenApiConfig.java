package br.com.gbb.rest_with_spring_boot_and_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("REST API's RESTFul from 0 with Java, Spring Boot, Kubernetes, Docker")
                        .version("v1")
                        .description("REST API's RESTFul from 0 with Java, Spring Boot, Kubernetes, Docker. (DESCRIPTION)")
                        .termsOfService("https//pub.erudio.com.br/meus-cursos")
                        .license(new License()
                                .name("Apache Maven 4.0")
                                .url("https://maven.apache.org/")

                        )
                );
    }
}
