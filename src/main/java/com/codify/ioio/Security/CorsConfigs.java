package com.codify.ioio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
@Configuration
@Component
public class CorsConfigs {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
   CorsConfiguration configuration=new CorsConfiguration();
   configuration.setAllowedOrigins(List.of("http://localhost:5173"));
   configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
   configuration.setAllowedHeaders(List.of("Authorization","Content-type"));
   configuration.setAllowCredentials(true);
   configuration.setMaxAge(3600L);
   UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
   source.registerCorsConfiguration("/",configuration);
   return source;
    }
}
