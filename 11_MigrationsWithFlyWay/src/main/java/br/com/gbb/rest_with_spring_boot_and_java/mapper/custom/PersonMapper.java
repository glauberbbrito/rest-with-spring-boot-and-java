package br.com.gbb.rest_with_spring_boot_and_java.mapper.custom;

import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v2.PersonDTOV2;
import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonMapper {

    public PersonDTOV2 mapToDTO(Person entity) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setBirthDay(LocalDate.now());
        return dto;
    }

    public Person mapToEntity(PersonDTOV2 dto) {
        Person entity = new Person();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return entity;
    }

}
