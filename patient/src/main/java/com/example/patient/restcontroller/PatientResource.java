package com.example.patient.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/patient")
public class PatientResource {

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ResponseEntity<?> create(){
        return null;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> edit(){
        return null;
    }

    @RequestMapping(value="/delete", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(){
        return null;
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ResponseEntity<?> list(){
        return null;
    }

}
