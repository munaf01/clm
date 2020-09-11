package com.inspire.clm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);

        UserDetails user1 = userBuilder
                .username("user1")
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = userBuilder
                .username("user2")
                .password("password")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/customers/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/customers/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")
                .and()
                .csrf()
                .disable()
                .formLogin();

                //.anyRequest()
                //.permitAll();
    }
}
