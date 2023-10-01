package com.example.patient.repository;


import com.example.patient.entity.Contact;
import com.example.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    public void deleteAllByPatientId(Long id);

    public Contact findByPhoneNumberAndPatientId(Long phoneNumber,Patient patient);

}
