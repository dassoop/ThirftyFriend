package com.example.TestEbayAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TestEbayAPI.models.SearchResult;
import com.example.TestEbayAPI.services.SearchService;

@Controller
public class MainController 
{
	@Autowired 
	private SearchService searchService;
	
	@GetMapping("/")
	public String index()
	{
		return "index.jsp";
	}
	
	@PostMapping("/searchRequest")
	public String searchRequest(@RequestParam("searchText")String searchText, Model m)
	{
		//Send JSON request and set to response. 
		JSONObject response = this.searchService.requestSearch(searchText);
		System.out.println(response);
				
		//Start parsing through the JSON search response
		JSONArray jArray = response.getJSONArray("itemSummaries");
		List<SearchResult> searchResults = new ArrayList<>();
		
		for(int i = 0; i < jArray.length(); i++)
		{
			JSONObject obj = jArray.getJSONObject(i);
			String marketPrice = "";
			
			if(obj.has("marketingPrice"))
			{
				marketPrice = obj.getJSONObject("marketingPrice").getJSONObject("originalPrice").getString("value");
				System.out.println("marketPrice");
			}
			
			String title = obj.getString("title");
			String price = obj.getJSONObject("price").getString("value");
			String imageURL = obj.getJSONObject("image").getString("imageUrl");
	
			SearchResult result = new SearchResult(title, price, marketPrice, imageURL);	
			searchResults.add(result);
		}
		m.addAttribute("searchResults", searchResults);
		return "searchResult.jsp";
	}

}
