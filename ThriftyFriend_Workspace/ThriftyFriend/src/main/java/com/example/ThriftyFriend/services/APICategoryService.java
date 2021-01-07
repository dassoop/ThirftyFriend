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
	
	public String getCategoryTree(String token)
	{
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
			try 
			{
				response = Unirest.get("https://api.ebay.com/commerce/taxonomy/v1_beta/get_default_category_tree_id?marketplace_id=" + marketplace)
						   .header("Authorization", "Bearer " + token)
						   .asJson();
			} 
			catch (UnirestException e) 
			{
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			String categoryTreeId = jsonResponse.getString("categoryTreeId");
			System.out.println(categoryTreeId);
			return categoryTreeId;
	}
	
	public void getCategoryList(String token)
	{
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
			try 
			{
				response = Unirest.get("https://api.ebay.com/commerce/taxonomy/v1_beta/category_tree/0")
						   .header("Authorization", "Bearer " + token)
						   .header("Accept-Encoding", "application/gzip")
						   .asJson();
			} 
			catch (UnirestException e) 
			{
				e.printStackTrace();
			}
//			jsonResponse = response.getBody().getObject();
			System.out.println(response);
			//TODO: add gzip parsing?
	}
	
	public void suggestedCategory(String token, String search)
	{
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
			try 
			{
				response = Unirest.get("https://api.ebay.com/commerce/taxonomy/v1_beta/category_tree/0/get_category_suggestions?q=" + search)
						   .header("Authorization", "Bearer " + token)
						   .asJson();
			} 
			catch (UnirestException e) 
			{
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			System.out.println(jsonResponse);
			
			
	}
}
