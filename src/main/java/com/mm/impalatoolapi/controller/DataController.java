package com.mm.impalatoolapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cloudera.impala.jdbc42.internal.com.cloudera.altus.shaded.javax.ws.rs.Path;
import com.mm.impalatoolapi.config.QueryListProperties;
import com.mm.impalatoolapi.dto.QueryParamsModel;
import com.mm.impalatoolapi.service.DataSourceService;


// https://medium.com/@natnael.mekuannt/pagination-and-streaming-data-within-distributed-7b00fc35ce88
	
/**
 * 
 * @author tspann
 *
 */
@RestController
public class DataController {
 
	
	   @Autowired
	   QueryListProperties queryListProperties;
	  
	   @Autowired
	   DataSourceService dataSourceService;
	  
	  
	   
	public static HttpServletRequest getCurrentRequest() {
	     RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	     Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
	     Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
	     HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
	     Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
	     return servletRequest;
	 }
	
	Logger logger = LoggerFactory.getLogger(DataController.class);

	//@Autowired
	//private DataSourceService dataSourceService;
	
    private static final String twitterView = "Twitter User: %s";
 

 
    
    @GetMapping("/query/{queryId}")
    public List<Map> query(@PathVariable String queryId,@RequestParam(required = false) Map<String,String> allParams) 
    {
    	
    	
    	logger.info("allParams" + allParams) ;
    	
    	logger.info("queryListProperties"+queryListProperties.getQuerylist().get("persoanl-finnace"));
        
    	
    	String query=queryListProperties.getQuerylist().get(queryId);//"persoanl-finnace"
    	
    	logger.error("query"+query);
    	if(allParams!=null && !allParams.isEmpty()) 
    	{
	    	for (Map.Entry<String, String> entry : allParams.entrySet()) {
	    		
	    		 System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	    		 query=query.replace("$$"+entry.getKey()+"$$",  entry.getValue());
	    	}
    	}
    	
       List<Map> value = dataSourceService.excuteQuery(query);
    	final String userIpAddress = getCurrentRequest().getRemoteAddr();
    	final String userAgent = getCurrentRequest().getHeader("user-agent");
    	final String userDisplay = String.format("Query:%s,IP:%s Browser:%s", query, userIpAddress, userAgent);
    	
    	logger.error(userDisplay);
       // List<Map> value=new ArrayList<>();
		return value;
    }
}