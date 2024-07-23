package com.learn.hibernate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;

public class HackMain {
	public static String xml= "<?xml version=\"1.0\" ?><root><test       attribute=\"text1\">javatpoint</test><test attribute=\"text2\">JTP</test></root>";  
	private class RequestBody{
		private String xml_directory;

		/**
		 * @return the xml_directory
		 */
		public String getXml_directory() {
			return xml_directory;
		}

		/**
		 * @param xml_directory the xml_directory to set
		 */
		public void setXml_directory(String xml_directory) {
			this.xml_directory = xml_directory;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//callOpenAIPostCall();
		sendPOST();
		try {  
			JSONObject json = XML.toJSONObject(xml);   
			        String jsonString = json.toString(4);  
			        System.out.println(jsonString);  
			  
			}catch (JSONException e) {  
			// TODO: handle exception  
			//System.out.println(e.toString());  
			} 
		
	}
	
	private static void callOpenAIPostCall() {
		try {
			RequestBody reqBody = new HackMain().new RequestBody();
			reqBody.setXml_directory("uploads/converter/");
			Gson gson = new Gson();
			final String gsonReq = gson.toJson(reqBody);
			System.out.println(gsonReq);
			HttpRequest postReq= HttpRequest.newBuilder()
					.header("Content-Type", "application/json")
					.uri(new URI("http://localhost:5000/fetchAndConvert"))
					.POST(BodyPublishers.ofString("{\"xml_directory\":\"uploads/converter/\"}")).build();
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpResponse<String> response = httpClient.send(postReq, BodyHandlers.ofString());
			System.out.println(response.body());
			
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String sendPOST(){

        String result = "";
        HttpPost post = new HttpPost("http://10.65.184.159:9899/fetchAndConvert");
        // add request headers
        post.addHeader("Content-Type", "application/json");
        
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"xml_directory\":\"uploads/converter/\"");
        json.append("}");

        // send a JSON data
        try {
			post.setEntity(new StringEntity(json.toString()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return result;
    }
}
