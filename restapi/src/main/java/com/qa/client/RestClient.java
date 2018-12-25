package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// GET METHOD without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		 
		// Create HTTPClient connection
		CloseableHttpClient  httpClient = HttpClients.createDefault();	
		// It will create connection with given URL (Http Get Request)
		HttpGet httpGet = new HttpGet(url); 
		// Hit the get URL
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); 
		
		return closeableHttpResponse;
	}
	
	    // GET METHOD with Headers
		public CloseableHttpResponse get(String url,HashMap<String,String> header) throws ClientProtocolException, IOException {
			
			CloseableHttpClient  httpClient = HttpClients.createDefault();	
			HttpGet httpGet = new HttpGet(url);			
			
			for(Map.Entry<String, String> entry:header.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); //hit the get URL
			
			return closeableHttpResponse;
		}
		
	    // POST METHOD with Headers
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> header) throws ClientProtocolException, IOException {
					
			CloseableHttpClient  httpClient = HttpClients.createDefault();	
			//Http POST Request
			HttpPost httpPost = new HttpPost(url);	
			// For Payload
			httpPost.setEntity(new StringEntity(entityString));
				
			for(Map.Entry<String, String> entry:header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost); //hit the get URL
			return closeableHttpResponse;
		}
}