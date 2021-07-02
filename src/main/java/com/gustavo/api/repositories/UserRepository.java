package com.gustavo.api.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Transactional(readOnly=true)
	User findByEmail(String email);
	
}
