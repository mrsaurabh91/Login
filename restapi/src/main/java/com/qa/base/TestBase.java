package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_403 = 403;
	public int RESPONSE_STATUS_CODE_300 = 300;

	public Properties properties;
	
	public TestBase() {
		// TODO Auto-generated constructor stub
		
		try {
			properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
