package com.rr.springTest.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

import com.rr.springTest.model.SecUserInfo;
import com.rr.springTest.model.UserInfo;
import com.rr.springTest.service.UserService;

/**
 * Normally this class would do validation of the cert and ask
 * a user details service to get information about a user using
 * only the name/DN that is being passed around as the principle
 * in the current token.  So if you want/need more information from
 * the cert and or the request to make this decision then you need
 * to create your own version of the {@link X509AuthenticationFilter}
 * and override the getPreAuthenticatedPrincipal method to get whatever
 * you need and return it so that it ends up in the {@link Authentication}
 * object that is passed to this class.
 * 
 * @author Ross Romano
 */
public class X509AuthProvider extends PreAuthenticatedAuthenticationProvider 
{
	private static Logger logger = LoggerFactory.getLogger(X509AuthProvider.class);
	public String message;
	private UserService service;
	
	@Override
	public void afterPropertiesSet() {
	}

	/**
	 * At any point that you want the login to fail simply 
	 * return null or throw an {@link AuthenticationException}.
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException 
	{
		if (!supports(authentication.getClass())) {
            return null;
        }

		logger.info("In X509AuthProvider, configured message is: " + message);

        if (logger.isDebugEnabled()) {
            logger.debug("PreAuthenticated authentication request: " + authentication);
        }

        if (authentication.getPrincipal() == null) {
            logger.debug("No pre-authenticated principal found in request.");
            return null;
        }

        /* Commented out for testing.
        if (authentication.getCredentials() == null) {
            logger.debug("No pre-authenticated credentials found in request.");
            return null;
        }
        */

        /*
         * NOTE: should get the user info from somewhere else, or do some
         * real login logic.
         * 
         * You could use the default:
         * 
         * UserDetails ud = preAuthenticatedUserDetailsService.loadUserDetails((PreAuthenticatedAuthenticationToken)authentication)
         * 
         * call to get it.
         */
		//SecUserInfo ud = new SecUserInfo((UserInfo)authentication.getPrincipal());
		SecUserInfo ud = service.getUser((UserInfo)authentication.getPrincipal());
		
		/*
		 * Do some other validity checks
		 * 
		 * userDetailsChecker.check(ud);
		 */

		/*
		 * Create the auth token to continue the filter chain with.
		 */
		PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(ud, 
                						authentication.getCredentials(), 
                						ud.getAuthorities());
        result.setDetails(authentication.getDetails());
		return result;
	}

	public void setService(UserService service) {
		this.service = service;
	}

}
