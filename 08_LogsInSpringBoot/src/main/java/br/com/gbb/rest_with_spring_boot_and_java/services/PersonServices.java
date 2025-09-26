package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import br.com.gbb.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Find All People!");
        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding person by id " + id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
    }

    public Person create(Person  person){
        logger.info("Creating one Person ");
        repository.save(person);
        logger.info("Person created: " + person.getId());
        return person;
    }

    public Person update(Person  person){
        logger.info("Updating one Person: "+ person.getId());
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        repository.save(entity);

        logger.info("Person updated: " + entity.getId());
        return entity;
    }

    public void delete(Long id) {
        logger.info("deleting person by id " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        repository.delete(entity);
        logger.info("Person deleted: " + id);
    }

}
