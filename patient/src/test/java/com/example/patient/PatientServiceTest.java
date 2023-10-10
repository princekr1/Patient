package com.example.patient;

import com.example.patient.config.Constants;
import com.example.patient.entity.Patient;
import com.example.patient.model.PatientDTO;
import com.example.patient.repository.DoctorRepository;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceTest {

    @MockBean
    private DoctorRepository doctorRepository;
    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private PatientService patientService;


    public PatientDTO getPatientDTO(){
        PatientDTO patientDTO=new PatientDTO();
        patientDTO.setName("Narayan");
        patientDTO.setAddress("Bangalore");
        patientDTO.setAge(47);
        patientDTO.setGender("M");
        patientDTO.setTreatmentStatus(Constants.IN_PROGRESS);
        return patientDTO;
    }

    @Test
    public void savePatientTest(){
        PatientDTO patientDTO = getPatientDTO();

        Patient patient1=new Patient();
        patient1.setName(patientDTO.getName());
        Optional<Patient> optionalPatient=Optional.empty();
        System.out.println(optionalPatient.isPresent());
        when(patientRepository.findById(null)).thenReturn(optionalPatient);
        when(patientRepository.save(patient1)).thenReturn(patient1);
        String message = patientService.edit(getPatientDTO(), null);
        System.out.println("message : "+message);
        assertEquals(message,"Patient Created with name :"+patient1.getName());

    }

}
