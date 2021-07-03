package com.gustavo.api.dto;

import java.io.Serializable;

import com.gustavo.api.entities.Creator;

public class CreatorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CreatorDTO() {
		
	}
	
	public CreatorDTO(Creator creator) {
		super();
		this.id = creator.getId();
		this.name = creator.getName();
	}
	
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
