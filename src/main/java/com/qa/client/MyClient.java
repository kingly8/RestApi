package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.base.BaseTest;

public class MyClient  
{
	public CloseableHttpResponse response;

//GET METHOD WITHOUT HEADERS
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{

		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet httpget = new HttpGet(url); //GET METHOD CONNECTION WITH URL
		  response = httpclient.execute(httpget); // hit the GET URL
 
     return response;
	
	}
	
	//GET METHOD WITH HEADERS
		public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException
		{
			 CloseableHttpClient httpclient = HttpClients.createDefault();
			 HttpGet httpget = new HttpGet(url); //GET METHOD CONNECTION WITH URL
			 
			for(Map.Entry<String,String> entry : headerMap.entrySet())
			{
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			
			  response = httpclient.execute(httpget); // hit the GET URL

		      return response;

}
	//POST METHOD 
		
	public CloseableHttpResponse post(String url, String entitystring, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);  //POST REQUEST
		httppost.setEntity(new StringEntity(entitystring)); //FOR PAYLOAD  
		
		for(Map.Entry<String,String> entry : headerMap.entrySet())
		{
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		 
		response = httpClient.execute(httppost);
		return response;
		
	}
		
		
		
		
		
		
		
		}