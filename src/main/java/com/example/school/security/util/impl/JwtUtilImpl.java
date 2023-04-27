package com.example.school.security.util.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.school.model.UserCredentials;
import com.example.school.model.util.Role;
import com.example.school.security.details.UserDetailsImpl;
import com.example.school.security.util.JwtUtil;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtilImpl implements JwtUtil {

    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;

    @Value("${access-token.expires-at}")
    private long ACCESS_TOKEN_EXPIRES_AT;

    @Value("${refresh-token.expires-at}")
    private long REFRESH_TOKEN_EXPIRES_AT;

    public ParsedToken parseToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT
                .require(Algorithm.HMAC256(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return ParsedToken.builder()
                .login(decodedJWT.getSubject())
                .authority(decodedJWT.getClaim("authority").asString())
                .build();
    }

    @Override
    public Map<String, String> generateTokens(String subject, String authority, String issuer) {
        String accessToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES_AT))
                .withClaim("authority", authority)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8)));

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES_AT))
                .withClaim("authority", authority)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8)));

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    @Override
    public Authentication buildAuthentication(String token) throws JWTVerificationException {
        ParsedToken parsedToken = parseToken(token);

        UserDetails userDetails = new UserDetailsImpl(
                UserCredentials.builder()
                        .login(parsedToken.getLogin())
                        .role(Role.valueOf(parsedToken.getAuthority()))
                        .build()
        );

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                Collections.singleton(new SimpleGrantedAuthority(parsedToken.getAuthority()))
        );
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ParsedToken {
        private String login;
        private String authority;
    }
}
