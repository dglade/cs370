package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class RequestReaderTest {

	private RequestReader reader;
	
	@Before
	public void setUp() {
		reader = new RequestReader();
	}
    @Test
    public void shouldReturnAnEmptyObjectForAnEmptyRequest() throws Exception {
        assertEquals("{}", reader.respond(new StringReader("[]")));
    }
    
	@Test
	public void shouldReturnCentsForCheckValues() throws Exception {
        assertEquals("{\"one\":100}", reader.respond(new StringReader("[\"one\"]")));
        assertEquals("{\"seven\":700}", reader.respond(new StringReader("[\"seven\"]")));
        assertEquals("{\"twenty-two\":2200}", reader.respond(new StringReader("[\"twenty-two\"]")));
        assertEquals("{\"thirty-seven and 27/100\":3727}", reader.respond(new StringReader("[\"thirty-seven and 27/100\"]")));
        assertEquals("{\"thirty-seven dollars and 27/100\":3727}", reader.respond(new StringReader("[\"thirty-seven dollars and 27/100\"]")));
        assertEquals("{\"one dollar and 100/100\":200}", reader.respond(new StringReader("[\"one dollar and 100/100\"]")));
	}
	
	@Test
	public void shouldIgnoreMalformedAmounts() throws Exception {
		assertEquals("{}", reader.respond(new StringReader("[\"purple\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"purple-two\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"purple-twenty-two\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"7/10\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"a/100\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"Eighty and 9a/10\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"Eighty and 9/10\"]")));
	}
}
