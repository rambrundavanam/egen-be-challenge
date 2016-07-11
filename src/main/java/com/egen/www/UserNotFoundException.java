package com.egen.www;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User doesn't present in the db")
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

}
