package com.rr.springTest.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rr.springTest.service.UserService;
import com.rr.springTest.service.impl.UserServiceImpl;

@Configuration
public class RootConfig 
{
	@Bean
	public UserService userService()
	{
		return new UserServiceImpl();
	}
}
