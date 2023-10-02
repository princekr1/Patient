package com.example.patient.service;

import com.example.patient.config.Constants;
import com.example.patient.entity.Doctor;
import com.example.patient.entity.Patient;
import com.example.patient.exception.EntityNotFound;
import com.example.patient.model.DoctorDTO;
import com.example.patient.model.PatientDTO;
import com.example.patient.repository.ContactRepository;
import com.example.patient.repository.DoctorRepository;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    public static final Logger logger= LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    public String create(DoctorDTO doctorDTO){
        logger.debug("create() Started...");
        String message= edit(doctorDTO,null);
        logger.debug("create() Started...");
        return message;
    }

    public String edit(DoctorDTO doctorDTO, Long id){
        logger.debug("edit() Started...");
        String message="";
        Doctor doctor=null;

        logger.info("DoctorDTO : "+doctorDTO);
        if(id==null){
            doctor=new Doctor();
        }else{
            Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
            if(optionalDoctor.isPresent()){
                doctor=optionalDoctor.get();
            }else{
                message="Doctor does not exist";
                logger.error(message+" with id : "+id);
                throw new EntityNotFound(message);
            }
        }
        doctor.setName(doctorDTO.getName());
        doctor.setAddress(doctorDTO.getAddress());
        doctor.setGender(doctorDTO.getGender());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        logger.info("updatedDoctor : "+updatedDoctor);
        if(id==null){
            message="Doctor Created with name : "+updatedDoctor.getName();
        }else{
            message="Doctor record updated Successfully";
        }
        logger.info(message);
        logger.debug("edit() Finished.");
        return message;
    }

    public String delete(Long id){
        String message="";
        logger.debug("delete() Started...");
        logger.info("id : "+id);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor=null;
        if(optionalDoctor.isPresent()){
            doctor=optionalDoctor.get();
        }else{
            message="Doctor does not exist with id : "+id;
            logger.info(message);
            return message;
        }
        doctorRepository.deleteById(id);
        logger.debug("delete() Finished.");
        return message;
    }

    public List<Doctor> list(){
        logger.debug("list() Started...");
        List<Doctor> doctors = doctorRepository.findAll();
        if(doctors==null){
            doctors=new ArrayList<>();
        }
        logger.info("Number of doctors : "+doctors.size());
        logger.debug("list() Finished.");
        return doctors;
    }

    public Doctor get(Long id){
        logger.debug("get() Started...");
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor=null;
        if(optionalDoctor.isPresent()){
            doctor=optionalDoctor.get();
        }else{
            String message="Doctor does not exist";
            logger.error(message+" with id : "+id);
            throw new EntityNotFound(message);
        }
        logger.debug("get() Finished.");
        return doctor;
    }
}
