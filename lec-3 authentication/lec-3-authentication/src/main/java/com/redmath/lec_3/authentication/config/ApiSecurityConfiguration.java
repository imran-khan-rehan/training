package com.redmath.lec_3.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ApiSecurityConfiguration {
    @Value("${api.security.ignored}")
    private String[] ignored;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return web -> {
            for (String loc:ignored)
            {
                web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,loc));
            }
        };
    }

}
