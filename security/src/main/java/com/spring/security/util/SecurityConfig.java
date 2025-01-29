package com.spring.security.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private CustomUserDetailsService userService;
	
	 @Autowired
	    private JwtFilter jwtFilter;

	
	  @Bean
	  public SecurityFilterChain securityFilter(HttpSecurity http) throws	Exception {
	  
	  http
	  	.csrf(cus -> cus.disable())
	  	.authorizeHttpRequests(cus ->cus
			  .requestMatchers("/login","/register").permitAll()
			  .anyRequest().authenticated())
	  	.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
	  //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	  
	  ;
	  
	  
	  return http.build();
	  
	 }
	 

	

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userService);

		return provider;

	}
	
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		
		
		return config.getAuthenticationManager();
		
		
		
	}
}
