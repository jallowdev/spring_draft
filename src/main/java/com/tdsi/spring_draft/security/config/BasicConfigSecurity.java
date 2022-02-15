package com.tdsi.spring_draft.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//https://openclassrooms.com/en/courses/7137776-securisez-votre-application-web-avec-spring-security/7275531-configurez-oauth-2-0-avec-openid-connect-sur-une-application-web-spring
public class BasicConfigSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                .and()
                .withUser("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("USER","ADMIN");
    }
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       // httpSecurity.authorizeRequests().anyRequest().authenticated().and().httpBasic();


        httpSecurity
         .authorizeRequests()
         .antMatchers("/admin").hasRole("ADMIN")
         .antMatchers("/user").hasRole("USER")
         .anyRequest().authenticated()
         .and()
         .httpBasic();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}

}
