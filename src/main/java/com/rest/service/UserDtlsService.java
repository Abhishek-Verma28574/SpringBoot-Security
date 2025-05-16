package com.rest.service;

import java.util.List;

import com.rest.dto.UserRequest;
import com.rest.model.UserDtls;

public interface UserDtlsService {

	public String login(UserRequest request);
	
	public List<UserDtls> getUserDtls();
	
}
