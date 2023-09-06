package com.example.TaskManager.exception;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.rmi.ServerException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found!");
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> globalException(Exception e){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<String> internalServerError(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server Error, Please be patient..");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> resourceNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with the details cannot be found");
    }
}
