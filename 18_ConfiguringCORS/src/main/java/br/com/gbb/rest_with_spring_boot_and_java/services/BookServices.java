package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.controllres.BookController;
import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.gbb.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.gbb.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.gbb.rest_with_spring_boot_and_java.model.Book;
import br.com.gbb.rest_with_spring_boot_and_java.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.gbb.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll(){
        logger.info("Find All Books!");
        List<BookDTO> dtos = parseListObjects(repository.findAll(), BookDTO.class);
        dtos.forEach(this::addHateoasLinks);
        return dtos;
    }

    public BookDTO findById(Long id){
        logger.info("Finding book by id " + id);
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        BookDTO dto = parseObject(entity, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public BookDTO create(BookDTO book){
        logger.info("Creating one Book ");
        if (book == null) throw new RequiredObjectIsNullException();

        Book entity = parseObject(book, Book.class);
        entity.setRegistrationDate(LocalDateTime.now());
        repository.save(entity);
        logger.info("Book created: " + book.getId());
        BookDTO dto =  parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book){
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one Book: "+ book.getId());

        Book entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+book.getId()));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        repository.save(entity);

        logger.info("Book updated: " + entity.getId());
        BookDTO dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting book by id " + id);
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID: "+id));
        repository.delete(entity);
        logger.info("Book deleted: " + id);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
