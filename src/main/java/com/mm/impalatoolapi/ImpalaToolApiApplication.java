/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.mm.impalatoolapi;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mm.impalatoolapi.config.QueryListProperties;

import org.springframework.boot.Banner;
@EnableConfigurationProperties({
	QueryListProperties.class
})
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class ImpalaToolApiApplication  extends SpringBootServletInitializer {
	
	
 
	public static void main(String[] args) {
		//SpringApplication.run(LiveChatApplication.class, args);
		 SpringApplication app = new SpringApplication(ImpalaToolApiApplication.class);
		 app.setBannerMode(Banner.Mode.OFF);
		  app.run(args);
	}
	
 
	 
	
	
	
}


