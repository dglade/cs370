package edu.luc.clearing;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class CheckHistory {

	private DataStoreAdapter storeAdapter;
	private Gson gson;
	
	public CheckHistory(DataStoreAdapter storeAdapter) {
		this.storeAdapter = storeAdapter;
		gson = new Gson();
	}
	
	public String getAmounts(String limitStr) {
		Integer l = null;
		if (limitStr != null) {
			l = Integer.parseInt(limitStr);
		}
		Set<String> amounts = new HashSet<String>();
		List<Map<String, Object>> runQuery = storeAdapter.runQuery("Checks");
		for(Map<String, Object> properties : runQuery) {
			if (limitStr == null || (amounts.size() < l.intValue()))
				amounts.add(properties.get("amount").toString());
		}
		return gson.toJson(amounts);
	}
}
