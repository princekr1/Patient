package com.example.patient;

import com.example.patient.entity.Doctor;
import com.example.patient.exception.EntityNotFound;
import com.example.patient.model.DoctorDTO;
import com.example.patient.repository.DoctorRepository;
import com.example.patient.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoctorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    void createDoctorTest() throws Exception {
        DoctorDTO doctorDTO = new DoctorDTO("Dr Rani Saxena", "F","Delhi");

        mockMvc.perform(post("/doctor/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isCreated());

        List<Doctor> doctors = doctorRepository.findAllByName(doctorDTO.getName());
        List<String> patientNames = doctors.stream().map(patient -> patient.getName()).collect(Collectors.toList());
        assertThat(patientNames.get(0)).isEqualTo(doctorDTO.getName());
    }

    @Test
    void editDoctorTest() throws Exception {
        DoctorDTO doctorDTO = new DoctorDTO("Dr Rani Saxena", "F","Delhi");


        mockMvc.perform(delete("/doctor/delete/122")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        mockMvc.perform(put("/doctor/edit/122")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFound));

    }





}
