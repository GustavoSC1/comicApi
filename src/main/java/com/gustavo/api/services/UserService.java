package com.gustavo.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.api.dto.UserNewDTO;
import com.gustavo.api.entities.User;
import com.gustavo.api.repositories.UserRepository;
import com.gustavo.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User find(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"User não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	public User insert(UserNewDTO objDto) {
		User user = new User(null, objDto.getName(), objDto.getEmail(), 
				objDto.getCpf(), objDto.getBirthDate());
		
		user = userRepository.save(user);
		return user;
	}	

}




