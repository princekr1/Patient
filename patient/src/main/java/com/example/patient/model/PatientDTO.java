package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {

    @JsonProperty
    @NotNull
    private String name;
    @JsonProperty
    @NotNull
    private String gender;

    @JsonProperty
    @NotNull
    private String address;

    @JsonProperty
    private String treatmentStatus;

    @JsonProperty
    @NotNull
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
