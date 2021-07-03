package com.gustavo.api.services.utils;

import java.util.ArrayList;
import java.util.List;

public class Comic {
	
	private Integer id;
	private String title;
	private String isbn;
	private String description;
	private List<ComicPrice> prices = new ArrayList<>();
	private CreatorList creators;
	
	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<ComicPrice> getPrices() {
		return prices;
	}
	
	public CreatorList getCreators() {
		return creators;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrices(List<ComicPrice> prices) {
		this.prices = prices;
	}
	
	public void setCreators(CreatorList creators) {
		this.creators = creators;
	}
	
}