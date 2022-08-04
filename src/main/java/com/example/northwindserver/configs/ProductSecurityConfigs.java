package com.example.northwindserver.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@Configuration
public class ProductSecurityConfigs extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Yanaki")
                .password("{noop}1234")
                .authorities("USER");
        auth.inMemoryAuthentication()
                .withUser("Piotr")
                .password("{noop}google")
                .authorities("USER");
        auth.inMemoryAuthentication()
                .withUser("Neil")
                .password("{noop}123456")
                .authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product/delete/*").hasAnyAuthority("ADMIN")
                .antMatchers("/addNewProduct").hasAnyAuthority("ADMIN")
                .antMatchers("/product/edit/*").hasAnyAuthority("ADMIN")

                .antMatchers("/basket/delete/*").hasAnyAuthority("USER")
                .antMatchers("/basket/show/").hasAnyAuthority("USER")
                .antMatchers("/product/add/*").hasAnyAuthority("USER")

                .antMatchers("/product/all").permitAll()
                .antMatchers("/welcome*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome", true)
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }
}