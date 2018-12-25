package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{

	TestBase testbase;
	RestClient restClient;
	
	String url;
	String serviceuRL;
	String exactURL;
	
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() {
		url = properties.getProperty("URL");
		serviceuRL = properties.getProperty("serviceURL");		
		exactURL = url + serviceuRL;
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String,String> header = new HashMap<String,String>();
		header.put("Content-Type", "application/json");
		
		// Jackson API
		ObjectMapper objectMapper = new ObjectMapper();
		Users users = new Users("Saurabh", "Tester");
		
		// Convert Object to JSON File Conversion
		objectMapper.writeValue(new File("D:\\Eclipse Workarea\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		// Object to JSON in String (Marshelling)
		String userJsonString  = objectMapper.writeValueAsString(users);
		System.out.println("userJsonString "+userJsonString);
		
		closeableHttpResponse = restClient.post(exactURL, userJsonString, header);
	
		// Getting Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); 
		System.out.println("statusCode "+statusCode);
				
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"Status code is not 201");	
		
		// Getting Response in String
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		// Convert Response string in JsonObject 
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson "+responseJson);
		
		// JSON to Java Object (UnMarshelling)
		Users userObj = objectMapper.readValue(responseString, Users.class);
		System.out.println("userObj "+userObj);
		
		Assert.assertTrue(users.getName().equals(userObj.getName()));
		Assert.assertTrue(users.getJob().equals(userObj.getJob()));
		
	}
}
