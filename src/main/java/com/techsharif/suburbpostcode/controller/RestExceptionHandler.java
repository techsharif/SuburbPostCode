package com.techsharif.suburbpostcode.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.techsharif.suburbpostcode.dto.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackageClasses = SuburbController.class)
public class RestExceptionHandler {
    ExceptionResponseDto response = new ExceptionResponseDto();


    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(ResponseStatusException e, WebRequest request) {
        log.info("Expected exception {}", e.getMessage());
        ExceptionResponseDto response = new ExceptionResponseDto(e.getStatus(), e.getReason());
        response.set(e.getStatus(), e.getReason());
        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(MethodArgumentNotValidException e, WebRequest request) {
        log.info("Expected exception {}", e.getMessage());
        response.set(HttpStatus.BAD_REQUEST, getFieldErrors(e.getBindingResult().getFieldErrors()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(MethodArgumentTypeMismatchException e, WebRequest request) {
        log.info("Expected exception {}", e.getMessage());

        response.set(HttpStatus.BAD_REQUEST, e.getValue() + " is not a valid " + e.getName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(HttpMessageNotReadableException e, WebRequest request) {
        log.info("Expected exception {}", e.getMessage());
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException err = (InvalidFormatException) e.getCause();
            response.set(HttpStatus.BAD_REQUEST, "'" + err.getValue() + "' is not a valid request data");
        } else {
            response.set(HttpStatus.BAD_REQUEST, "Invalid data " + "Invalid Request data");
        }
        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponseDto> handleAll(Exception e, WebRequest request) {
        log.info("Unknown", e);
        ExceptionResponseDto response = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private List<Map<String, Object>> getFieldErrors(List<FieldError> errors) {
        List<Map<String, Object>> fieldErrors = new ArrayList<>();

        for (FieldError error : errors) {
            Map<String, Object> fieldError = new HashMap<>();
            fieldError.put("field", error.getField());
            fieldError.put("rejectedValue", error.getRejectedValue());
            fieldError.put("message", error.getDefaultMessage());
            fieldErrors.add(fieldError);
        }
        return fieldErrors;
    }


}
