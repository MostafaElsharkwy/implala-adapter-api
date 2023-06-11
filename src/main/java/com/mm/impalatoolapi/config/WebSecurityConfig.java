package com.mm.impalatoolapi.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

 
 
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
 

    /*       http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/sadad/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic();
          */
    //"/socket**","/socket/**"
    @Override
    protected void configure(HttpSecurity http) throws Exception {
               http 
                .csrf().disable()
                
                .authorizeRequests()
                
                .antMatchers("/token/*", 
                		      "/signup",
                		      "/query/**",
                		      "/users/signup",
                		      "/users/login",
                		      "/permissions**",
                		      "/roles**",
                   		      "/v2/*",
                   		      "/v3/*",
                   		   "/v3/api-docs/swagger-config",
 
                   		      "/swagger-ui/**",
                   		    "/favicon.ico",
              		      "/swagger-ui.html",
              		      "/swagger**",
              		      "/webjars/**",
              	 
              		      "/swagger-resources/**", 
                   		      "/api-docs**",
                		      "/swagger-ui.html**",
                		      
                		      "/swagger**",
                		      "/webjars/**",
                		      "/swagger-resources/**" ,
                		//      ,"/socket**","/socket/**"
                		      "*" 
                		      ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
              
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
               
                 http.cors();
              
               ;
              
    }

//    @Bean(name = "passwordEncoder")
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();     
//     }


}
