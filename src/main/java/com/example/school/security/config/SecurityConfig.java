package com.example.school.security.config;

import com.example.school.model.util.Role;
import com.example.school.security.filter.JwtAuthenticationFilter;
import com.example.school.security.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsServiceImpl;
    private final AuthenticationProvider refreshTokenAuthProvider;
    private static final String LOGOUT_ENDPOINT = "/logout";
    private static final String LOGOUT_HTTP_METHOD = "GET";

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JwtAuthorizationFilter jwtAuthorizationFilter
    ) throws Exception {
        httpSecurity.csrf()
                .disable();

        jwtAuthenticationFilter.setFilterProcessesUrl(JwtAuthorizationFilter.AUTH_PATH);

        httpSecurity.addFilter(jwtAuthenticationFilter);

        httpSecurity.addFilterBefore(
                jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeHttpRequests((customizer) ->
                customizer
                        .requestMatchers(JwtAuthorizationFilter.AUTH_PATH).permitAll()
                        .requestMatchers(HttpMethod.POST, "/students", "/teachers", "/parents").permitAll()
                        .requestMatchers(HttpMethod.GET, "/students/**", "/teachers/**", "/tasks/**", "/timetablelines/**", "/parents/**", "/clazzes/**", "/lessons/**").authenticated()
                        .requestMatchers("/parents/**").hasAuthority(String.valueOf(Role.PARENT))
                        .requestMatchers("/teachers/**", "/tasks/**", "/timetablelines/**", "/parents/**", "/lessons/**", "/students/**", "/clazzes/**").hasAuthority(String.valueOf(Role.TEACHER))
                        .anyRequest().authenticated()
        );

        httpSecurity.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_ENDPOINT, LOGOUT_HTTP_METHOD))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(
            AuthenticationManagerBuilder builder
    ) throws Exception {
        builder
                .authenticationProvider(refreshTokenAuthProvider)
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }
}
