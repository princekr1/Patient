package com.example.patient.restcontroller;

import com.example.patient.entity.Doctor;
import com.example.patient.entity.Patient;
import com.example.patient.model.DoctorDTO;
import com.example.patient.model.PatientDTO;
import com.example.patient.service.ContactService;
import com.example.patient.service.DoctorService;
import com.example.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/doctor")
public class DoctorController {

    public static final Logger logger= LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    DoctorService doctorService;

        @RequestMapping(value="/create", method= RequestMethod.POST)
        public ResponseEntity<?> create(@Valid @RequestBody DoctorDTO doctorDTO){
            String message = doctorService.create(doctorDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }

        @RequestMapping(value="/edit/{id}", method= RequestMethod.PUT)
        public ResponseEntity<?> edit(
                @PathVariable("id") Long id,
               @Valid @RequestBody DoctorDTO doctorDTO){
            String message = doctorService.edit(doctorDTO,id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        @RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE)
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            String message = doctorService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        @RequestMapping(value="/list", method= RequestMethod.GET)
        public ResponseEntity<?> list(){
            List<Doctor> doctorList = doctorService.list();
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }

}
