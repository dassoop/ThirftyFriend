package com.example.TestEbayAPI.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String searchRequest(@RequestParam("searchText")String searchText)
	{
		JSONObject response = this.searchService.requestSearch(searchText);
		System.out.println(response);
		return "searchResult.jsp";
	}

}
