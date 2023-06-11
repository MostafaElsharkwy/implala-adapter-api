package com.mm.impalatoolapi.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.security.SecurityRequirement; 
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
 
import org.springframework.util.StringUtils;

/*
 * 

 * */

@Configuration
@OpenAPIDefinition(info = @Info(
		                 title = "Impala Tool API ", 
		                 version = "v1",
		                 description="Impala tool apis",
		                 contact = @Contact(
		                		    email="moustafa.elsharkawy@gmail.com",
		                		    name=" Mostafa Elsharkawy"
		                		    )
		                 )
)
public class OpenApi30Config {
	
	  @Bean
	  public OpenAPI customOpenAPI() {
	    final String securitySchemeName = "bearerAuth";
	//   String moduleName="ImageTool";
 
	    return new OpenAPI()
	        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
	        .components(
	            new Components()
	                .addSecuritySchemes(securitySchemeName,
	                    new SecurityScheme()
	                        .name(securitySchemeName)
	                        .type(SecurityScheme.Type.HTTP)
	                        .scheme("bearer")
	                        .bearerFormat("JWT")
	                )
	        )
	       
	      //.info(new Info().title(apiTitle).version(apiVersion)
	        		;
	  }
	  
}