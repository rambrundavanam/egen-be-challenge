package com.egen.www;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

/**
 * Created by Ram on 07/09/16.
 */
public class UserResource {
	private static final String API_CONTEXT = "/api/v1";
	private final UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
		setUpEndPoints();
	}

	private void setUpEndPoints() {
		post(API_CONTEXT + "/users", "application/json", (request, response) -> {
			ObjectMapper mapper = new ObjectMapper();
			JsonObject json = mapper.readValue(request.body(), JsonObject.class);
			userService.createNewUser(json);
			response.status(201);
			return response;
		}, new JsonTransformer());

		get(API_CONTEXT + "/users/:id", "application/json",
				(request, response) -> userService.find(request.params(":id")), new JsonTransformer());

		get(API_CONTEXT + "/users", "application/json", (request, response)

		-> userService.findAll(), new JsonTransformer());

		put(API_CONTEXT + "/users/:id", "application/json", (request, response)

		-> userService.update(new ObjectMapper().readValue(request.body(), JsonObject.class), request.params(":id")),
				new JsonTransformer());
	}
}
