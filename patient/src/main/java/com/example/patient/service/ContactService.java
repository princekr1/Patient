package com.example.patient.service;

import com.example.patient.entity.Contact;
import com.example.patient.entity.Patient;
import com.example.patient.exception.EntityNotFound;
import com.example.patient.model.ContactDTO;
import com.example.patient.repository.ContactRepository;
import com.example.patient.repository.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PatientRepository patientRepository;

    public static final Logger logger= LoggerFactory.getLogger(ContactService.class);

    public String addContactDetailsForPatient(Long patientId, ContactDTO contactDTO){
        logger.debug("addContactDetailsForPatient() Started...");
        String message="";
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Patient patient=null;
        if(optionalPatient.isPresent()){
            patient=optionalPatient.get();
        }else{
            message="Patient does not exist";
            logger.error(message+" with id : "+patientId);
            throw new EntityNotFound(message);
        }
        Contact patientContact = contactRepository.findByPhoneNumberAndPatientId(contactDTO.getPhoneNumber(), patient);
        if(patientContact==null){
            logger.info("no contact exists for patient.");
            patientContact=new Contact();
            patientContact.setPhoneNumber(contactDTO.getPhoneNumber());
            patientContact.setPatientId(patient);
        }
        patientContact.setName(contactDTO.getName());
        patientContact.setRelation(contactDTO.getRelation()==null?"self":contactDTO.getRelation());
        Contact contact = contactRepository.save(patientContact);
        message="Update contact successful";
        logger.info(message+", updated contact : "+contact);
        logger.debug("addContactDetailsForPatient() Started...");
        return message;
    }




}
