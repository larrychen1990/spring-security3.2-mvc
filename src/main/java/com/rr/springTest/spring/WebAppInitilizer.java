package com.rr.springTest.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Replaces the web.xml file, and sets up the Spring Security
 * filter chain.
 *  
 * @author Ross Romano
 */
public class WebAppInitilizer extends AbstractSecurityWebApplicationInitializer
{
	public WebAppInitilizer()
	{
		//super(RootConfig.class, WebConfig.class);
		//super(WebConfig.class);
		//super(SecurityConfig.class);
		//super(SecurityConfig.class, WebConfig.class);
		super(RootConfig.class, SecurityConfig.class, WebConfig.class);
	}

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) 
	{
		ServletRegistration.Dynamic servlet = servletContext.addServlet("DispatcherServlet", DispatcherServlet.class);
        servlet.setInitParameter("contextConfigLocation", "");
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	}

}

/*
public class WebAppInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() 
	{
		return new String[] { "/" };
	}

}
*/
