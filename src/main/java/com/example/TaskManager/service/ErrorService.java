package com.example.TaskManager.service;

import com.example.TaskManager.exception.GlobalExceptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class ErrorService {
    public static ResponseEntity<String> errorHandler(){
        return ResponseEntity.internalServerError()
                .body(new GlobalExceptions()
                        .internalServerError(new Exception())).getBody();
    }
}
