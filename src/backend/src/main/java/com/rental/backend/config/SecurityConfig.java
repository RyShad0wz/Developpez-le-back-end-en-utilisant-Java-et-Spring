package com.rental.backend.config;

import com.rental.backend.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        // On autorise l'accès public aux endpoints d'authentification
        auth.requestMatchers("/auth/register", "/auth/login", "/api/rentals/**").permitAll();
        auth.requestMatchers("/auth/me").permitAll();
        auth.requestMatchers(HttpMethod.POST, "/api/rentals").authenticated();
        auth.anyRequest().authenticated();
      })
      .authenticationProvider(authenticationProvider)
      // On place le filtre JWT avant UsernamePasswordAuthenticationFilter
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
