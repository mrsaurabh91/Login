package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;


public class GetAPITest extends TestBase{

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
	public void getAPITest() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(exactURL);
		
		// Getting Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); 
		System.out.println("statusCode "+statusCode);
				
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");	 			
				
		// Getting Response in String
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
				
		// Convert Response string in JsonObject 
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson "+responseJson);
		
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("perPageValue "+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
		// Get the value from JSON Array
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		@SuppressWarnings("unused")
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("lastName "+lastName);
		System.out.println("id "+id);
		System.out.println("avatar "+avatar);
		System.out.println("firstName "+firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		
		// Getting All Headers
		Header[] hearderArray = closeableHttpResponse.getAllHeaders();
				
		LinkedHashMap<String,String> allHeaders = new LinkedHashMap<String,String>();
				
		for(Header header:hearderArray) {
			allHeaders.put(header.getName(),header.getValue());
		}				
		System.out.println("allHeaders "+allHeaders);		
	}
	
	@Test
	public void getAPIWithHeaderTest() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		/*headerMap.put("userName", "test@amazon.com");
		headerMap.put("password", "test123");
		headerMap.put("Auth Token", "12345");*/
		
		closeableHttpResponse = restClient.get(exactURL,headerMap);
		
		// Getting Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); 
		System.out.println("statusCode "+statusCode);
				
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");				
				
		// Getting JSON String
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
				
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson "+responseJson);
		
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("perPageValue "+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
		// Get the value from JSON Array
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		@SuppressWarnings("unused")
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("lastName "+lastName);
		System.out.println("id "+id);
		System.out.println("avatar "+avatar);
		System.out.println("firstName "+firstName);
		
		Assert.assertEquals(lastName, "Bluth");
		
		// Getting All Headers
		Header[] hearderArray = closeableHttpResponse.getAllHeaders();
				
		LinkedHashMap<String,String> allHeaders = new LinkedHashMap<String,String>();
				
		for(Header header:hearderArray) {
			allHeaders.put(header.getName(),header.getValue());
		}				
		System.out.println("allHeaders "+allHeaders);		
	}
	
	
	
}
