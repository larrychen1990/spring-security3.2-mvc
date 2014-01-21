package com.rr.springTest.controllers;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rr.springTest.model.SecUserInfo;
import com.rr.springTest.model.UserInfo;

/**
 * Simple REST exmaple with JSR250 annontations.
 * 
 * For this example you need to modify the getAuthorities
 * method in {@link SecUserInfo} to change the roles
 * hence the access to the below methods.
 * 
 * @author Ross Romano
 */
@RestController
@RequestMapping(value="/users")
public class UserController 
{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public UserController()
	{
		logger.debug("Creating UserContoller...");
	}

	@RolesAllowed({"USER_ROLE"})
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	public @ResponseBody UserInfo getUser(SecUserInfo principal)
	{
		logger.info("Principal = " + principal);
		UserInfo info = new UserInfo();
		info.setDn("User");
		return info;
	}

	@RolesAllowed({"ADMIN_ROLE"})
	@RequestMapping(value = "/user/admin", method = RequestMethod.GET)
	public @ResponseBody UserInfo getUserAdmin(SecUserInfo principal)
	{
		logger.info("Principal = " + principal);
		UserInfo info = new UserInfo();
		info.setDn("Admin");
		return info;
	}
}
