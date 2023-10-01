package com.example.patient.repository;

import com.example.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


}
