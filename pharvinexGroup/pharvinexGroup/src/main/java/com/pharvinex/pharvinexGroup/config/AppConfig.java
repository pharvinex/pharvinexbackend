package com.pharvinex.pharvinexGroup.config;

import com.pharvinex.pharvinexGroup.user.CustomeUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.security.PublicKey;

@Configuration
public class AppConfig {

    private final CustomeUserDetailService customUserDetailsService;

    // Constructor to inject the custom UserDetailsService
    public AppConfig(CustomeUserDetailService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCryptPasswordEncoder for password encoding
    }

    // Use the custom UserDetailsService (which loads user from the database) instead of InMemoryUserDetailsManager
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;  // Return the custom UserDetailsService
    }

    // Configure the AuthenticationManager to use the AuthenticationConfiguration bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}