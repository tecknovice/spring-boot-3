package com.example.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    private UserDetails john = User.builder()
            .username("john")
            .password("{noop}password")
            .roles("EMPLOYEE")
            .build();

    private UserDetails marry = User.builder()
            .username("marry")
            .password("{noop}password")
            .roles("EMPLOYEE","MANAGER")
            .build();

    private UserDetails susan = User.builder()
            .username("susan")
            .password("{noop}password")
            .roles("EMPLOYEE","MANAGER","ADMIN")
            .build();

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        return new InMemoryUserDetailsManager(john,marry,susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.authorizeHttpRequests(configure ->configure
                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );

        //use http basic auth
        httpSecurity.httpBasic(Customizer.withDefaults());

        //disable CSRF (cross site request forgery)
        //in general, it is not required for stateless REST APIs that use POST, PUT, DELETE, PATCH
        httpSecurity.csrf(csrf->csrf.disable());

        return httpSecurity.build();
    }

}
