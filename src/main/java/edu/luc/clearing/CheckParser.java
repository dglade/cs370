package edu.luc.clearing;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class CheckParser {

	private static final Map<String, Integer> AMOUNTS = new HashMap<String, Integer>();
	
	public CheckParser() {
		AMOUNTS.put("zero", 0);
		AMOUNTS.put("one", 100);
		AMOUNTS.put("two", 200);
		AMOUNTS.put("three", 300);
		AMOUNTS.put("four", 400);
		AMOUNTS.put("five", 500);
		AMOUNTS.put("six", 600);
		AMOUNTS.put("seven", 700);
		AMOUNTS.put("eight", 800);
		AMOUNTS.put("nine", 900);
		AMOUNTS.put("ten", 1000);
		AMOUNTS.put("eleven", 1100);
		AMOUNTS.put("twelve", 1200);
		AMOUNTS.put("thirteen", 1300);
		AMOUNTS.put("forteen", 1400);
		AMOUNTS.put("fifteen", 1500);
		AMOUNTS.put("sixteen", 1600);
		AMOUNTS.put("seventeen", 1700);
		AMOUNTS.put("eighteen", 1800);
		AMOUNTS.put("nineteen", 1900);
		AMOUNTS.put("twenty", 2000);
		AMOUNTS.put("thirty", 3000);
		AMOUNTS.put("forty", 4000);
		AMOUNTS.put("fifty", 5000);
		AMOUNTS.put("sixty", 6000);
		AMOUNTS.put("seventy", 7000);
		AMOUNTS.put("eighty", 8000);
		AMOUNTS.put("ninety", 9000);
	}
	
	public Integer parseAmount(String amount) {
		amount = amount.toLowerCase();
		String[] CompoundNumberParts = amount.split("-");
		if(CompoundNumberParts.length == 2 && AMOUNTS.containsKey(CompoundNumberParts[0]) && AMOUNTS.containsKey(CompoundNumberParts[1]))
			return parseAmount(CompoundNumberParts[0]) + parseAmount(CompoundNumberParts[1]);
		else return AMOUNTS.get(amount.toLowerCase());
	}
	
	private boolean isNumeric(String amount) {
		for(int i = 0; i < amount.length(); i++)
			if(!Character.isDigit(amount.charAt(i)))
				return false;
		return true;
	}
	
	public Integer parseFractionToCents(String fraction) {
		String[] fractionComponents = fraction.split("/");
		Integer numerator = Integer.valueOf(fractionComponents[0]);
		Integer denominator = Integer.valueOf(fractionComponents[1]);
		if (numerator < denominator)
			return numerator % denominator;
		else if (numerator == denominator)
			return 100;
		else return (numerator/denominator) * 100 + numerator%denominator;
	}
	
	public String[] createArrayFromString(String input) {
		return input.split(" ");
	}
	
	public Integer findAnd(String input) {
		input.toLowerCase();
		return input.indexOf("and");
	}
}