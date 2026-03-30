package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtTokenProvider {
    
    // Fixed secret key - stored as Base64 so it remains consistent across application restarts
    private static final String SECRET = "mySecretKeyForJWTTokenGenerationAndValidationPurpose123456789";
    private final SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET.getBytes()));
    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    
    public String generateToken(String username) {
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }
    
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
