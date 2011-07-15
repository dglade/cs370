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
	}

	@Test 
	public void shouldParseCentValuesLessThanTen() throws Exception {
		assertThat(parsedAmountOf("0"), is(equalTo(0)));
		assertThat(parsedAmountOf("1"), is(equalTo(1)));
		assertThat(parsedAmountOf("2"), is(equalTo(2)));
		assertThat(parsedAmountOf("3"), is(equalTo(3)));
		assertThat(parsedAmountOf("4"), is(equalTo(4)));
		assertThat(parsedAmountOf("5"), is(equalTo(5)));
		assertThat(parsedAmountOf("6"), is(equalTo(6)));
		assertThat(parsedAmountOf("7"), is(equalTo(7)));
		assertThat(parsedAmountOf("8"), is(equalTo(8)));
		assertThat(parsedAmountOf("9"), is(equalTo(9)));
	}
	
	@Test
	public void shouldParseCentValuesTenToNinetyNine() throws Exception {
		assertThat(parsedAmountOf("10"), is(equalTo(10)));
		assertThat(parsedAmountOf("11"), is(equalTo(11)));
		assertThat(parsedAmountOf("22"), is(equalTo(22)));
		assertThat(parsedAmountOf("50"), is(equalTo(50)));
		assertThat(parsedAmountOf("99"), is(equalTo(99)));
	}
	
	@Test
	public void shouldParseTwoDigitNumbersLessThanTen() throws Exception {
		assertThat(parsedAmountOf("00"), is(equalTo(0)));
		assertThat(parsedAmountOf("01"), is(equalTo(1)));
		assertThat(parsedAmountOf("02"), is(equalTo(2)));
		assertThat(parsedAmountOf("03"), is(equalTo(3)));
		assertThat(parsedAmountOf("04"), is(equalTo(4)));
		assertThat(parsedAmountOf("05"), is(equalTo(5)));
		assertThat(parsedAmountOf("06"), is(equalTo(6)));
		assertThat(parsedAmountOf("07"), is(equalTo(7)));
		assertThat(parsedAmountOf("08"), is(equalTo(8)));
		assertThat(parsedAmountOf("09"), is(equalTo(9)));
	}
	
	@Test
	public void shouldFindAndInString() throws Exception {
		assertThat(foundAndIndexOf("Nine and 99/100"), is(equalTo(5)));
	}
	
	private int foundAndIndexOf(String input) {
		return parser.findAnd(input);
	}
	private int parsedAmountOf(String amount) {
		return parser.parseAmount(amount).intValue();
	}

	//check for other forms "and"
}
