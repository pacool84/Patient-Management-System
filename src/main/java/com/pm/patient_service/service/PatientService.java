package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        List<Patient> patients = patientRepository.findAll(); //Fetch all patients from the database

        return patients.stream()
                .map(PatientMapper::toDTO).toList();

//       The previous lambda expression replace the following code:
//        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
//                .map(patient -> PatientMapper
//                        .toDTO(patient)).toList();

    }

    //Add a new patient method
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        //Check if email already exists
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with these Email already exists: " + patientRequestDTO.getEmail());
        }

        Patient newpatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newpatient);
    }

    //Get a patient by id method
    public PatientResponseDTO getPatientById(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return PatientMapper.toDTO(patient);
    }

    //Update a patient method
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        //Check a patient by id exists
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        //Also we need to check if the email already exists, but ignore the current patient email
        if (patientRepository.existsByEmailAndIdNot (patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with these Email already exists: " + patientRequestDTO.getEmail());
        }

        //Update patient details
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        //Save updated patient
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);


}

    //Delete a patient method
    public void deletePatient(UUID id) {
        //Check a patient by id exists
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id)); //If not found, throw exception
        patientRepository.delete(patient);

    }


    //Another approach for delete a patient
    /*
     public void deletePatient(UUID id) {
    patientRepository.deleteById(id);
  }
     */
}
