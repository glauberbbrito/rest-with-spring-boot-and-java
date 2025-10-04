package br.com.gbb.rest_with_spring_boot_and_java.data.dto.v1;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.gbb.rest_with_spring_boot_and_java.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

//@JsonPropertyOrder({"id", "first_name", "last_name", "address"})
@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // @JsonProperty("first_name")
    private String firstName;

    // @JsonProperty("last_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    private String address;

    // @JsonIgnore
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    private String sensitiveData;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO dto)) return false;
        return Objects.equals(getId(), dto.getId()) && Objects.equals(getFirstName(), dto.getFirstName()) && Objects.equals(getLastName(), dto.getLastName()) && Objects.equals(getPhoneNumber(), dto.getPhoneNumber()) && Objects.equals(getBirthDay(), dto.getBirthDay()) && Objects.equals(getAddress(), dto.getAddress()) && Objects.equals(getGender(), dto.getGender()) && Objects.equals(getSensitiveData(), dto.getSensitiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber(), getBirthDay(), getAddress(), getGender(), getSensitiveData());
    }
}
