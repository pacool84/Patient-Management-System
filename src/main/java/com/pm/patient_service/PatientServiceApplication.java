package com.pm.patient_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

    @GetMapping
    public String helloWorld() {
        java.time.ZonedDateTime now = java.time.ZonedDateTime.now(java.time.ZoneId.systemDefault());
        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return String.format("Hello — My Friend Spring Boot service. Server time: %s", now.format(fmt));
    }
    
}


