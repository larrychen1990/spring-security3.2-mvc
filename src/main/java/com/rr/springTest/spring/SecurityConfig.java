package com.rr.springTest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.context.NullSecurityContextRepository;

import com.rr.springTest.auth.X509AuthProvider;
import com.rr.springTest.service.UserService;
import com.rr.springTest.spring.configurers.X509Configurer;

/**
 * Configuration for spring security.
 *  
 * @author Ross Romano
 */
@Configuration
@EnableWebSecurity
//@Import(WebConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	public UserService userService;
	
	public SecurityConfig()
	{
		super(true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception 
	{
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PreAuthenticatedAuthenticationProvider authenticationProvider() 
	{
		X509AuthProvider provider = new X509AuthProvider();
		provider.message = "Security Config";
		provider.setService(userService);
		return provider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception 
	{
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests().antMatchers("/**").fullyAuthenticated();
		//http.authorizeRequests().antMatchers("/foo/**").fullyAuthenticated();
		
		X509Configurer<HttpSecurity> x509 = new X509Configurer<>();
		x509.authenticationProvider(authenticationProvider())
			.subjectPrincipalRegex("CN=(.*?),");
		
		http.securityContext()
			.securityContextRepository(new NullSecurityContextRepository()).and()
			.exceptionHandling().and()
			.apply(x509);
	}
	
	
}
