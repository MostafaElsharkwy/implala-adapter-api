package com.mm.impalatoolapi.config;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

//import org.apache.hadoop.security.UserGroupInformation;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.apache.hadoop.security.UserGroupInformation;

 

@Configuration
@ConfigurationProperties("impala.datasource") 
@Primary
public class ImpalaConfig {

	   public String url;
	    public String username;
	    public String password;
	    public String driverClassName;
	    public String threadNum;
	    
 
 
	    
	    @Bean
	    public DataSource datasource() {
	    	System.out.println("  driverClassName " +driverClassName);
	    	System.out.println("  driverClassName " +url);
	 
	    	 DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	         dataSourceBuilder.driverClassName(driverClassName);
	         dataSourceBuilder.url(url);
	         dataSourceBuilder.username(username);
	         dataSourceBuilder.password(password);
	         DataSource dataSource = dataSourceBuilder.build();
	    	
	    	
  
	        return dataSource;
	        
	         
	    }

	    
	    
	    public ImpalaConfig() {
	    	
	        super();
	        System.out.println("url"+url);
	    }



		public String getUrl() {
			return url;
		}



		public void setUrl(String url) {
			this.url = url;
		}



		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getDriverClassName() {
			return driverClassName;
		}



		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}



		public String getThreadNum() {
			return threadNum;
		}



		public void setThreadNum(String threadNum) {
			this.threadNum = threadNum;
		}
  
//	    public ImpalaConfig(DataSource dataSource) {
//	        super(dataSource);
//	    }
//	    
 
}
