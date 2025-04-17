package com.ys.customannotations.controller;

import com.ys.customannotations.exception.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateRequestException(DuplicateRequestException e) {
        Map<String, String> errors = Map.of("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
