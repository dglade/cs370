package edu.luc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckParser;

public class AcceptanceTest {

	private CheckParser parser;

	@Before
	public void setUp() {
		parser = new CheckParser();
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
	
//	@Test 
//	public void shouldParseValuesFromFractions() throws Exception {
//        assertThat(parsedAmountOf("50/100"), is(equalTo(50)));
//        assertThat(parsedAmountOf("0/100"), is(equalTo(0)));
//        assertThat(parsedAmountOf("150/100"), is(equalTo(150)));
//	}
	
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
	public void createArrayOfStringComponents() throws Exception {
		assertThat(createdArray("Nine and 99/100").length, is(equalTo(3)));
		assertThat(createdArray("Twenty-two and 10/100")[0], is(equalTo("Twenty-two")));
		assertThat(createdArray("Fifty-one and 9/100")[1], is(equalTo("and")));
		assertThat(createdArray("Ninety and 0/100")[2], is(equalTo("0/100")));
	}
	
	@Test
	public void shouldFindAndInString() throws Exception {
		assertThat(foundAndIndexOf("Nine and 99/100"), is(equalTo(5)));
	}
	
	
	
	
	
	private int parsedCentsFromFraction(String fraction) throws Exception {
		return parser.parseFractionToCents(fraction);
	}
	
	private int foundAndIndexOf(String input) {
		return parser.findAnd(input);
	}
	
	private int parsedAmountOf(String amount) {
		return parser.parseAmount(amount).intValue();
	}
	
	private String[] createdArray(String input) {
		return parser.createArrayFromString(input);
	}

	//what about improper fractions?
	//check for other forms "and"
	//test isNumeric

}
