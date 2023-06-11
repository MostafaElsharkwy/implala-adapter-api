package com.mm.impalatoolapi.config;

import java.util.Map; 
import org.springframework.boot.context.properties.ConfigurationProperties; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


 

@Configuration
//@PropertySource("classpath:queries-config.yml")
@ConfigurationProperties(prefix = "impala")
public class QueryListProperties {

	  
	    
	    private Map<String, String> querylist;

		public QueryListProperties() {
			super();
			// TODO Auto-generated constructor stub
			System.out.println("querylist"+querylist);
		}

		public Map<String, String> getQuerylist() {
			return querylist;
		}

		public void setQuerylist(Map<String, String> querylist) {
			this.querylist = querylist;
		}
	    
	    
 
	    
}
