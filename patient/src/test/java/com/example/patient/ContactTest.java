package com.example.patient;

import com.example.patient.entity.Patient;
import com.example.patient.model.ContactDTO;
import com.example.patient.model.PatientDTO;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

   @Autowired
   private ContactService contactService;
   @Autowired
   private PatientRepository patientRepository;


    @Test
    void addContactDetailsForPatientTest() throws Exception {
        PatientDTO patientDTO = new PatientDTO("Rahul Mehra", "M","Lucknow", "COMPLETED",25);

        mockMvc.perform(post("/patient/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().isCreated());

        List<Patient> patients = patientRepository.findAllByName(patientDTO.getName());
        Patient patient = patients.get(0);

        ContactDTO contactDTOFirst=new ContactDTO("Ankur","brother",9123456753l);
        mockMvc.perform(put("/contact/addContact/"+patient.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contactDTOFirst)))
                .andExpect(status().isOk());

        ContactDTO contactDTOSecond=new ContactDTO("Anjali","sister",9123464156l);
        mockMvc.perform(put("/contact/addContact/"+patient.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contactDTOSecond)))
                .andExpect(status().isOk());

    }




}
