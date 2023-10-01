package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    @JsonProperty
    @NotNull
    private String name;
    @JsonProperty
    private String relation;
    @JsonProperty
    @NotNull
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
