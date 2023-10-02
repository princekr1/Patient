package com.example.patient.service;

import com.example.patient.config.Constants;
import com.example.patient.entity.Doctor;
import com.example.patient.entity.Patient;
import com.example.patient.exception.EntityNotFound;
import com.example.patient.model.PatientDTO;
import com.example.patient.repository.ContactRepository;
import com.example.patient.repository.DoctorRepository;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    public static final Logger logger= LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    // Refresh cache
    public String create(PatientDTO patientDTO){
        logger.debug("create() Started...");
        String message= edit(patientDTO,null);
        logger.debug("create() Started...");
        return message;
    }

    @CacheEvict(value = "patients", allEntries = true)
    public String edit(PatientDTO patientDTO, Long id){
        logger.debug("edit() Started...");
        String message="";
        Patient patient=null;

        logger.info("patientDTO : "+patientDTO);
        if(id==null){
            patient=new Patient();
        }else{
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if(optionalPatient.isPresent()){
                patient=optionalPatient.get();
            }else{
                message="Patient does not exist";
                logger.error(message+" with id : "+id);
                throw new EntityNotFound(message);
            }
        }
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        if(patientDTO.getTreatmentStatus()==null ){
            if(patient.getTreatmentStatus()==null){
                patient.setTreatmentStatus(Constants.NOT_STARTED);
            }
        }else{
            patient.setTreatmentStatus(patientDTO.getTreatmentStatus());
        }
        Patient updatedPatient = patientRepository.save(patient);
        logger.info("updatedPatient : "+patientDTO);
        if(id==null){
            message="Patient Created with name : "+updatedPatient.getName();
        }else{
            message="Patient record updated Successfully";
        }
        logger.info(message);
        logger.debug("edit() Finished.");
        return message;
    }


    @Caching(evict = {@CacheEvict(value = "patients", allEntries = true),
            @CacheEvict(value = "patient", key = "#id")
    })
    public String delete(Long id){
        String message="";
        logger.debug("delete() Started...");
        logger.info("id : "+id);
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient=null;
        if(optionalPatient.isPresent()){
            patient=optionalPatient.get();
        }else{
            message="patient does not exist with id : "+id;
            logger.info(message);
            return message;
        }
        patientRepository.deleteById(id);
        message="patient Deleted.";
        logger.debug("delete() Finished.");
        return message;
    }

    @Cacheable("patients")
    public List<Patient> list(){
        logger.debug("list() Started...");
        List<Patient> patients = patientRepository.findAll();
        if(patients==null){
            patients=new ArrayList<>();
        }
        logger.info("Number of patients : "+patients.size());
        logger.debug("list() Finished.");
        return patients;
    }

    @CachePut(value = "patient", key = "#id")
    public Patient get(Long id){
        logger.debug("get() Started...");
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient=null;
        if(optionalPatient.isPresent()){
            patient=optionalPatient.get();
        }else{
           String message="Patient does not exist";
            logger.error(message+" with id : "+id);
            throw new EntityNotFound(message);
        }
        logger.debug("get() Finished.");
        return patient;
    }


    @CachePut(value = "patient",key = "#patientId")
    public String assignDoctor(Long patientId, Long doctorId ){
        logger.debug("assignDoctor() Started...");
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
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        Doctor doctor=null;
        if(optionalDoctor.isPresent()){
            doctor=optionalDoctor.get();
        }else{
            message="Doctor does not exist";
            logger.error(message+" with id : "+doctorId);
            throw new EntityNotFound(message);
        }
        List<Doctor> doctors=new ArrayList<>();
        doctors.add(doctor);
        patient.setDoctors(doctors);
        Patient updatedPatient = patientRepository.save(patient);
        message="assign doctor to the patient";
        logger.info(message+", updatedPatient : "+updatedPatient);
        logger.debug("assignDoctor() Finished.");
        return message;
    }

}
