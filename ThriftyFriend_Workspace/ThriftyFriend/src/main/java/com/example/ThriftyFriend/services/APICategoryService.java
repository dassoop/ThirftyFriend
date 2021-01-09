package com.example.ThriftyFriend.services;

import org.json.JSONArray;
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
	public String appId = "KellyHow-ThriftyF-PRD-a736b19af-f0dcc13b";
	
	public String getCategoryInfo(String token, String catId)
	{
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
			try 
			{
				response = Unirest.get("https://open.api.ebay.com/Shopping?callname=GetCategoryInfo&appid=LanceSum-ThriftyF-PRD-3736735cb-18624020&version=967&siteid=0&CategoryID="+ catId +"&responseencoding=JSON")
						   .header("Authorization", "Bearer " + token)
						   .asJson();
			} 
			catch (UnirestException e) 
			{
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			System.out.println(jsonResponse);
			JSONArray categoryArray = jsonResponse.getJSONObject("CategoryArray").getJSONArray("Category");
			String catName = "";
			for(int i = 0; i < categoryArray.length(); i++)
			{
				JSONObject catObj = categoryArray.getJSONObject(i);
				catName = catObj.getString("CategoryName");
			}
			return catName;
	}
}
