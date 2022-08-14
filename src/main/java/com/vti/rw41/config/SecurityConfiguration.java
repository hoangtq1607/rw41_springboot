package com.vti.rw41.config;

import com.vti.rw41.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    @Primary
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable() // de tranh loi 403
                .cors().disable() // de web goi duoc API
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(
                                "/swagger-ui/**",
                                "/accounts/login",
                                "/accounts/register",
                                "/v3/api-docs",
                                "/v3/api-docs/**").permitAll() //cho phep cac URL cos pattern nhu tren truy cap ma khong can authentication
                        .antMatchers(HttpMethod.GET, "/products", "/products/**").permitAll()
                        //cho phep cac URL cos pattern nhu tren voi method GET truy cap ma khong can authentication
                        .anyRequest().authenticated() // cac URL con lai phai xac thuc (authentication)
                ).httpBasic(Customizer.withDefaults());

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
