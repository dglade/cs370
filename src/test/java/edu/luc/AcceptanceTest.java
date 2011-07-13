package edu.luc;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckClearingServlet;

public class AcceptanceTest {

	private CheckClearingServlet servlet;

	@Before
	public void setUp() {
		servlet = new CheckClearingServlet();
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
        
//        assertEquals(200, servlet.parseAmount("two").intValue());
//        assertEquals(300, servlet.parseAmount("three").intValue());
//        assertEquals(400, servlet.parseAmount("four").intValue());
//        assertEquals(500, servlet.parseAmount("five").intValue());
//        assertEquals(600, servlet.parseAmount("six").intValue());
//        assertEquals(700, servlet.parseAmount("seven").intValue());
//        assertEquals(800, servlet.parseAmount("eight").intValue());
//        assertEquals(900, servlet.parseAmount("nine").intValue());
	}



	private int parsedAmountOf(String amount) {
		return servlet.parseAmount(amount).intValue();
	}

}