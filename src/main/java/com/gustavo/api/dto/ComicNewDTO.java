package com.gustavo.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class ComicNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Min(value = 1, message = "idUser não pode ser menor que 1")
	private Integer idUser;
	
	@Min(value = 1, message = "idComicMarvel não pode ser menor que 1")
	private Integer idComicMarvel;
	
	public ComicNewDTO() {
		
	}

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdComicMarvel() {
		return idComicMarvel;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdComicMarvel(Integer idComicMarvel) {
		this.idComicMarvel = idComicMarvel;
	}
	
}
