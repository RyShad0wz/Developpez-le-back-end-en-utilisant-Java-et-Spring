package com.rental.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    // Autorise l'origine du front Angular
    config.setAllowedOrigins(List.of("http://localhost:4200"));
    // Autorise les méthodes nécessaires
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    // Autorise tous les headers
    config.setAllowedHeaders(List.of("*"));
    // Autorise l'envoi de credentials (ex. cookies) si besoin
    config.setAllowCredentials(true);

    // Applique cette config à toutes les routes
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}

