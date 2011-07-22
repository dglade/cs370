package edu.luc.clearing;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CheckParser {

	private static final Map<String, Integer> AMOUNTS = new HashMap<String, Integer>();
	
	public CheckParser() {
		AMOUNTS.put("cent", 1);
		AMOUNTS.put("cent", 1);
		AMOUNTS.put("dollar", 100);
		AMOUNTS.put("dollars", 100);
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
		AMOUNTS.put("hundred", 10000);
		AMOUNTS.put("thousand", 100000);
		AMOUNTS.put("million", 100000000);
//		AMOUNTS.put("billion", 100000000000);
//		AMOUNTS.put("trillion", 100000000000000);
	}
	
	//if string is followed by cent(s), convert to number
	//if string is followed by dollar(s), convert to number and multiply by 100
	//"no" is the same as 0
	//replace "-" with check for greater than, less than, 
	
//	public Integer newprocessCheckString(String checkString) {
//		Integer total = 0;
//		checkString.toLowerCase();
//		String[] stringInput = checkString.split("and");
//		if (stringInput.length == 2) {
//			String[] dollarInput = stringInput[0].split("\\W");
//			String[] centInput = stringInput[1].split("\\W");
//		}
//		else if (stringInput.length == 1) {
//			String[] dollarInput = stringInput[0].split("\\W");
//		}
//		
//		ArrayList<Integer> integerInput = new ArrayList<Integer>();
//		
//		for (String word : stringInput) {
//			
//		}
//		
//	}
	
	public Integer processCheckString(String checkString) {
		Integer total = 0;
		checkString.toLowerCase();
		ArrayList<String> inputArrayList = createArrayListFromString(checkString);
		inputArrayList = removeWord("and", inputArrayList);
		inputArrayList = removeWord("dollars", inputArrayList);
		inputArrayList = removeWord("dollar", inputArrayList);
		ArrayList<Integer> integerList = convertStringArrayListToIntegerArrayList(inputArrayList);
		if (!integerList.contains(null)) {
			for (Integer amount : integerList)
				total += amount;
			return total;
		}
		else return null;
	}
	
	public Integer parseAmount(String amount) {
		amount = amount.toLowerCase();
		String[] compoundNumberParts = amount.split("-");
		if(compoundNumberParts.length == 2 && AMOUNTS.containsKey(compoundNumberParts[0]) && AMOUNTS.containsKey(compoundNumberParts[1]))
			return parseAmount(compoundNumberParts[0]) + parseAmount(compoundNumberParts[1]);
		else if(amount.contains("/"))
			return parseFractionToCents(amount);
		else return AMOUNTS.get(amount.toLowerCase());
	}
	
	public Integer parseFractionToCents(String fraction) {
		Integer numerator = null;
		Integer denominator = null;
		String[] fractionComponents = fraction.split("/");
		if (isNumeric(fractionComponents[0]) && isNumeric(fractionComponents[1])) {
			numerator = Integer.valueOf(fractionComponents[0]);
			denominator = Integer.valueOf(fractionComponents[1]);
			if (denominator == 100) {
				if (numerator < denominator)
					return numerator % denominator;
				else if (numerator == denominator)
					return 100;
				else return (numerator/denominator) * 100 + numerator%denominator;
			}
		}
		return null;
	}
	
	private boolean isNumeric(String amount) {
		for(int i = 0; i < amount.length(); i++)
			if(!Character.isDigit(amount.charAt(i)))
				return false;
		return true;
	}
	
	public ArrayList<String> createArrayListFromString(String input) {
		ArrayList<String> stringArrayList = new ArrayList<String>();
		String[] stringArray = input.split(" ");
		for (String word : stringArray) {
			stringArrayList.add(word);
		}
		return stringArrayList;
	}
	
	public ArrayList<Integer> convertStringArrayListToIntegerArrayList(ArrayList<String> input) {
		ArrayList<Integer> integerArray = new ArrayList<Integer>();
		for (String word : input)
			integerArray.add(parseAmount(word));
		return integerArray;
	}
	
	public ArrayList<String> removeWord(String word, ArrayList<String> input) {
		input.remove(word);
		return input;
	}
}