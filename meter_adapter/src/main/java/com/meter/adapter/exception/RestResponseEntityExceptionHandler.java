package com.meter.adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MeterNotFoundException.class)
	public ResponseEntity<RestError> exceptionHandler(MeterNotFoundException ex, WebRequest request) {
		RestError error = new RestError();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(ex.getMessage());
		error.setRequestURL(request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);				
	}

}