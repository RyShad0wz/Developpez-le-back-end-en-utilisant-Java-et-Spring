package com.rental.backend.config;

import com.rental.backend.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

  @Autowired
  private CorsConfigurationSource corsConfigurationSource;

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,
                        AuthenticationProvider authenticationProvider) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.authenticationProvider = authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.configurationSource(corsConfigurationSource))
      .authorizeHttpRequests(auth -> {
        // Autoriser l’accès public aux endpoints d’authentification et aux GET sur les utilisateurs et rentals
        auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
        auth.requestMatchers("/api/auth/register", "/api/auth/login").permitAll();
        auth.requestMatchers("/api/rentals/**", "/api/messages/**", "/api/user/**", "/api/auth/me").authenticated();
      })
      .authenticationProvider(authenticationProvider)
      // Ajout du filtre JWT
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }



  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
