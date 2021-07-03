package com.gustavo.api.services.utils;

import java.util.ArrayList;
import java.util.List;

public class CreatorList {
	
	private List<CreatorSummary> items = new ArrayList<>();

	public List<CreatorSummary> getItems() {
		return items;
	}

	public void setItems(List<CreatorSummary> items) {
		this.items = items;
	}
	
}
