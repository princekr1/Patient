package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {

    @JsonProperty
    @NotNull
    @Size(min=1, max=20)
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

    @JsonProperty
    @Size(min=3, max=10)
    private String treatmentStatus;

    @JsonProperty
    @NotNull
    @Max(110)
    private int age;

    @JsonCreator
    public PatientDTO(@JsonProperty("name") String name,@JsonProperty("gender") String gender, @JsonProperty("address") String address, @JsonProperty("treatmentStatus") String treatmentStatus, @JsonProperty("age") int age) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.treatmentStatus = treatmentStatus;
        this.age = age;
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

    public String getTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(String treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", treatmentStatus='" + treatmentStatus + '\'' +
                ", age=" + age +
                '}';
    }
}
