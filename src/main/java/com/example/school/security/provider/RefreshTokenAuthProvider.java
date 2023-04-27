package com.example.school.security.provider;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.school.security.auth.RefreshTokenAuth;
import com.example.school.security.exceptions.RefreshTokenException;
import com.example.school.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenAuthProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            return jwtUtil.buildAuthentication((String) authentication.getCredentials());
        } catch (JWTVerificationException e) {
            throw new RefreshTokenException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RefreshTokenAuth.class.isAssignableFrom(authentication);
    }
}
