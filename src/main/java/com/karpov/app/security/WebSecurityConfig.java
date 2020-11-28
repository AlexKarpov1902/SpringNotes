package com.karpov.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.username}")
    String username;
    @Value("${app.password}")
    String password;
    @Value("${app.roles}")
    String roles;

    @Value("${app.username1}")
    String username1;
    @Value("${app.password1}")
    String password1;
    @Value("${app.roles1}")
    String roles1;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //                .authorizeRequests()
                //                .antMatchers("/", "/home").permitAll()
                //                .anyRequest().authenticated()
                //                .and()
                //                .formLogin()
                //                .loginPage("/login")
                //                .permitAll()
                //                .and()
                //                .logout()
                //                .permitAll();

                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user
                = User.withDefaultPasswordEncoder()
                        .username(username)
                        .password(password)
                        .roles(roles)
                        .build();

        UserDetails user1
                = User.withDefaultPasswordEncoder()
                        .username(username1)
                        .password(password1)
                        .roles(roles1)
                        .build();

        return new InMemoryUserDetailsManager(user, user1);
    }
}
