package com.davcd.kubespringfibonacci.service;

import com.davcd.kubespringfibonacci.exception.InvalidArgumentFibonacciException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class FibonacciServiceTest {
	
	private FibonacciService fibonacciService;
	
	@BeforeAll
	void setUp() {
		fibonacciService = new FibonacciService();
	}
	
	@ParameterizedTest
	@CsvSource({"0,0", "1,1", "10,55", "100,354224848179261915075"})
	void shouldReturnNThFibonacciSequenceNumber_whenNIsValid(Integer n, BigInteger expected) throws InvalidArgumentFibonacciException {
		assertThat(fibonacciService.calculateFibonacci(n)).isEqualTo(expected);
	}
	
	@Test
	void shouldThrowIllegalArgumentException_whenNIsNegative() {
		assertThatThrownBy(() -> fibonacciService.calculateFibonacci(-1)).isInstanceOf(InvalidArgumentFibonacciException.class);
	}
}
