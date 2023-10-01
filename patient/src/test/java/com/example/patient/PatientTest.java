package com.example.patient;

import com.example.patient.entity.Doctor;
import com.example.patient.entity.Patient;
import com.example.patient.exception.EntityNotFound;
import com.example.patient.model.DoctorDTO;
import com.example.patient.model.PatientDTO;
import com.example.patient.repository.DoctorRepository;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.PatientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${env}")
    String env;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void createPatientTest() throws Exception {
        PatientDTO patientDTO = new PatientDTO("Raj Babu", "M","Lucknow", "COMPLETED",25);

        mockMvc.perform(post("/patient/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().isCreated());

        List<Patient> patients = patientRepository.findAllByName(patientDTO.getName());
        List<String> patientNames = patients.stream().map(patient -> patient.getName()).collect(Collectors.toList());
        assertThat(patientNames.get(0)).isEqualTo(patientDTO.getName());
    }

    @Test
    public void test(){
        assertThat(env).isNotEqualTo("dev");
    }

    @Test
    void editPatientTest() throws Exception {
        PatientDTO patientDTO = new PatientDTO("Mohan Misra", "M","Lucknow", "COMPLETED",25);


        mockMvc.perform(delete("/patient/delete/52")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        mockMvc.perform(put("/patient/edit/52")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFound));

    }

    @Transactional
    @Test
    void assignDoctorToPatientTest() throws Exception {

        //Adding doctor
        DoctorDTO doctorDTOFirst = new DoctorDTO("Dr Rahul Rana", "M","Mumbai");

        mockMvc.perform(post("/doctor/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(doctorDTOFirst)))
                .andExpect(status().isCreated());

        //Adding doctor
        DoctorDTO doctorDTOSecond = new DoctorDTO("Dr Rahul Rana", "M","Mumbai");

        mockMvc.perform(post("/doctor/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(doctorDTOSecond)))
                .andExpect(status().isCreated());

        List<String> nameList=new ArrayList<>();
        nameList.add(doctorDTOFirst.getName());
        nameList.add(doctorDTOSecond.getName());

        List<Doctor> doctors = doctorRepository.findAllByNameIn(nameList);

        assertThat(doctors.size()>0).isTrue();

        //Adding patient

        PatientDTO patientDTO = new PatientDTO("Raj Babu", "M","Lucknow", "COMPLETED",25);

        mockMvc.perform(post("/patient/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(status().isCreated());

        List<Patient> patients = patientRepository.findAllByName(patientDTO.getName());
        Patient patient = patients.get(0);

       for(Doctor doctor: doctors){
           mockMvc.perform(put("/patient/assignDoctor/"+doctor.getId()+"/"+patient.getId())
                           .contentType("application/json")
                           .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk());
       }

        listPatients();

    }

    @Test
    void listPatients() throws Exception {


        MvcResult mvcResult = mockMvc.perform(get("/patient/list")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<Patient> patients = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Patient>>() {});
        //assertThat(patients.size()==0).isTrue();

    }


}
