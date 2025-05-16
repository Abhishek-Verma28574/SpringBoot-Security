package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rest.dto.UserRequest;
import com.rest.model.UserDtls;
import com.rest.repository.UserDtlsRepository;

@Service
public class UserDtlsServiceImpl implements UserDtlsService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDtlsRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public String login(UserRequest request) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		if(authenticate.isAuthenticated()) {
//			return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
			return jwtService.generateToken(request.getUsername()); 
		}
		return null;
	}

	@Override
	public List<UserDtls> getUserDtls() {
		return userRepository.findAll();
	}

}
