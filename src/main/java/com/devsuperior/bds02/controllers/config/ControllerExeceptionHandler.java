package com.devsuperior.bds02.controllers.config;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExeceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	        return ResponseEntity.status(status).body(err);
	    }

	    @ExceptionHandler(DatabaseException.class)
	    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	        return ResponseEntity.status(status).body(err);
	    }
}
