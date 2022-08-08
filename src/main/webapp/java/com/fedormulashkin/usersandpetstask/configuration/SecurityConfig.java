package com.fedormulashkin.usersandpetstask.configuration;

import com.fedormulashkin.usersandpetstask.component.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Класс для конфигурации Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final DataSource dataSource;
    @Autowired
    private AuthProvider authProvider;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().httpBasic()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/registration/new").permitAll()
                .antMatchers(HttpMethod.GET, "/api/registration/check/**").permitAll()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/users/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER")
                .anyRequest().rememberMe()
                .and()
                .formLogin().loginProcessingUrl("/api/users/user").permitAll()
                .defaultSuccessUrl("/api/users/user")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }
}
