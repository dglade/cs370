package edu.luc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckParser;

public class AcceptanceTest {

	private CheckParser parser;
	private ArrayList<String> arrayList;

	@Before
	public void setUp() {
		parser = new CheckParser();
		arrayList = new ArrayList<String>();
		arrayList.add("fifty-five");
		arrayList.add("and");
		arrayList.add("55/100");
	}
	
	@Test
	public void shouldParseWholeValuesLessThanTen() throws Exception {
        assertThat(parsedAmountOf("one"), is(equalTo(100)));
        assertThat(parsedAmountOf("two"), is(equalTo(200)));
        assertThat(parsedAmountOf("three"), is(equalTo(300)));
        assertThat(parsedAmountOf("four"), is(equalTo(400)));
        assertThat(parsedAmountOf("five"), is(equalTo(500)));
        assertThat(parsedAmountOf("six"), is(equalTo(600)));
        assertThat(parsedAmountOf("seven"), is(equalTo(700)));
        assertThat(parsedAmountOf("eight"), is(equalTo(800)));
        assertThat(parsedAmountOf("nine"), is(equalTo(900)));
        assertThat(parsedAmountOf("ten"), is(equalTo(1000)));
	}
	
	@Test
	public void shouldParseCompoundNumbersLessThanOneHundred() throws Exception {
        assertThat(parsedAmountOf("fifty-five"), is(equalTo(5500)));
        assertThat(parsedAmountOf("ninety-nine"), is(equalTo(9900)));
        assertThat(parsedAmountOf("twenty-three"), is(equalTo(2300)));
	}
	
	@Test 
	public void shouldParseValuesFromFractions() throws Exception {
        assertThat(parsedAmountOf("50/100"), is(equalTo(50)));
        assertThat(parsedAmountOf("0/100"), is(equalTo(0)));
        assertThat(parsedAmountOf("150/100"), is(equalTo(150)));
	}
	
	@Test 
	public void shouldParseFractionsToCents() throws Exception {
		assertThat(parsedCentsFromFraction("0/100"), is(equalTo(0)));
		assertThat(parsedCentsFromFraction("00/100"), is(equalTo(0)));
		assertThat(parsedCentsFromFraction("1/100"), is(equalTo(1)));
		assertThat(parsedCentsFromFraction("01/100"), is(equalTo(1)));
		assertThat(parsedCentsFromFraction("50/100"), is(equalTo(50)));
		assertThat(parsedCentsFromFraction("99/100"), is(equalTo(99)));
		assertThat(parsedCentsFromFraction("100/100"), is(equalTo(100)));
		assertThat(parsedCentsFromFraction("130/100"), is(equalTo(130)));
	}
	
	@Test
	public void returnValueOfComplexString() throws Exception {
		assertThat(convertedString("Ninety-nine and 99/100"), is(equalTo(9999)));
		assertThat(convertedString("Fifty-four and 130/100"), is(equalTo(5530)));
		assertThat(convertedString("Thirty and 0/100"), is(equalTo(3000)));
		assertThat(convertedString("Zero and 100/100"), is(equalTo(100)));
	}
	
	@Test
	public void createArrayFromStringComponents() throws Exception {
		assertThat(createdArray("Nine and 99/100").size(), is(equalTo(3)));
		assertThat(createdArray("Twenty-two and 10/100").get(0), is(equalTo("Twenty-two")));
		assertThat(createdArray("Fifty-one and 9/100").get(1), is(equalTo("and")));
		assertThat(createdArray("Ninety and 0/100").get(2), is(equalTo("0/100")));
	}
	
	@Test
	public void convertArrayOfStringsToArrayOfIntegers() throws Exception {
		assertThat(convertedArray(arrayList).size(), is(equalTo(3)));
		assertThat(convertedArray(arrayList).get(0), is(equalTo(5500)));
		assertThat(convertedArray(arrayList).get(2), is(equalTo(55)));
	}
	
	@Test
	public void shouldFindAndInString() throws Exception {
		arrayList.add("dollar");
		arrayList.add("dollars");
		assertThat(removedWord("and", arrayList).size(), is(equalTo(4)));
		assertThat(removedWord("dollar", arrayList).size(), is(equalTo(3)));
		assertThat(removedWord("dollars", arrayList).size(), is(equalTo(2)));
		assertThat(convertedArray(arrayList).get(0), is(equalTo(5500)));
		assertThat(convertedArray(arrayList).get(1), is(equalTo(55)));
	}
	
	
	
	private int convertedString(String checkString) {
		return parser.processCheckString(checkString);
	}
	
	private int parsedCentsFromFraction(String fraction) throws Exception {
		return parser.parseFractionToCents(fraction);
	}
	
	private ArrayList<String> removedWord(String word, ArrayList<String> input) {
		return parser.removeWord(word, input);
	}
	
	private int parsedAmountOf(String amount) {
		return parser.parseAmount(amount).intValue();
	}
	
	private ArrayList<String> createdArray(String input) {
		return parser.createArrayListFromString(input);
	}
	
	private ArrayList<Integer> convertedArray(ArrayList<String> input) {
		return parser.convertStringArrayListToIntegerArrayList(input);
	}

	//what about improper fractions?
	//check for other forms "and"
	//test isNumeric

}
