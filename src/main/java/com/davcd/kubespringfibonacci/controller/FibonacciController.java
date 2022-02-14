package com.davcd.kubespringfibonacci.controller;

import com.davcd.kubespringfibonacci.exception.InvalidArgumentFibonacciException;
import com.davcd.kubespringfibonacci.exception.InvalidRequestFibonacciException;
import com.davcd.kubespringfibonacci.service.FibonacciService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/fib")
@Slf4j
@RequiredArgsConstructor
public class FibonacciController {
	
	private final FibonacciService fibonacciService;
	
	@GetMapping(produces = TEXT_PLAIN_VALUE)
	public ResponseEntity<String> calculateFibonacci(@RequestParam Integer n) throws InvalidRequestFibonacciException {
		log.info("Calculating {}-th fibonacci sequence", n);
		try {
			BigInteger result = fibonacciService.calculateFibonacci(n);
			log.info("Result of {}-th fibonacci sequence is {}", n, result);
			return ResponseEntity.ok(result.toString());
		} catch (InvalidArgumentFibonacciException e) {
			throw new InvalidRequestFibonacciException("Parameter 'n' must be positive");
		}
	}
	
}
