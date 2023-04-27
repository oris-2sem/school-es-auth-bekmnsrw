package com.example.school.security.util.impl;

import com.example.school.security.util.AuthHeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHeaderUtilImpl implements AuthHeaderUtil {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    public Boolean hasAuthToken(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(AUTH_HEADER);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public String getToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest
                .getHeader(AUTH_HEADER)
                .substring(BEARER.length());
    }
}
