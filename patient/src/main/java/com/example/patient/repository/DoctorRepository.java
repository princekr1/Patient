package com.example.patient.repository;

import com.example.patient.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
