package com.example.Login.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http)throws Exception{
    http
         .authorizeRequests()
               .antMatchers("/","/home").permitAll()
               .antMatchers("/hello").access("hasRole('USER')")
               .antMatchers("/admin").access("hasRole('ADMIN')")
               .anyRequest().authenticated()
               .and()
         .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
         .logout()
                .permitAll();
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder  auth) throws Exception{
    auth
        .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    auth
        .inMemoryAuthentication()
                .withUser("admin").password("root123").roles("ADMIN");
  }
}
