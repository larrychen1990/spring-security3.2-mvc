package com.rr.springTest.service;

import com.rr.springTest.model.SecUserInfo;
import com.rr.springTest.model.UserInfo;

public interface UserService 
{
	SecUserInfo getUser(UserInfo info);
}
