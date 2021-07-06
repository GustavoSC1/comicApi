package com.gustavo.api.dto;

import java.io.Serializable;

import com.gustavo.api.entities.Author;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(Author author) {
		super();
		this.id = author.getId();
		this.name = author.getName();
	}
	
	// getters e setters omitidos
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
