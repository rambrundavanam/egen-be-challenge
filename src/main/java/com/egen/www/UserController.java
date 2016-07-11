package com.egen.www;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody JsonObject user) throws InvalidUserException{
		return userService.createNewUser(user);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAllUsers(){
		return userService.findAll();
	}
	@RequestMapping(value="/{userId}", method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@PathVariable("userId") String userId ,@RequestBody JsonObject user) throws UserNotFoundException, InvalidUserException{
		return userService.update(user, userId);
	}
}
