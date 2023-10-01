package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    @JsonProperty
    @NotNull
    @Size(min=1, max=10)
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String name;
    @JsonProperty
    @Size(min=1, max=10)
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String relation;
    @JsonProperty
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    @Max(10)
    @Min(10)
    private Long phoneNumber;

    @JsonCreator
    public ContactDTO(@JsonProperty("name") String name,@JsonProperty("relation") String relation,  @JsonProperty("phoneNumber") Long phoneNumber) {
        this.name = name;
        this.relation = relation;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
