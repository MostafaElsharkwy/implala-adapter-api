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
	    
//	    @Autowired
//	    private DataSource dataSource;

//	    @Autowired
//	    private PlatformTransactionManager transactionManager;

	 
	    
 
	    
	    @Bean
	    public DataSource datasource() {
	    	System.out.println("  driverClassName " +driverClassName);
	    	System.out.println("  driverClassName " +url);
	       /*
	    	return DataSourceBuilder.create()
	          .driverClassName(driverClassName)
	          .url(url)
	          .username(username)
	          .password(password)
	          .build();	
	        */
	    	 DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	         dataSourceBuilder.driverClassName(driverClassName);
	         dataSourceBuilder.url(url);
	         dataSourceBuilder.username(username);
	         dataSourceBuilder.password(password);
	         DataSource dataSource = dataSourceBuilder.build();
	    	
	    	
//	    	System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");
//	        System.setProperty("java.security.krb5.conf", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/krb5.conf");
//	        System.setProperty("java.security.auth.login.config","/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/jaas.conf");
// 
//	        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
//	        conf.set("hadoop.security.authentication", "kerberos");
//	        UserGroupInformation.setConfiguration(conf);
//	        try {
//				UserGroupInformation.loginUserFromKeytab("impala/quickstart.cloudera@CLOUDERA", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/impala.keytab");
//		
//	        //UserGroupInformation.loginUserFromKeytab(priniciple, keyTabLocation);
//
//
//
//	        com.cloudera.impala.jdbc41.DataSource dataSource2 =
//	                new com.cloudera.impala.jdbc41.DataSource();
//	        dataSource2.setURL(url);
//	        
//
//	    	} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
// 
	    
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
