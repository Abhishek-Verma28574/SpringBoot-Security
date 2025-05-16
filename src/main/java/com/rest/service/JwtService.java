package com.rest.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "";
	
	public JwtService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);
		
		String token = Jwts.builder()
		    .claims()
		    .add(claims)
		    .subject(username)
		    .issuedAt(new Date(System.currentTimeMillis()))
		    .expiration(new Date(System.currentTimeMillis()+60*60*10))
            .and()
            .signWith(getKey())
            .compact();
            
		return token;
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	private Claims extractAllClaims(String token) {
		Claims claims = Jwts.parser().verifyWith(decryptKey(secretKey)).build()
		             .parseSignedClaims(token)
		             .getPayload();
		
		return claims;
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimReslover){
		Claims claims = extractAllClaims(token);
		return claimReslover.apply(claims);
	}

	private SecretKey decryptKey(String secretkey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		
		String username = extractUsername(token);
		boolean isExpired = isTokenExpired(token);
		
		if(username.equals(userDetails.getUsername()) && !isExpired) {
			return true;
		}
		
		return false;
	}

	private boolean isTokenExpired(String token) {
		Date expireDate = extractExpiration(token);
		return expireDate.before(new Date());
	}
}
