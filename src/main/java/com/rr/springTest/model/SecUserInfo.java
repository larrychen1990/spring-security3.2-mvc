package com.rr.springTest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Ross Romano
 */
public class SecUserInfo implements UserDetails 
{
	private static final long serialVersionUID = -6366285375746092772L;

	private UserInfo userInfo;
	
	public SecUserInfo()
	{
		
	}

	public SecUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		List<SimpleGrantedAuthority> auths = new ArrayList<>();
		//auths.add(new SimpleGrantedAuthority("USER_ROLE"));
		auths.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
		return auths;
	}

	@Override
	public String getPassword() 
	{
		return null;
	}

	@Override
	public String getUsername() 
	{
		return userInfo.getDn();
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return userInfo != null;
	}

	@Override
	public boolean isAccountNonLocked() {
		return userInfo != null;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "SecUserInfo [userInfo=" + userInfo + "]";
	}

}
