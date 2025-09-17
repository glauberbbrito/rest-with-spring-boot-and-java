package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

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

}
