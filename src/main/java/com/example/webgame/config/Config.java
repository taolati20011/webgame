package com.example.webgame.config;

import com.google.common.collect.ImmutableList;
import com.example.webgame.filter.JwtAuthenticationFilter;
import com.example.webgame.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config {
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/api/user/signin").permitAll()
                .antMatchers("/api/user/signup").permitAll()
                .antMatchers("/api/user/forgotpassword").permitAll()
                .antMatchers("/api/user/resetpassword").permitAll()
                .antMatchers("/api/user/authenticate").permitAll()
                .antMatchers("/api/user/login").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/api/type/**").permitAll()
                .antMatchers("/api/game/get-detail/**").permitAll()
                .antMatchers("/api/game/get-main-file").permitAll()
                .antMatchers("/api/game/get-cover-file").permitAll()
//                .antMatchers("/api/user/view-all").permitAll()
//                .antMatchers("/api/game/get-detail/*").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe()
                .rememberMeCookieName("remember-me")
                .tokenValiditySeconds(86400);

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

//        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

