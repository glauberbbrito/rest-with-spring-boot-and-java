package br.com.gbb.rest_with_spring_boot_and_java.unitetests.mapper.mocks;

import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.gbb.rest_with_spring_boot_and_java.model.Book;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Some Author Teste " + number);
        book.setTitle(" Some Title Teste " + number);
        book.setPrice(new BigDecimal(number/2).round(MathContext.DECIMAL32));
        book.setLaunchDate(LocalDate.of(2003,10,25));
        book.setId(number.longValue());
        book.setRegistrationDate(LocalDateTime.now());
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setAuthor("Author Teste " + number);
        book.setTitle("First Title Teste " + number);
        book.setPrice(new BigDecimal(number/2).round(MathContext.DECIMAL32));
        book.setLaunchDate(LocalDate.of(2003,10,25));
        book.setId(number.longValue());
        book.setRegistrationDate(LocalDateTime.now());
        return book;
    }

}