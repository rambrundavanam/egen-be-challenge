package com.egen.www;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public interface IUserService {

public User createNewUser(JsonObject json) throws InvalidUserException;

public User update(JsonObject json,String id) throws UserNotFoundException, InvalidUserException;

public List<User> findAll();

}
