package edu.luc.clearing;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CheckHistoryTest {

	private CheckHistory history;
	DataStoreAdapter mockDataStoreAdapter;

	@Before
	public void setUp() {
		mockDataStoreAdapter = Mockito.mock(DataStoreAdapter.class);
		history = new CheckHistory(mockDataStoreAdapter);
	}
	
	@Test
	public void getRequestReturnsAllThePreviouslyEncounteredCheckAmounts() throws Exception {
		Map<String, Object> check = new HashMap<String, Object>();
		check.put("amount", "one");
		List<Map<String, Object>> checks = Arrays.asList(check);
		Mockito.when(mockDataStoreAdapter.runQuery("Checks")).thenReturn(checks); 
		assertEquals("[\"one\"]", history.getAmounts());
	}

}
