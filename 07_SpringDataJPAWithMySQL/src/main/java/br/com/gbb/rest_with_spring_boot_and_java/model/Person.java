package br.com.gbb.rest_with_spring_boot_and_java.model;

import java.io.Serializable;
import java.util.Objects;

public class Person  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String Address;

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() { return Address; }

    public void setAddress(String address) { Address = address; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getGender(), person.getGender()) && Objects.equals(getAddress(), person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getGender(), getAddress());
    }
}
