package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.controllres.PersonalController;
import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.gbb.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.gbb.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import br.com.gbb.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;


@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll(){
        logger.info("Find All People!");
        List<PersonDTO> dtos = parseListObjects(repository.findAll(), PersonDTO.class);
        dtos.forEach(this::addHateoasLinks);
        return dtos;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding person by id " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        PersonDTO dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO create(PersonDTO  person){
        logger.info("Creating one Person ");
        if (person == null) throw new RequiredObjectIsNullException();

        Person entity = parseObject(person, Person.class);
        repository.save(entity);
        logger.info("Person created: " + person.getId());
        PersonDTO dto =  parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO  person){
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one Person: "+ person.getId());

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        repository.save(entity);

        logger.info("Person updated: " + entity.getId());
        PersonDTO dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting person by id " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        repository.delete(entity);
        logger.info("Person deleted: " + id);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonalController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonalController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonalController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonalController.class).update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(PersonalController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
