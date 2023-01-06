package com.rmendez.expenses.backend.api.config;

import com.rmendez.expenses.backend.api.services.JpaUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig {

    private final JpaUserDetailService jpaUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(jpaUserDetailService).httpBasic()
                .and()
                .authorizeRequests(auth -> auth
                        .mvcMatchers(HttpMethod.POST, "/users").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/").permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf().ignoringAntMatchers("/users/**", "/expenses/**")
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
