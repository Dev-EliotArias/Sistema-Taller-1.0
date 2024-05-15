package com.main.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex){
		ErrorResponse error = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
}
