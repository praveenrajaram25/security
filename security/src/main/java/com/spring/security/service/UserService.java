package com.spring.security.service;

import java.util.List;

import com.spring.security.model.Users;

public interface UserService {

	
	public List<Users> getUserData();

	public Users registerUser(Users user);

	public String validateUser(Users user);
}
