package com.java26.eolt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /*  @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
                .withUser("user").password("123").roles("USER")
                .and()
                .withUser("tester").password("123").roles("USER","ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/", "/eolt").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
