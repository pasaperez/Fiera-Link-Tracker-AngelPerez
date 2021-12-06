package com.example.fieralinktrackerangelperez.controllers;

import com.example.fieralinktrackerangelperez.dtos.ErrorDTO;
import com.example.fieralinktrackerangelperez.exceptions.LinkStorageAlreadyExistException;
import com.example.fieralinktrackerangelperez.exceptions.UrlInvalidException;
import com.example.fieralinktrackerangelperez.exceptions.UrlNotValidOrNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LinkStorageExceptionController {

    @ExceptionHandler(LinkStorageAlreadyExistException.class)
    ResponseEntity<ErrorDTO> handleGlobalExceptions(LinkStorageAlreadyExistException e) {
        return new ResponseEntity<>(e.getError(),e.getStatus());
    }

    @ExceptionHandler(UrlNotValidOrNotFoundException.class)
    ResponseEntity<ErrorDTO> handleGlobalExceptions(UrlNotValidOrNotFoundException e) {
        return new ResponseEntity<>(e.getError(),e.getStatus());
    }

    @ExceptionHandler(UrlInvalidException.class)
    ResponseEntity<ErrorDTO> handleGlobalExceptions(UrlInvalidException e) {
        return new ResponseEntity<>(e.getError(),e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}