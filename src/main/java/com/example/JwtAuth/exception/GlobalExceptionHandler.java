package com.example.JwtAuth.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("email")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists. Please log in."));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Data integrity error"));
    }
}
