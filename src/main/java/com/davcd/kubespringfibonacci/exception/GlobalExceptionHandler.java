package com.davcd.kubespringfibonacci.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler({
			MethodArgumentTypeMismatchException.class,
			MissingServletRequestParameterException.class
	})
	public ResponseEntity<String> handleGlobalBadRequest(Exception e) {
		log.info("Invalid request", e);
		return new ResponseEntity<>("Invalid request", BAD_REQUEST);
	}
	
	@ExceptionHandler({
			InvalidRequestFibonacciException.class
	})
	public ResponseEntity<String> handleInvalidArgumentFibonacciException(Exception e) {
		log.info(e.getMessage(), e);
		return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
	}
}
