package com.pm.patient_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //To handle exceptions globally across all controllers
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class); //Logger for logging exception details

    @ExceptionHandler(MethodArgumentNotValidException.class) //Handles validation errors
    public ResponseEntity<Map<String, String>> HandleValidationException(MethodArgumentNotValidException ex) {
        //Extracting field errors and mapping them to a more readable format
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors); //Returning a 400 Bad Request with error detail

        //Todo: Add exception handles for the dateOfBirth format


    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email address already exists: {} ", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }


}
