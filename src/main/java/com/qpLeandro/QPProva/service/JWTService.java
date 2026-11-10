package com.qpLeandro.QPProva.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    @Value("${app.jwt.secret}")
    private String secret;


    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret));
    }

    public String gerarToken(String email) {

        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret));

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + 180000))
                .signWith(key)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}