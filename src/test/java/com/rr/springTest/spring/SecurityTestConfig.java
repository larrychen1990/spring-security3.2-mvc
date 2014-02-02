package com.rr.springTest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.rr.springTest.auth.X509AuthProvider;
import com.rr.springTest.service.UserService;

public class SecurityTestConfig extends SecurityConfig 
{
	@Autowired
	public UserService userService;
	
	@Bean
	@Override
	public PreAuthenticatedAuthenticationProvider authenticationProvider() 
	{
		X509AuthProvider provider = new X509AuthProvider();
		provider.message = "Security Config Test";
		provider.setService(userService);
		return provider;
	}

}
