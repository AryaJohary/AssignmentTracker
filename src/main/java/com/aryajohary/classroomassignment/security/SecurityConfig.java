package com.aryajohary.classroomassignment.security;

import com.aryajohary.classroomassignment.schemas.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {
        http.authenticationProvider(authenticationProvider);
        http.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/students/**")
                                .hasAnyAuthority(String.valueOf(Role.Student),String.valueOf(Role.Teacher))

                                .requestMatchers("/teachers/**")
                                .hasAuthority(String.valueOf(Role.Teacher))

                                .requestMatchers("/assignments/{id}")
                                .hasAnyAuthority(String.valueOf(Role.Student),String.valueOf(Role.Teacher))

                                .requestMatchers("/submisssions/**")
                                .hasAuthority(String.valueOf(Role.Teacher))

                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.loginPage("/showLogin")
                                .loginProcessingUrl("authenticateTheUser")
                                .successForwardUrl("/")
                                .permitAll())
        ;

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        System.out.println("Authentication provider being called");
        return daoAuthenticationProvider;
    }

}
