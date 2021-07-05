package com.gustavo.api.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.gustavo.api.entities.Comic;

public class ComicDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private Float price;	
	private String isbn;
	private String description;
	private boolean descontoAtivo;
	
	private Set<CreatorDTO> creators = new HashSet<>();
	
	public ComicDTO() {
		
	}

	public ComicDTO(Comic comic) {
		super();
		this.id = comic.getId();
		this.title = comic.getTitle();
		this.price = comic.getPrice();
		this.isbn = comic.getIsbn();
		this.description = comic.getDescription();
		
		if(checkDiscount()) {
			Double percentual = 10.0 / 100.0; 
			Double newValue = this.price - (percentual * this.price);
			
			this.descontoAtivo = true;
			this.price = newValue.floatValue();			
		}
	}
		
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Float getPrice() {
		return price;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescription() {
		return description;
	}

	public boolean getDescontoAtivo() {
		return descontoAtivo;
	}

	public Set<CreatorDTO> getCreators() {
		return creators;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreators(Set<CreatorDTO> creators) {
		this.creators = creators;
	}
	
	private boolean checkDiscount() {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		char lastNumber = this.isbn.charAt(this.isbn.length() - 1);
		
		if ((lastNumber == '0' || lastNumber == '1') && day == 2)  {
			return true;
		} else if ((lastNumber == '2' || lastNumber == '3') &&  day == 3) {
			return true;
		} else if (( lastNumber == '4' || lastNumber == '5') && day == 4) {
			return true;
		} else if ((lastNumber == '6' || lastNumber == '7') && day == 5) {
			return true;
		} else if ((lastNumber == '8' || lastNumber == '9') && day == 6) {
			return true;
		}
		
		return false;
	}
	
}
