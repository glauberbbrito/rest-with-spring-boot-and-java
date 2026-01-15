package br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String author;
    private BigDecimal price;
    private String title;
    private LocalDate launchDate;
    private LocalDateTime registrationDate;

    public BookDTO() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public BigDecimal getPrice() { return price;}

    public void setPrice(BigDecimal price) { this.price = price;}

    public String getTitle() {return title; }

    public void setTitle(String title) { this.title = title;}

    public LocalDateTime getRegistrationDate() { return registrationDate; }

    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    public LocalDate getLaunchDate() {return launchDate; }

    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDTO booksDTO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), booksDTO.getId()) && Objects.equals(getAuthor(), booksDTO.getAuthor()) && Objects.equals(getPrice(), booksDTO.getPrice()) && Objects.equals(getTitle(), booksDTO.getTitle()) && Objects.equals(getLaunchDate(), booksDTO.getLaunchDate()) && Objects.equals(getRegistrationDate(), booksDTO.getRegistrationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getAuthor(), getPrice(), getTitle(), getLaunchDate(), getRegistrationDate());
    }
}
