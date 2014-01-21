package com.rr.springTest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rr.springTest.model.SecUserInfo;
import com.rr.springTest.model.UserInfo;
import com.rr.springTest.service.UserService;

public class UserServiceImpl implements UserService 
{
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public SecUserInfo getUser(UserInfo info) {
		logger.info("In getUser");
		return new SecUserInfo(info);
	}

}
