package com.gustavo.api.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UserInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInsert {
	//mensagem emitida em caso de erro na validação
	String message() default "Erro de validação";
	
	//para utilização de grupo de validações de Bean Validation
	Class<?>[] groups() default {};
	
	//configura o grau do erro de validação
	Class<? extends Payload>[] payload() default {};
}

