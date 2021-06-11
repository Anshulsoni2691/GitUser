package com.systemplus.spring.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice

public class ExceptionResolver extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleNoHandlerFound(HttpServletRequest request, Exception e) {
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		error.setMessage("No such user found");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<String> handleHttpMediaTypeNotAcceptableException() {
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.toString());
		error.setMessage("Not acceptable content type");
		return new ResponseEntity<String>(error.toString(), HttpStatus.NOT_ACCEPTABLE);
	}

}