package com.finoba.finoba.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indicamos que esta clase define configuraciones para el contexto de Spring
@EnableWebSecurity // Habilita la seguridad web en Spring Security
@EnableMethodSecurity(prePostEnabled = true) // Permite usar anotaciones como @PreAuthorize y @Secured a nivel de métodos
@RequiredArgsConstructor // Genera un constructor con todos los campos marcados como final
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
