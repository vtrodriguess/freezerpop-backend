package com.geladinho.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.geladinho.entities.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class Token {

	private static final Key KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

	public String generateToken(Customer c) {
		Long id = c.getId();
		String email = c.getEmail();

		return Jwts.builder().setSubject(String.valueOf(id)).claim("email", email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).signWith(KEY).compact();

	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String extractEmail(String token) {
		return extractAllClaims(token).get("email", String.class); // retorna o email
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
	}
}
