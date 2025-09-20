package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Find All People!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0 ; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(Long id){
        logger.info("Finding person by id " + id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gláuber");
        person.setLastName("Barreto Brito");
        person.setAddress("Patos - Paraíba - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person  person){
        logger.info("Creating one Person ");
        logger.info("Person created: " + person.getId());
        return person;
    }

    public Person update(Person  person){
        logger.info("Updating one Person: "+ person.getId());
        logger.info("Person updated: " + person.getId());
        return person;
    }

    public void delete(Long id) {
        logger.info("deleting person by id " + id);
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Some Address in Brasil");
        person.setGender("Male");

        return person;
    }

}
