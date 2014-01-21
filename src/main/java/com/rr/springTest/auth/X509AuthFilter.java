package com.rr.springTest.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

import com.rr.springTest.model.UserInfo;

/**
 * Sample X509 servlet filter to replace the default.
 * <br/><br/>
 * Really the only time you should ever have to do this would
 * be if you needed to get more then just the DN from the
 * HttpServletRequest.  
 * <br/><br/>
 * NOTE: this is not normal to replace this, I was only 
 * curious how I could do it.
 *  
 * @author Ross Romano
 */
public class X509AuthFilter extends X509AuthenticationFilter 
{

	/**
	 * In a real application you could use this hook to get
	 * whatever you need from the request and create your
	 * Principle.  Now spring security by default treats what is
	 * returned here as a string by default, so if you want to store
	 * other things then you need to create it and handle it yourself
	 * later in a provider.
	 */
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) 
	{
		UserInfo ui = new UserInfo();
		ui.setDn("DN=TEST TEST TEST");
		return ui;
	}

}
