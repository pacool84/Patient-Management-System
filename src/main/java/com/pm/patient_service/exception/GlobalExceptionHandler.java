package com.pm.patient_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //To handle exceptions globally across all controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Handles validation errors
    public ResponseEntity<Map<String, String>> HandleValidationException(MethodArgumentNotValidException ex) {
        //Extracting field errors and mapping them to a more readable format
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors); //Returning a 400 Bad Request with error detail

        //Todo: Add exception handlres for the dateOfBirth format


    }


}
