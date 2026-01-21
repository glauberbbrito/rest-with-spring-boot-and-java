package br.com.gbb.rest_with_spring_boot_and_java.services;

import br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.gbb.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.gbb.rest_with_spring_boot_and_java.model.Book;
import br.com.gbb.rest_with_spring_boot_and_java.repository.BookRepository;
import br.com.gbb.rest_with_spring_boot_and_java.unitetests.mapper.mocks.MockBook;
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
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        Book book = input.mockEntity(1);
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));

        var result = service.findById(book.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getTitle());
        assertNotNull(result.getPrice());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getTitle(), result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getLaunchDate(), result.getLaunchDate());

    }

    @Test
    void create() {
        Book book = input.mockEntity(1);

        Book persisted = book;
        persisted.setId(book.getId());

        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getTitle());
        assertNotNull(result.getPrice());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getTitle(), result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getLaunchDate(), result.getLaunchDate());

    }

    @Test
    void testCreateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->service.create(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMenssage = exception.getMessage();
        assertTrue(actualMenssage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);

        Book persisted = book;
        persisted.setId(book.getId());

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getTitle());
        assertNotNull(result.getPrice());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+book.getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(book.getId(), result.getId());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getTitle(), result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getLaunchDate(), result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->service.update(null));

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMenssage = exception.getMessage();
        assertTrue(actualMenssage.contains(expectedMessage));
    }

    @Test
    void delete() {

        Book book = input.mockEntity(1);
        book.setId(book.getId());
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));

        service.delete(book.getId());

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository); // verifica se não teve mais interações com o repository.
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(list.size(), people.size());

        var bookOne = people.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getAuthor());
        assertNotNull(bookOne.getTitle());
        assertNotNull(bookOne.getPrice());
        assertNotNull(bookOne.getLaunchDate());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+list.get(1).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+list.get(1).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(1).getId(), bookOne.getId());
        assertEquals(list.get(1).getAuthor(), bookOne.getAuthor());
        assertEquals(list.get(1).getTitle(), bookOne.getTitle());
        assertEquals(list.get(1).getPrice(), bookOne.getPrice());
        assertEquals(list.get(1).getLaunchDate(), bookOne.getLaunchDate());

        var bookFour = people.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getAuthor());
        assertNotNull(bookFour.getTitle());
        assertNotNull(bookFour.getPrice());
        assertNotNull(bookFour.getLaunchDate());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+list.get(4).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+list.get(4).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(4).getId(), bookFour.getId());
        assertEquals(list.get(4).getAuthor(), bookFour.getAuthor());
        assertEquals(list.get(4).getTitle(), bookFour.getTitle());
        assertEquals(list.get(4).getPrice(), bookFour.getPrice());
        assertEquals(list.get(4).getLaunchDate(), bookFour.getLaunchDate());

        var bookSeven = people.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getAuthor());
        assertNotNull(bookSeven.getTitle());
        assertNotNull(bookSeven.getPrice());
        assertNotNull(bookSeven.getLaunchDate());
        assertNotNull(bookSeven.getLinks());

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")
                        && link.getHref().equals("/api/book/v1/"+list.get(7).getId())
                        && link.getType().equals("GET")
                )
        );


        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("findAll")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("create")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("update")
                        && link.getHref().equals("/api/book/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("delete")
                        && link.getHref().equals("/api/book/v1/"+list.get(7).getId())
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals(list.get(7).getId(), bookSeven.getId());
        assertEquals(list.get(7).getAuthor(), bookSeven.getAuthor());
        assertEquals(list.get(7).getTitle(), bookSeven.getTitle());
        assertEquals(list.get(7).getPrice(), bookSeven.getPrice());
        assertEquals(list.get(7).getLaunchDate(), bookSeven.getLaunchDate());

    }
}