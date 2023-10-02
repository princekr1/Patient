package com.example.patient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name="metrics_seq", initialValue = 1,allocationSize = 1, sequenceName = "metrics_seq")
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="relation")
    private String relation="self";
    @Column(name="phone_number")
    private Long phoneNumber;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="patient_id")
    private Patient patientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
