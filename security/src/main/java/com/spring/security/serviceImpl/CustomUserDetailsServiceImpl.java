package com.spring.security.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.UserPrincipal;
import com.spring.security.model.Users;
import com.spring.security.repository.UsersRepository;
import com.spring.security.service.CustomUserDetailsService;
import com.spring.security.util.SecurityConfig;


@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	
	@Autowired
	private UsersRepository userRepo;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		Users user = userRepo.getByUserName(username);
		
		if(user==null) {
			logger.info("User Not Found : " +username);
			
			throw new 	UsernameNotFoundException(username);
		}else{
			logger.info("User Found");
			logger.info(user.toString());
			
		}
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		
		return userPrincipal;
	}

}
