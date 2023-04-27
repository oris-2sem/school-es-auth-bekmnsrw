package com.example.school.security.util;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthHeaderUtil {

    Boolean hasAuthToken(HttpServletRequest httpServletRequest);
    String getToken(HttpServletRequest httpServletRequest);
}
