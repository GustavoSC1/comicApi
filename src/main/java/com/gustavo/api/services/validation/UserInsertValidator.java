package com.gustavo.api.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gustavo.api.dto.UserNewDTO;
import com.gustavo.api.entities.User;
import com.gustavo.api.repositories.UserRepository;
import com.gustavo.api.controllers.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void initialize(UserInsert ann) {		
	}

	@Override
	public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		User auxEmail = repo.findByEmail(objDto.getEmail());
		if (auxEmail != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		User auxCpf = repo.findByCpf(objDto.getCpf());
		if (auxCpf != null) {
			list.add(new FieldMessage("cpf", "CPF já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty(); 
	}

}
