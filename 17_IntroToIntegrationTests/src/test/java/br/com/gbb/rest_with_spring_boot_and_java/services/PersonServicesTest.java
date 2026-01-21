package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.gbb.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import br.com.gbb.rest_with_spring_boot_and_java.repository.PersonRepository;
import br.com.gbb.rest_with_spring_boot_and_java.unitetests.mapper.mocks.MockPerson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        Person person = input.mockEntity(1);
        when(repository.findById(person.getId())).thenReturn(Optional.of(person));

        var result = service.findById(person.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getGender(), result.getGender());

    }

    @Test
    void create() {
        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(person.getId());

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getGender(), result.getGender());

    }

    @Test
    void testCreateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->service.create(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMenssage = exception.getMessage();
        assertTrue(actualMenssage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(person.getId());

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(person.getId())).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+person.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getGender(), result.getGender());
    }

    @Test
    void testUpdateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->service.update(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMenssage = exception.getMessage();
        assertTrue(actualMenssage.contains(expectedMessage));
    }

    @Test
    void delete() {

        Person person = input.mockEntity(1);
        person.setId(person.getId());
        when(repository.findById(person.getId())).thenReturn(Optional.of(person));

        service.delete(person.getId());

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository); // verifica se não teve mais interações com o repository.
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(list.size(), people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getFirstName());
        assertNotNull(personOne.getLastName());
        assertNotNull(personOne.getAddress());
        assertNotNull(personOne.getGender());
        assertNotNull(personOne.getLinks());

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+list.get(1).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+list.get(1).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(1).getId(), personOne.getId());
        assertEquals(list.get(1).getFirstName(), personOne.getFirstName());
        assertEquals(list.get(1).getLastName(), personOne.getLastName());
        assertEquals(list.get(1).getAddress(), personOne.getAddress());
        assertEquals(list.get(1).getGender(), personOne.getGender());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getFirstName());
        assertNotNull(personFour.getLastName());
        assertNotNull(personFour.getAddress());
        assertNotNull(personFour.getGender());
        assertNotNull(personFour.getLinks());

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+list.get(4).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+list.get(4).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(4).getId(), personFour.getId());
        assertEquals(list.get(4).getFirstName(), personFour.getFirstName());
        assertEquals(list.get(4).getLastName(), personFour.getLastName());
        assertEquals(list.get(4).getAddress(), personFour.getAddress());
        assertEquals(list.get(4).getGender(), personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getFirstName());
        assertNotNull(personSeven.getLastName());
        assertNotNull(personSeven.getAddress());
        assertNotNull(personSeven.getGender());
        assertNotNull(personSeven.getLinks());

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/person/v1/"+list.get(7).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(personSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/person/v1/"+list.get(7).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(7).getId(), personSeven.getId());
        assertEquals(list.get(7).getFirstName(), personSeven.getFirstName());
        assertEquals(list.get(7).getLastName(), personSeven.getLastName());
        assertEquals(list.get(7).getAddress(), personSeven.getAddress());
        assertEquals(list.get(7).getGender(), personSeven.getGender());

    }
}