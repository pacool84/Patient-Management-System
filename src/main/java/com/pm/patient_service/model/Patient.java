package com.pm.patient_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity //Indicates that this class is a JPA entity - maps to a database table
public class Patient {
    //Attributes
    @Id //Indicates the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) //Specifies the generation strategy for the primary key - UUID generation - Strategy.AUTO lets the JPA provider choose the strategy
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email //Validates that the string is a well-formed email address
    @Column(unique = true) //Ensures that the email column has unique values in the database
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registeredDate;

    //Constructors
//    public Patient() {
//
//    }
//
//    public Patient(UUID id, String name, String email, String address, LocalDate dateOfBirth, LocalDate registeredDate) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.address = address;
//        this.dateOfBirth = dateOfBirth;
//        this.registeredDate = registeredDate;
//    }

    //Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
