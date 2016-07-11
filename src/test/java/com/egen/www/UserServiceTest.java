package com.egen.www;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();
	
	private User user;
	private Company company;
	private Address address;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		company = new Company();
		company.setName("Apple");
		company.setWebsite("https://www.apple.com");
		
		address = new Address();
		address.setStreet("abc");
		address.setCity("Indianapolis");
		address.setState("IN");
		address.setCountry("USA");
		address.setZipCode(12321);
		
		user = new User();
		user.setId("abab12b12bsas277fdsfsdf8tfsdasd");
		user.setFirstName("abcdhe");
		user.setLastName("dsadsa");
		user.setEmail("abc@123.com");
		user.setCompany(company);
		user.setDateCreated(new Date(0));
		user.setAddress(address);
		user.setProfilePic("someLink!!");
		user.setDone(true);
	}
	@Test
	public void createNewUser() throws InvalidUserException,Exception{
		Mockito.when(userService.find(user.getEmail())).thenReturn(null);
		user.setId("abcd");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(user);
		ObjectMapper mapper = new ObjectMapper();
		JsonObject jsonobject = mapper.readValue(json, JsonObject.class);
		userService.createNewUser(jsonobject);
		//
	}
	
	@Test(expected=InvalidUserException.class)
	public void createNewUserThrowsInvalidException() throws InvalidUserException,Exception{
		//verify the exception
	}

	
	@Test
	public void update() throws UserNotFoundException,InvalidUserException{
		//verify
	}
	@Test(expected=UserNotFoundException.class)
	public void updateThrowsUserNotFoundException() throws UserNotFoundException,InvalidUserException{
		//verify if exception
	}
}
