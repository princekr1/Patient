package com.example.patient.restcontroller;

import com.example.patient.model.ContactDTO;
import com.example.patient.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/contact")
public class ContactController {

    public static final Logger logger= LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/addContact/{patientId}",method = RequestMethod.PUT)
    public ResponseEntity<?> addContact(
            @PathVariable("patientId") Long patientId,
            @RequestBody ContactDTO contactDTO){
        String message = contactService.addContactDetailsForPatient(patientId,contactDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
