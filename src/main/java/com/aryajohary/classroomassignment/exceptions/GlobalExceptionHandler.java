package com.aryajohary.classroomassignment.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> noResouceFoundHandler(NoResourceFoundException exc){
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage("There is no such page. check the endpoint");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


    // exception handler for invalid json body when using POST method
    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleInvalidJSON(HttpMessageNotReadableException exc){
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("JSON Syntax not correct. See \"/syntax\" path for proper syntax");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    // exception handler for constraint violations while giving values in json body
    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(errors.toString());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // when url method is wrong, i always get this error. so i handled i here.
    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException exc){
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setMessage("Check URL. Method not allowed here");
        errorResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }

    // this one for handling URLs which have not been defined.
    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleMethodArguementTypeMismatch(MethodArgumentTypeMismatchException exc){
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setMessage("No such URL is present. Check README for URL list");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    // exception handler for situations when some required Id couldn't be found
    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> handleEntityNotFoundException(CustomEntityNotFoundException exc){
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
