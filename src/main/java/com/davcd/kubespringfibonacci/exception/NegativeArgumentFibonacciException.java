package com.davcd.kubespringfibonacci.exception;

public class NegativeArgumentFibonacciException extends InvalidArgumentFibonacciException {
	public NegativeArgumentFibonacciException(String message) {
		super(message);
	}
}
