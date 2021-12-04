package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest
{
	public Properties prop;
	public int RESPONSE_status_code_200 = 200;
	public int RESPONSE_status_code_500 = 500;
	public int RESPONSE_status_code_400 = 400;
	public int RESPONSE_status_code_401 = 401;
	public int RESPONSE_status_code_201 = 201;
	
	public BaseTest() throws IOException
	{
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//qa//config//config.properties");
		     prop.load(file);
		} 
		catch (FileNotFoundException e) 
			{
			e.printStackTrace();
			}
		
	}

}
