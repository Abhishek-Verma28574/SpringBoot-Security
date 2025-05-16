package com.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.UserRequest;
import com.rest.model.UserDtls;
import com.rest.service.UserDtlsService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HomeController {
	
	@Autowired
	private UserDtlsService userService;

	@GetMapping("/")
	public ResponseEntity<?> home(){
		return new ResponseEntity<>("Hello, DashBoard", HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDetails(HttpServletRequest request){
		
		return new ResponseEntity<>(userService.getUserDtls(), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
		
		String token = userService.login(userRequest);
		
		if(token == null) {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
}
