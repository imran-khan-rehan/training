package com.redmath.lec_3.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableMethodSecurity
@Configuration
public class ApiSecurityConfiguration {
    @Value("${api.security.ignored}")
    private String[] ignored;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return web -> {
         //   web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/v1"));

            web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/index.html"));
            web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/employees"));

            for (String loc:ignored)
            {
                web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,loc));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(config -> config
                // .requestMatchers(HttpMethod.POST, "/api/v1/news").hasAnyAuthority("admin",
                // "reporter")
                // .requestMatchers(HttpMethod.PUT, "/api/v1/news/**").hasAnyAuthority("admin",
                // "editor")
                .anyRequest().authenticated());
//        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()));
        http.csrf(csrf -> csrf.disable());  // Disable CSRF protection

        return http.build();
    }
}
