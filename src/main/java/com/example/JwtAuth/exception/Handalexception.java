package com.example.JwtAuth.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public class Handalexception {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicateKey(DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("email")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists. Please login."));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid request."));
    }
}
