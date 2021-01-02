package com.example.ThriftyFriend.services;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class APICategoryService 
{
	public String marketplace = "EBAY_US";
	
	public void getCategoryTree(String token)
	{
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
			try 
			{
				response = Unirest.post("https://api.ebay.com/commerce/taxonomy/v1_beta/get_default_category_tree_id?marketplace_id=" + marketplace)
						   .header("Content-Type", "application/x-www-form-urlencoded")
						   .header("Authorization", "Bearer " + token)
						   .asJson();
			} 
			catch (UnirestException e) 
			{
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			System.out.println(response);	
	}
	
	public void getCategories(String token)
	{

	}
}
