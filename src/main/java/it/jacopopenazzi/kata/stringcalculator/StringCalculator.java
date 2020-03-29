package it.jacopopenazzi.kata.stringcalculator;

public class StringCalculator {
	
	private static final String DEFAULT_DELIMITER = ",";
	private static final String DELIMITER_PREFIX = "//";
	
	public int sum(String numbers) {
		if (numbers == null || 0 == numbers.length()) {
			return 0;
		}
		return sumStringOfNumbers(numbers);
	}
	
	
	private int sumStringOfNumbers(String numbers) {
		String delimiter = getSequenceDelimiter(numbers);
		String[] arrayOfNumbers = getArrayOfStringNumbers(numbers, delimiter);
		int sum = sumArrayOfStringNumbers(arrayOfNumbers);
		return sum;
	}
	
	private String getSequenceDelimiter(String sequence) {
		String delimiter = DEFAULT_DELIMITER;
		if(sequence.startsWith(DELIMITER_PREFIX)) {
			int delimiterStart = DELIMITER_PREFIX.length();
			int delimiterEnd = sequence.indexOf("\n");
			delimiter = sequence.substring(delimiterStart, delimiterEnd);
		}
		return delimiter;
	}
	
	private String sanitizeSequence(String sequence, String delimiter) {
		if ( !DEFAULT_DELIMITER.equals(delimiter) )
			sequence = sequence.substring(sequence.indexOf("\n") + 1) ;
		String sanitized = sequence.replaceAll("\n",delimiter);
		return sanitized;
	}
	
	private String[] getArrayOfStringNumbers(String sequence, String delimiter) {
		sequence = sanitizeSequence(sequence, delimiter);
		String[] splitted = sequence.split(delimiter);
		return splitted;
	}
	
	private int sumArrayOfStringNumbers(String[] numbers) {
		int sum = 0;
		for ( String s : numbers ) {
			if (!"".equals(s)) {
				int toAdd = Integer.parseInt(s);
				if ( toAdd >= 0 ) 
					sum += Integer.parseInt(s);
				else throw new RuntimeException("negatives not allowed: " + toAdd);
			}
		}
		return sum;
	}

}
