package com.gustavo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.api.entities.Author;
import com.gustavo.api.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author findByName(String name) {
		return authorRepository.findByName(name);
	}
	
}




