package com.rest.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.rest.model.UserDtls;

public class CustomUserDetails implements UserDetails {

	@Autowired
	private UserDtls userDtls;
	
	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(UserDtls userDtls) {
		super();
		this.userDtls = userDtls;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		List<SimpleGrantedAuthority> asList = Arrays.asList(authority);
		return asList;
	}

	@Override
	public String getPassword() {
		return userDtls.getPassword();
	}

	@Override
	public String getUsername() {
		return userDtls.getUsername();
	}

}
