package com.gustavo.api.services.utils;

import java.util.ArrayList;
import java.util.List;

public class ComicDataContainer {
	
	private List<Comic> results = new ArrayList<>();

	public List<Comic> getResults() {
		return results;
	}

	public void setResults(List<Comic> results) {
		this.results = results;
	}
	
}

