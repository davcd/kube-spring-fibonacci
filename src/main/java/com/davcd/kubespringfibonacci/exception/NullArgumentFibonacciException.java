package com.davcd.kubespringfibonacci.exception;

public class NullArgumentFibonacciException extends InvalidArgumentFibonacciException {
	public NullArgumentFibonacciException(String message) {
		super(message);
	}
}
