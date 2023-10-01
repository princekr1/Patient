package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {
    @JsonProperty
    @NotNull
    @Size(min=1, max=10)
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String name;

    @JsonProperty
    @NotNull
    @Size(min=1, max=6)
    private String gender;

    @JsonProperty
    @NotNull
    @Size(min=1, max=234)
    private String address;

    @JsonCreator
    public DoctorDTO(@JsonProperty("name") String name,@JsonProperty("gender") String gender, @JsonProperty("address") String address) {
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
