package com.gustavo.api.services.exceptions;

public class ApiAttributeNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ApiAttributeNullException(String msg) {
		super(msg);
	}
	
	public ApiAttributeNullException(String msg, Throwable cause) {
		super(msg, cause);
	}

}