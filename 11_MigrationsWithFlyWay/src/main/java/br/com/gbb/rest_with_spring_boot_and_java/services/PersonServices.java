package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v2.PersonDTOV2;
import br.com.gbb.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.gbb.rest_with_spring_boot_and_java.mapper.custom.PersonMapper;
import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import br.com.gbb.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;

import java.util.List;


@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper personMapper = new PersonMapper();

    public List<PersonDTO> findAll(){
        logger.info("Find All People!");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding person by id " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO  person){
        logger.info("Creating one Person ");
        Person entity = parseObject(person, Person.class);
        repository.save(entity);
        logger.info("Person created: " + person.getId());
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 dto){
        logger.info("Creating one Person ");
        Person entity = personMapper.mapToEntity(dto);
        repository.save(entity);
        logger.info("Person created: " + dto.getId());
        return personMapper.mapToDTO(entity);
    }

    public PersonDTO update(PersonDTO  person){
        logger.info("Updating one Person: "+ person.getId());
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        repository.save(entity);

        logger.info("Person updated: " + entity.getId());
        return parseObject(entity, PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("deleting person by id " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        repository.delete(entity);
        logger.info("Person deleted: " + id);
    }

}
