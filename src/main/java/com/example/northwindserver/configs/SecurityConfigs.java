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
public class SecurityConfigs extends WebSecurityConfigurerAdapter {
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
                // ONLY ADMINS HAVE ACCESS
                .antMatchers("/product/delete/*").hasAnyAuthority("ADMIN")
                .antMatchers("/product/add").hasAnyAuthority("ADMIN")
                .antMatchers("/product/edit/*").hasAnyAuthority("ADMIN")
                .antMatchers("/customer/delete/*").hasAnyAuthority("ADMIN")
                .antMatchers("/customer/add").hasAnyAuthority("ADMIN")
                .antMatchers("/customer/edit/*").hasAnyAuthority("ADMIN")
                .antMatchers("/order/delete/*").hasAnyAuthority("ADMIN")
                .antMatchers("/updateOrder").hasAnyAuthority("ADMIN")
                .antMatchers("/order/edit/*").hasAnyAuthority("ADMIN")
                // ONLY USERS HAVE ACCESS
                .antMatchers("/basket/delete/*").hasAnyAuthority("USER")
                .antMatchers("/basket/show/").hasAnyAuthority("USER")
                .antMatchers("/basket/add/*").hasAnyAuthority("USER")
                // EVERYONE HAS ACCESS
                .antMatchers("/product/all").permitAll()
                .antMatchers("/welcome*").permitAll()
                .antMatchers("/orders/all").permitAll()
                .antMatchers("/customer/all").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome", true)
                .permitAll()
                .and().logout()
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }

}