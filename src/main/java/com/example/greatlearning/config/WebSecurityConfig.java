package com.example.greatlearning.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // JDBC authentication, method chaining
        auth.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery("SELECT username, password, enabled from users where username = ?").
                authoritiesByUsernameQuery("SELECT username, role from users where username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/users/*").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().permitAll();
    }

    //	If you don't want to encode the created password, you can write the below bean method, FYI: not recommended for Prod env
    @Bean
    public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");

    }

}