package edu.luc.clearing;

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
	}
	
	public Integer parseAmount(String amount) {
		if (amount.length() < 3) {
			return Integer.valueOf(amount);
		}
		return AMOUNTS.get(amount.toLowerCase());
	}
	
	public Integer findAnd(String input) {
		input.toLowerCase();
		return input.indexOf("and");
	}
}
