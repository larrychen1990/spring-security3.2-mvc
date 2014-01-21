package com.rr.springTest.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.rr.springTest.auth.X509AuthProvider;

public class SecurityTestConfig extends SecurityConfig 
{
	@Bean
	@Override
	public PreAuthenticatedAuthenticationProvider authenticationProvider() 
	{
		X509AuthProvider provider = new X509AuthProvider();
		provider.message = "Security Config Test";
		return provider;
	}

}
