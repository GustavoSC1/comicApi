package com.gustavo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.api.entities.Creator;
import com.gustavo.api.repositories.CreatorRepository;

@Service
public class CreatorService {
	
	@Autowired
	private CreatorRepository creatorRepository;
	
	public Creator findByName(String name) {
		return creatorRepository.findByName(name);
	}
	
}
