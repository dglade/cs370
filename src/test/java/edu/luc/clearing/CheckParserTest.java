package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CheckParserTest {

	private CheckParser parser;
	
	@Before
	public void setUp() {
		parser = new CheckParser();
	}
	
	@Test
	public void shouldIgnoreCase() throws Exception {
		assertEquals(300, parser.parseAmount("Three").intValue());
	}
	
	@Test
	public void shouldHandleZero() throws Exception {
		assertEquals(0, parser.parseAmount("zero").intValue());
	}
	
	@Test
	public void shouldHandleCompoundNumbers() throws Exception {
		assertEquals(3300, parser.parseAmount("THIRTY-THREE").intValue());
	}
}
