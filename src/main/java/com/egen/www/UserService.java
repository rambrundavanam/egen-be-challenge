package com.egen.www;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Created by Ram on 07/09/16.
 */

public class UserService implements IUserService {
	private final DB db;
	private final DBCollection collection;

	public UserService(DB db) {
		this.db = db;
		this.collection = db.getCollection("users");
	}

	public UserService() {
		db = null;
		collection = null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		DBCursor dbObjects = collection.find();

		while (dbObjects.hasNext()) {

			DBObject dbObject = dbObjects.next();
			users.add(new User((BasicDBObject) dbObject));

		}
		return users;
	}

	@Override
	public User createNewUser(JsonObject json) throws InvalidUserException {

		User user = JSON.parseObject(json.toString(), User.class);
		if (this.find(user.getEmail()) != null) {
			throw new InvalidUserException();
		}
		else{
		Address address = new Address(user.getAddress().getStreet(), user.getAddress().getCity(),
				user.getAddress().getState(), user.getAddress().getCountry(), user.getAddress().getZipCode());
		
		Company company = new Company(user.getCompany().getName(), user.getCompany().getWebsite());

		collection.insert(new BasicDBObject("id", user.getId()).append("firstName", user.getFirstName())
				.append("lastName", user.getLastName()).append("email", user.getEmail()).append("address", address)
				.append("dateCreated", user.getDateCreated()).append("company", company)
				.append("profilePic", user.getProfilePic()));
		return this.find(user.getId());
		}
		
	}

	public User find(String id) {
		return new User((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
	}

	@Override
	public User update(JsonObject json, String userId) throws UserNotFoundException, InvalidUserException {

		User user = JSON.parseObject(json.toString(), User.class);

		collection.update(new BasicDBObject("_id", new ObjectId(userId)),
				new BasicDBObject("$set", new BasicDBObject("done", user.isDone())));
		user = this.find(userId);
		if (user != null) {
			return user;
		} else
			throw new InvalidUserException();
	}
}
