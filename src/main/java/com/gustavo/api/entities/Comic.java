package com.gustavo.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_COMIC")
public class Comic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String title;
	private Float price;	
	private String isbn;
	
	@Column(length = 3000)
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_comic_creator",
		joinColumns = @JoinColumn(name = "comic_id"),
	    inverseJoinColumns = @JoinColumn(name = "creator_id"))
	private Set<Creator> creators = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_comic_user",
		joinColumns = @JoinColumn(name = "comic_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id"))	
	private List<User> users = new ArrayList<>();
	
	public Comic() {
		
	}

	public Comic(Integer id, String title, Float price, String isbn, String description) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		this.description = description;
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

	public Set<Creator> getCreators() {
		return creators;
	}

	public List<User> getUsers() {
		return users;
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

	public void setCreators(Set<Creator> creators) {
		this.creators = creators;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comic other = (Comic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}