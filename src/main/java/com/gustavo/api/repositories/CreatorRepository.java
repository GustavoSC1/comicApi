package com.gustavo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.api.entities.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Integer> {
	
	@Transactional(readOnly=true)
	Creator findByName(String name);

}