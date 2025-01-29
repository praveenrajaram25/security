package com.spring.security.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.Users;
import com.spring.security.service.UserService;

@RestController
public class SecurityController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/")
	public String getData() {
		return "Spring Security";
	}
	
	@GetMapping("/users")
	public List<Users> getUsers(){
		return userService.getUserData();
		
	}
	
	@PostMapping("/register")
	public Users createUser(@RequestBody Users user){
		return userService.registerUser(user);
		
	}
	
	@PostMapping("/loginForm")
	public String login(@RequestBody Users user) {
		return userService.validateUser(user);
	}

}
