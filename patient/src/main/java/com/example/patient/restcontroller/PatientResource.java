package com.example.patient.restcontroller;

import com.example.patient.entity.Patient;
import com.example.patient.model.PatientDTO;
import com.example.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/patient")
public class PatientResource {

    public static final Logger logger= LoggerFactory.getLogger(PatientResource.class);
    @Autowired
    PatientService patientService;

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody PatientDTO patientDTO){
        String message = patientService.create(patientDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> edit(
            @PathVariable("id") Long id,
            @RequestBody PatientDTO patientDTO){
        String message = patientService.edit(patientDTO,id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        String message = patientService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ResponseEntity<?> list(){
        List<Patient> patientList = patientService.list();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @RequestMapping(value = "/assignDoctor/{doctorId}/{patientId}",method = RequestMethod.PUT)
    public ResponseEntity<?> assignDoctors(@PathVariable("doctorId") Long doctorId,
                                           @PathVariable("patientId") Long patientId){
        String message = patientService.assignDoctor(patientId,doctorId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
