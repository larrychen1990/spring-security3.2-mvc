package com.rr.springTest.spring;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import com.rr.springTest.spring.resolvers.SecUserArgumentResolver;

/**
 * Configures Spring MVC
 * 
 * @author Ross Romano
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages="com.rr.springTest.controllers")
//@Import(SecurityConfig.class)
//public class WebConfig extends WebMvcConfigurerAdapter 
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class WebConfig extends WebMvcConfigurationSupport 
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) 
	{
		configurer.defaultContentType(MediaType.APPLICATION_XML)
				  .mediaType("xml", MediaType.APPLICATION_XML)
				  .mediaType("json", MediaType.APPLICATION_JSON)
				  .useJaf(false);
	}
	
	public BeanNameUrlHandlerMapping beanNameHandlerMapping() { return null; }

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) 
	{
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new SecUserArgumentResolver());
	}
	
}
