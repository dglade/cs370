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
	}
	
	@Test
	public void shouldIgnoreMalformedAmounts() throws Exception {
		assertEquals("{}", reader.respond(new StringReader("[\"purple\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"purple-two\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"purple-twenty-two\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"50/100\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"7/10\"]")));
		assertEquals("{}", reader.respond(new StringReader("[\"a/100\"]")));
	}
	
	@Test
	public void shouldIgnoreNonWholeDollarAmounts() throws Exception {	//for now!!!
		assertEquals("{}", reader.respond(new StringReader("[\"twenty-three and 99/100\"]")));
	}

}
