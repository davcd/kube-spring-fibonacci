package com.davcd.kubespringfibonacci.service;

import com.davcd.kubespringfibonacci.exception.InvalidArgumentFibonacciException;
import com.davcd.kubespringfibonacci.exception.NegativeArgumentFibonacciException;
import com.davcd.kubespringfibonacci.exception.NullArgumentFibonacciException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class FibonacciService {
	
	/**
	 * Fast doubling method. Faster than the matrix method.
	 * F(2n) = F(n) * (2*F(n+1) - F(n)).
	 * F(2n+1) = F(n+1)^2 + F(n)^2.
	 *
	 * @param n Fibonacci sequence number to calculate
	 * @return Value of the n-th fibonacci sequence number
	 * @throws InvalidArgumentFibonacciException representing an invalid n parameter
	 */
	public BigInteger calculateFibonacci(Integer n) throws InvalidArgumentFibonacciException {
		
		if (n == null) {
			throw new NullArgumentFibonacciException("Parameter 'n' must not be null");
		} else if (n < 0) {
			throw new NegativeArgumentFibonacciException("Parameter 'n' must be positive");
		}
		
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
			BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
			BigInteger e = a.multiply(a).add(b.multiply(b));
			a = d;
			b = e;
			
			if ((n & bit) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
			}
		}
		return a;
	}
	
}
