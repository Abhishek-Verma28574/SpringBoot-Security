package com.rest.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// authorization = Bearer shjkfhjkshdf.jsfjhsd.sknfdjnsdf
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		String username = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
		}
	}

}
