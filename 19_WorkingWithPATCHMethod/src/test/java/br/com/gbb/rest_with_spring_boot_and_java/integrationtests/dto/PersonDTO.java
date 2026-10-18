package br.com.gbb.rest_with_spring_boot_and_java.integrationtests.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private LocalDateTime registrationDate;
    private Boolean enabled;


    public PersonDTO() {}

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

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public LocalDateTime getRegistrationDate() {return registrationDate; }

    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate;}

    public Boolean getEnabled() { return enabled; }

    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender()) && Objects.equals(getRegistrationDate(), personDTO.getRegistrationDate()) && Objects.equals(getEnabled(), personDTO.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender(), getRegistrationDate(), getEnabled());
    }
}
