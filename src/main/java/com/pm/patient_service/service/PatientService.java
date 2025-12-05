package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    //Pattern DEPENDENCY INJECTION - injecting the repository into the service
    private PatientRepository patientRepository;

    //Constructor but still part of Dependency Injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Get all patients method
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();

//       The previous lambda expression replace the following code:
//        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
//                .map(patient -> PatientMapper
//                        .toDTO(patient)).toList();

    }
}
