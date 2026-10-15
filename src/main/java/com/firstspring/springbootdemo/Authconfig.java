package com.firstspring.springbootdemo;

import com.firstspring.springbootdemo.service.CustomOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class Authconfig {
    @Autowired
    private CustomOidcUserService customOidcUserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/oauth2/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/quizapp/**",
                                "/signup/**",
                                "/About-us/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/quizapp", true)
                        .userInfoEndpoint(userInfo ->
                                                userInfo.oidcUserService(customOidcUserService)
                                        )
                );

        return http.build();
    }
}