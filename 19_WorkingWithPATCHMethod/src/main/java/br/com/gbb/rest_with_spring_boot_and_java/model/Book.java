package br.com.gbb.rest_with_spring_boot_and_java.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 80)
    private String author;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate launchDate;

    @Column(name = "REGISTRATIONDATE", nullable = false)
    private LocalDateTime registrationDate;

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getLaunchDate() { return launchDate; }

    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book books)) return false;
        return Objects.equals(getId(), books.getId()) && Objects.equals(getAuthor(), books.getAuthor()) && Objects.equals(getPrice(), books.getPrice()) && Objects.equals(getTitle(), books.getTitle()) && Objects.equals(getLaunchDate(), books.getLaunchDate()) && Objects.equals(getRegistrationDate(), books.getRegistrationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getPrice(), getTitle(), getLaunchDate(), getRegistrationDate());
    }
}
