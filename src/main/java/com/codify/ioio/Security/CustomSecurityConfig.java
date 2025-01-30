package com.codify.ioio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {
    private final UserDetailsService userDetailsService;

    public CustomSecurityConfig(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(Customizer->Customizer.disable())


                .formLogin(FormLoginConfigurer->{
                    FormLoginConfigurer.disable();
                })


                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER)

                )
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/api/pos/v1/security/**").permitAll();
                    registry.requestMatchers("/home").permitAll();
                    registry.requestMatchers("/payment").permitAll();
                    registry.requestMatchers("/filtering").permitAll();
                    registry.requestMatchers("/names").authenticated();
                    registry.anyRequest().authenticated();
                }).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
      return   new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;

    }
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(daoAuthenticationProvider());
    }
}
