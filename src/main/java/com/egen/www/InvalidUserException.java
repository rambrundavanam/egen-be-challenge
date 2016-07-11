package com.egen.www;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid data")
public class InvalidUserException extends Exception {
	private static final long serialVersionUID = 1L;
}