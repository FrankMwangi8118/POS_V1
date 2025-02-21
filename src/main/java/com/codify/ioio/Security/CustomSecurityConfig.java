package com.codify.ioio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                )
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/login").permitAll();
                    registry.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();

                    registry.anyRequest().authenticated();
//                    registry.requestMatchers("/stock").permitAll();
//                    registry.requestMatchers("/baseController").authenticated();
//                    registry.requestMatchers("/filterSales").authenticated();
//                    registry.requestMatchers("/filterPayments").authenticated();

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
