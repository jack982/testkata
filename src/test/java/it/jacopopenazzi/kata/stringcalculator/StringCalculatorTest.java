package it.jacopopenazzi.kata.stringcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class StringCalculatorTest {
	
	StringCalculator sc;

	@BeforeEach
	void setUp() {
		sc = new StringCalculator();
	}
	
	
	@Test
	void testSumEmptyString() {
		assertEquals(0, sc.sum(""), "and empty string should return 0");
	}

	@Test
	void testSumOneNumber() {
		assertEquals(3, sc.sum("3"), "a string with one number should return the sum of the number + 0");
	}
	
	@Test
	void testSumTwoNumbers() {
		assertEquals(3, sc.sum("1,2"), "a string with two numbers comma separated should return their sum");
	}
	
	@Test
	void testSumUnknownAmountOfNumbers() {
		int sum = 11;
		String numbers = "1,2,3,5";
		assertEquals(sum, sc.sum(numbers), "a string with comma separated numbers should return their sum");
	}
	
	@Test
	void testSumWithNewLine() {
		assertEquals(6, sc.sum("1\n2,3"), "a string with newLine or comma separated numbers should return their sum");
	}
	
	
	@Test
	void testSumWithCustomDelimiter() {
		assertEquals(3, sc.sum("//;\n;1;2"), "a string starting with a custom delimiter should return the sum of the numbers separated by that delimiter");
		assertEquals(4, sc.sum("//@\n2@1@1"), "customer delimiter @");
	}
	
	@Test
	void testSumNegativeWillThrowException() {
		int negative = -2;
		
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("1")
				.append(",")
				.append(negative)
				.append(",")
				.append("3");
		String numbers = sbuf.toString();
		
		String expected = "negatives not allowed: " + negative;
		
		String exception = null;
		try {
			sc.sum( numbers );
		} catch (Exception e) {
			exception = e.getMessage();
		}
		assertEquals(expected, exception, "a string with negatives number will throw an exception" );
	}
	
	@Test
	void testSumOfZeros() {
		String numbers = "0,0,0";
		assertEquals(0, sc.sum(numbers), "a string of zeros should return always a sum of zero");
	}
	
}
