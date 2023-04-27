package com.example.school.security.filter;

import com.example.school.security.auth.RefreshTokenAuth;
import com.example.school.security.details.UserDetailsImpl;
import com.example.school.security.util.AuthHeaderUtil;
import com.example.school.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USER_PARAMETER = "login";
    private static final String CONTENT_TYPE = "application/json";
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final AuthHeaderUtil authHeaderUtil;

    public JwtAuthenticationFilter(
            AuthenticationConfiguration authenticationConfiguration,
            ObjectMapper objectMapper,
            JwtUtil jwtUtil,
            AuthHeaderUtil authHeaderUtil
    ) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USER_PARAMETER);
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.authHeaderUtil = authHeaderUtil;
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        response.setContentType(CONTENT_TYPE);

        GrantedAuthority grantedAuthority = authResult
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow();

        String login = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        String issuer = request.getRequestURL().toString();

        Map<String, String> tokens = jwtUtil.generateTokens(login, grantedAuthority.getAuthority(), issuer);

        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        if (authHeaderUtil.hasAuthToken(request)) {
            String refreshToken = authHeaderUtil.getToken(request);
            RefreshTokenAuth refreshTokenAuth = new RefreshTokenAuth(refreshToken);
            return super.getAuthenticationManager().authenticate(refreshTokenAuth);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
