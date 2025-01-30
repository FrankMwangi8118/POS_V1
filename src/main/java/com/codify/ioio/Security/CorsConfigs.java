package com.codify.ioio.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
@Configuration
@Component
public class CorsConfigs {
    public CorsFilter corsFilter() {
        System.out.println("CORS configuration loaded");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedHeaders(List.of(
                "Origin", "Content-Type", "Accept", "Authorization",
                "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        config.setAllowedHeaders(Collections.singletonList("*"));

        config.setExposedHeaders(List.of("Authorization")); // Headers exposed to frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // Allowed HTTP methods
        source.registerCorsConfiguration("/**", config);
        System.out.println("CORS Configuration: " + config.toString());
        return new CorsFilter(source);
    }
}
