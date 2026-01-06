package com.pm.patient_service.controller;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.dto.validators.CreatePatientValidationGroup;
import com.pm.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Management", description = "APIs for managing patients") // Swagger tag to group endpoints and provide description
public class PatientController {
    //Dependency Injection
    private final PatientService patientService;

    //Constructor
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Endpoint to get all patients
    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients in the system") // Swagger tag to describe the operation and provide summary
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    //Endpoint to get a patient by id
    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieve a patient's details by their unique ID") // Swagger tag to describe the operation and provide summary
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        PatientResponseDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok().body(patient);
    }

    //Endpoint to create a new patient
    @PostMapping
    @Operation(summary = "Create a new patient", description = "Add a new patient to the system") // Swagger tag to describe the operation and provide summary
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
        //return ResponseEntity.status(201).body(patientResponseDTO);

    }

    //Endpoint to update a patient
    //localhost:8080/patients/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Update the details of an existing patient by their unique ID") // Swagger tag to describe the operation and provide summary
    //Using @Validated with Default.class group for validation, to allow for future validation groups, e.g., for partial updates
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    //Endpoint to delete a patient
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Remove a patient from the system by their unique ID") // Swagger tag to describe the operation and provide summary
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
