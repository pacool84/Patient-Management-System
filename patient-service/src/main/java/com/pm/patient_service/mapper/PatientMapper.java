package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    // ⭐️Method to convert Patient entity to PatientResponseDTO
    public static PatientResponseDTO toDTO(Patient patient) {
        //Instance of PatientResponseDTO
        PatientResponseDTO patientDTO = new PatientResponseDTO();

        //Mapping fields from Patient entity to PatientResponseDTO
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    // ⭐️Method to convert PatientRequestDTO to Patient entity
    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        //Instance of Patient
        Patient patient = new Patient();

        //Mapping fields from PatientRequestDTO to Patient entity
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }
}
