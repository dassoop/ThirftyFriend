package com.example.ThriftyFriend.services;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class APIAuthService
{
	public String getAuthToken() 
	{
		String token = "";
		
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
		final String credentials = "KellyHow-ThriftyF-PRD-a736b19af-f0dcc13b:PRD-736b19afa887-f072-4d6e-a737-7572";
			try 
			{
				response = Unirest.post("https://api.ebay.com/identity/v1/oauth2/token")
						   .header("Content-Type", "application/x-www-form-urlencoded")
						   .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString(credentials.getBytes()))
						   .body("grant_type=client_credentials&scope=https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope")
						   .asJson();
				//assertEquals(201, response.getStatus());
			} 
			catch (UnirestException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			token = jsonResponse.getString("access_token"); 
			return token;
	}
}
