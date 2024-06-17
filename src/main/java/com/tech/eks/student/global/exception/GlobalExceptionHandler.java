package com.tech.eks.student.global.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	public GlobalExceptionHandler() {
		log.info("GlobalExceptionHandler Object is created >>>>>>> ");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	  @ExceptionHandler(Exception.class) public ResponseEntity<ErrorDetails>
	  handleGlobalException(Exception ex, WebRequest request) {
	  log.info("handleGlobalException() calling ***** ");
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	  request.getDescription(false));
	  return new ResponseEntity<>(errorDetails,
	  HttpStatus.BAD_REQUEST); }
	 
}
