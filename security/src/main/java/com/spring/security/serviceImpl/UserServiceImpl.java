package com.spring.security.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.model.Users;
import com.spring.security.repository.UsersRepository;
import com.spring.security.service.UserService;
import com.spring.security.util.JWTConfig;
import com.spring.security.util.SecurityConfig;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTConfig jwt;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	@Override
	public List<Users> getUserData() {

		return userRepo.findAll();
	}

	@Override
	public Users registerUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public String validateUser(Users user) {
		
		
		Authentication auth = (Authentication) authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		
		if(auth.isAuthenticated()) {
			
			String token = jwt.generateToken(user.getUserName());
			logger.info("JWT Token : "+token);
			return token;
		}else {
			return "Fail";
		}
		
	}

}
