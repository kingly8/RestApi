package com.qa.apiTests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.client.MyClient;
import com.qa.util.TestUtil;

import bsh.ParseException;

public class GetApiTest extends BaseTest
{
	public GetApiTest() throws IOException 
	{
		super();
	}

	BaseTest base;
	MyClient client;
	String endPointUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse response;
	 HashMap<String, String> headerMap;
  
     @BeforeMethod
	  public void setUp() throws IOException
	   {
        base = new BaseTest();
        endPointUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceurl");
      //https://reqres.in/api/users
        url = endPointUrl + apiUrl;
        System.out.println(url);
       
	   }
     
     @Test(priority=1)
     public void getTest() throws ClientProtocolException, IOException
     {
    	 client = new MyClient();
    	 response  = client.get(url);
         
         //STATUS CODE
         int status = response.getStatusLine().getStatusCode();
		 System.out.println("Status code---->" +status);
		 Assert.assertEquals(status, RESPONSE_status_code_200,  "Status code is not 200");
		 
		 
		 //JSON RESPONSE  
		 String stringResponse = EntityUtils.toString(response.getEntity(), "UTF-8"); // CONVERT INTO STRING
		 JSONObject json = new JSONObject(stringResponse); //CONVERT JSON FORMAT
		 System.out.println("Json Response---->" + json);
		 
//SINGLE VALUE ASSERTION(JSON object)
		 
		 //PER_PAGE
		String perPageValue = TestUtil.getValueByJPath(json, "/per_page");
		System.out.println(perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		 
		//TOTAL_PAGES
		String totalPageValue = TestUtil.getValueByJPath(json, "/total_pages");
		System.out.println(totalPageValue);
		Assert.assertEquals(Integer.parseInt(totalPageValue), 2);
		
//GET THE VALUE FROM (JSON ARRAY)
		
		String lastName = TestUtil.getValueByJPath(json, "/data[0]/last_name");
		System.out.println("Last name is---->" + lastName);
		Assert.assertEquals(lastName, "Bluth");
		
		String id = TestUtil.getValueByJPath(json, "/data[0]/id");
		System.out.println("Value of id is----> " + id);
		Assert.assertEquals(id, "1");
		
		String email = TestUtil.getValueByJPath(json, "/data[0]/email");
		System.out.println("Value of email id is---->" +email);
		Assert.assertEquals(email, "george.bluth@reqres.in");
		 
		String avatar = TestUtil.getValueByJPath(json, "/data[0]/avatar");
		System.out.println("Value of avatar is----->" +avatar);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		
		 //HEADER RESPONSE
		 Header[] array = response.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		    
		 for(Header header : array)
		    {
		    	allHeaders.put(header.getName(), header.getValue());
		    }
		   System.out.println("Header Response--->" + allHeaders);
	     }
         
     @Test(priority=2)
     public void getTestWithHeader() throws ClientProtocolException, IOException
     {
    	 client = new MyClient();
         headerMap = new HashMap<String, String>();
    	 
    	 headerMap.put("Cookie", "__cfduid=d1127569f929f8fdc8bf8c87ef84187ef1586411399");
//    	 headerMap.put("username", "test123");
//    	 headerMap.put("password", "123");
//    	 headerMap.put("authToken", "1234");
    
    	 response  = client.get(url, headerMap);
         
         //STATUS CODE
         int status = response.getStatusLine().getStatusCode();
		 System.out.println("Status code---->" +status);
		 Assert.assertEquals(status, RESPONSE_status_code_200,  "Status code is not 200");
		 
		 
		 //JSON RESPONSE  
		 String stringResponse = EntityUtils.toString(response.getEntity(), "UTF-8"); // CONVERT INTO STRING
		 JSONObject json = new JSONObject(stringResponse); //CONVERT JSON FORMAT
		 System.out.println("Json Response---->" + json);
		 
//SINGLE VALUE ASSERTION(JSON object)
		 
		 //PER_PAGE
		String perPageValue = TestUtil.getValueByJPath(json, "/per_page");
		System.out.println(perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		 
		//TOTAL_PAGES
		String totalPageValue = TestUtil.getValueByJPath(json, "/total_pages");
		System.out.println(totalPageValue);
		Assert.assertEquals(Integer.parseInt(totalPageValue), 2);
		
//GET THE VALUE FROM (JSON ARRAY)
		
		String lastName = TestUtil.getValueByJPath(json, "/data[0]/last_name");
		System.out.println("Last name is---->" + lastName);
		Assert.assertEquals(lastName, "Bluth");
		
		String id = TestUtil.getValueByJPath(json, "/data[0]/id");
		System.out.println("Value of id is----> " + id);
		Assert.assertEquals(id, "1");
		
		String email = TestUtil.getValueByJPath(json, "/data[0]/email");
		System.out.println("Value of email id is---->" +email);
		Assert.assertEquals(email, "george.bluth@reqres.in");
		 
		String avatar = TestUtil.getValueByJPath(json, "/data[0]/avatar");
		System.out.println("Value of avatar is----->" +avatar);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		
		 //HEADER RESPONSE
		 Header[] array = response.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		    
		 for(Header header : array)
		    {
		    	allHeaders.put(header.getName(), header.getValue());
		    }
		   System.out.println("Header Response--->" + allHeaders);
	     }
     }
	

