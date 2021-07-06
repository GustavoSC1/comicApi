package com.gustavo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.api.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	@Transactional(readOnly=true)
	Author findByName(String name);

}