package edu.luc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckParser;

public class AcceptanceTest {

	private CheckParser parser;
	private String[] array = {"fifty", "five", "55"};

	@Before
	public void setUp() {
		parser = new CheckParser();
	}
	
	@Test
	public void returnsValueOfSimpleDollarAmount() throws Exception {
		assertThat(convertedString("6"), is(equalTo(600)));
		assertThat(convertedString("SIX"), is(equalTo(600)));
		assertThat(convertedString("16"), is(equalTo(1600)));
		assertThat(convertedString("Sixteen"), is(equalTo(1600)));
		assertThat(convertedString("24"), is(equalTo(2400)));
		assertThat(convertedString("Twenty-four"), is(equalTo(2400)));
	}
	
	@Test
	public void returnsValueOfDollarOrDollarSign() throws Exception {
		assertThat(convertedString("$10"), is(equalTo(1000)));
		assertThat(convertedString("$TEN"), is(equalTo(1000)));
		assertThat(convertedString("10 dollars"), is(equalTo(1000)));
		assertThat(convertedString("10 dollar"), is(equalTo(1000)));
		assertThat(convertedString("ten dollar"), is(equalTo(1000)));
		assertThat(convertedString("ten dollars"), is(equalTo(1000)));
	}
	
	@Test
	public void returnsValueOfOnlyCentAmount() throws Exception {
		assertThat(convertedString("10 cents"), is(equalTo(10)));
		assertThat(convertedString("10/100"), is(equalTo(10)));
		assertThat(convertedString("10/100 cents"), is(equalTo(10)));
	}
	
	@Test
	public void returnsValueOfDollarCentStringWithAnd() throws Exception {
		assertThat(convertedString("64 and 12/100"), is(equalTo(6412)));
		assertThat(convertedString("Sixty-four and 12"), is(equalTo(6412)));
	}
	
	@Test
	public void handlesHyphenOrNoHyphenForCompoundNumbers() throws Exception {
		assertThat(convertedString("SIXTY FOUR"), is(equalTo(6400)));
		assertThat(convertedString("SIXTY-FOUR"), is(equalTo(6400)));
	}
	
	@Test
	public void returnsValueOfDollarCentStringWithAndDollarsOrCents() throws Exception {
		assertThat(convertedString("sixty nine dollars and twenty four cents"), is(equalTo(6924)));
		assertThat(convertedString("sixty nine dollars and 28 cents"), is(equalTo(6928)));
		assertThat(convertedString("sixty nine dollars and 28/100"), is(equalTo(6928)));
		assertThat(convertedString("sixty nine dollar twenty four cents"), is(equalTo(6924)));
		assertThat(convertedString("sixty nine dollar 28 cents"), is(equalTo(6928)));
		assertThat(convertedString("sixty nine dollar 28/100"), is(equalTo(6928)));
		assertThat(convertedString("sixty nine and twenty four cents"), is(equalTo(6924)));
		assertThat(convertedString("sixty nine and 28 cents"), is(equalTo(6928)));
		assertThat(convertedString("sixty nine and 28/100"), is(equalTo(6928)));
	}
	
	@Test
	public void handlesAmpersand() throws Exception {
		assertThat(convertedString("sixty nine & twenty four cents"), is(equalTo(6924)));
	}
	
	@Test
	public void handlesNoOr0() throws Exception {
		assertThat(convertedString("0 dollars and 0 cents"), is(equalTo(0)));
		assertThat(convertedString("Zero dollars and zero cents"), is(equalTo(0)));
		assertThat(convertedString("No dollars and no cents"), is(equalTo(0)));
		assertThat(convertedString("0/100 cents"), is(equalTo(0)));
	}
	
	@Test
	public void handlesImproperFractionalAmounts() throws Exception {
		assertThat(convertedString("130/100 cents"), is(equalTo(130)));
		assertThat(convertedString("100/100 cents"), is(equalTo(100)));

	}
	
	@Test
	public void eliminatesWhiteSpaceAtBeginningOrEndOfString() throws Exception {
		assertThat(convertedString("   one dollar and zero cents"), is(equalTo(100)));
		assertThat(convertedString("one dollar and zero cents   "), is(equalTo(100)));
		assertThat(convertedString("  one dollar and zero cents   "), is(equalTo(100)));
	}
	
	@Test
	public void eliminatesAdditionalWhiteSpaceInString() throws Exception {
		assertThat(convertedString("1  dollars   and one    cents"), is(equalTo(101)));
	}

	@Test
	public void handlesDash() throws Exception {
		assertThat(convertedString("sixty-eight dollars --- 4/100 cents"), is(equalTo(6804)));
		assertThat(convertedString("25 -- 47/100"), is(equalTo(2547)));
	}
	
	@Test
	public void handlesPlusSign() throws Exception {
		assertThat(convertedString("$60 four + 32 cents"), is(equalTo(6432)));
		assertThat(convertedString("twenty-four + 32/100"), is(equalTo(2432)));
		assertThat(convertedString("22 + 46/100"), is(equalTo(2246)));
	}
	
	@Test
	public void handlesComma() throws Exception {
		assertThat(convertedString("80 two , 46/100 cents"), is(equalTo(8246)));
		assertThat(convertedString("eighty-9, 46/100 cents"), is(equalTo(8946)));
	}
	
	@Test
	public void handlesTilda() throws Exception {
		assertThat(convertedString("$forty 5 ~ 16 cents"), is(equalTo(4516)));
		assertThat(convertedString("$45 ~ 46/100"), is(equalTo(4546)));
	}
	
	@Test
	public void handlesHundreds() throws Exception {
		assertThat(convertedString("$405 and 16 cents"), is(equalTo(40516)));
		assertThat(convertedString("Four hundred five and 46/100"), is(equalTo(40546)));
		assertThat(convertedString("$Eighteen hundred and 56 cents"), is(equalTo(180056)));
		assertThat(convertedString("Twenty two hundred five and 46/100"), is(equalTo(220546)));
	}
	
	@Test
	public void handlesIncreasingStringsToThousands() throws Exception {
		assertThat(convertedString("FIVE THOUSAND AND 56/100"), is(equalTo(500056)));
		assertThat(convertedString("Fifty-five THOUSAND AND 56/100"), is(equalTo(5500056)));
		assertThat(convertedString("FIVE HUNDRED THREE THOUSAND AND 56/100"), is(equalTo(50300056)));
		assertThat(convertedString("FIFTY-FIVE HUNDRED THOUSAND FIFTY-FIVE AND 56/100"), is(equalTo(550005556)));
	}
	
	@Test
	public void handlesIncreasingAndDecreasingStringsToThousands() {
		assertThat(convertedString("Five hundred three thousand six hundred fifty-two and 36/100"), is(equalTo(50365236)));
	}
	
	@Test
	public void returnNullForMalformedString() throws Exception {
		assertThat(convertedString("purple"), is(nullValue()));
		assertThat(convertedString("purple dollars"), is(nullValue()));
		assertThat(convertedString("purple dollars and blue cents"), is(nullValue()));
		assertThat(convertedString("6 dollars and blue cents"), is(nullValue()));
		assertThat(convertedString("purple dollars and 6 cents"), is(nullValue()));
	}
	
	@Test
	public void createArrayFromStringComponents() throws Exception {
		assertThat(createdArray("Nine and 99/100").length, is(equalTo(2)));
		assertThat(createdArray("Twenty-two and 10/100")[0], is(equalTo("twenty two")));
		assertThat(createdArray("Fifty-one and 9/100")[1], is(equalTo("9")));
		assertThat(createdArray("Ninety and 0/100")[1], is(equalTo("0")));
	}
	
	@Test
	public void convertArrayOfStringsToArrayOfIntegers() throws Exception {
		assertThat(convertedArray(array).size(), is(equalTo(3)));
		assertThat(convertedArray(array).get(0), is(equalTo(50)));
		assertThat(convertedArray(array).get(1), is(equalTo(5)));
		assertThat(convertedArray(array).get(2), is(equalTo(55)));
	}
	
	private Integer convertedString(String checkString) {
		return parser.processCheckString(checkString);
	}
	
	private Integer parsedAmountOf(ArrayList<Integer> amounts) {
		return parser.parseAmount(amounts).intValue();
	}
	
	private String[] createdArray(String input) {
		return parser.processString(input);
	}
	
	private ArrayList<Integer> convertedArray(String[] input) {
		return parser.stringArrToIntegerArr(input);
	}

}
