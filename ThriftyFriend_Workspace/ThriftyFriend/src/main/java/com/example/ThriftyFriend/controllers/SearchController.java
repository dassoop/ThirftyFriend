package com.example.ThriftyFriend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.services.ListingSummaryService;
import com.example.ThriftyFriend.services.UserService;

@Controller
public class SearchController 
{
	@Autowired
	private UserService uService;
	@Autowired
	private ListingSummaryService sumService;
	
	@PostMapping("/fakeSearchRequest")
	public String fakeSearchRequest(@RequestParam("search")String search, Model m)
	{
		List<ListingItem> listingItems = new ArrayList<>();
		
		listingItems.add(new ListingItem("iphone", 300.50));
		listingItems.add(new ListingItem("iphone", 270));
		listingItems.add(new ListingItem("iphone", 400));
		listingItems.add(new ListingItem("iphone", 150));
		
		double total = 0;
		double average = 0;

		double min = listingItems.get(0).getPrice();
		double max = 0;
		
		for(int i = 0; i <= listingItems.size()-1; i++)
		{
			total = total + listingItems.get(i).getPrice();
			
			if(listingItems.get(i).getPrice() < min)
			{
				min = listingItems.get(i).getPrice();
			}
			
			if(listingItems.get(i).getPrice() > max)
			{
				max = listingItems.get(i).getPrice();
			}
		}
		
		average = total / listingItems.size();
		
		
		List<ListingSummary> listingSummaries = this.sumService.searchForSummary(search);
		for(int i = 0; i < listingSummaries.size(); i++)
		{
			System.out.println(listingSummaries.get(i).getName());
		}
		
		m.addAttribute("averageCost", average);
		m.addAttribute("minCost", min);
		m.addAttribute("maxCost", max);
		
		m.addAttribute("searchedText", search);

		m.addAttribute("listingItems", listingItems);
		m.addAttribute("listingSummaries", listingSummaries);
		return "viewListings.jsp";
	}
	
	@GetMapping("/viewListings/{name}")
	public String viewListings(Model m, @PathVariable("name")String search)
	{
		List<ListingItem> listingItems = new ArrayList<>();
		
		listingItems.add(new ListingItem("iphone", 300.50));
		listingItems.add(new ListingItem("iphone", 270));
		listingItems.add(new ListingItem("iphone", 400));
		listingItems.add(new ListingItem("iphone", 150));
		
		double total = 0;
		double average = 0;

		double min = listingItems.get(0).getPrice();
		double max = 0;
		
		for(int i = 0; i <= listingItems.size()-1; i++)
		{
			total = total + listingItems.get(i).getPrice();
			
			if(listingItems.get(i).getPrice() < min)
			{
				min = listingItems.get(i).getPrice();
			}
			
			if(listingItems.get(i).getPrice() > max)
			{
				max = listingItems.get(i).getPrice();
			}
		}
		
		average = total / listingItems.size();
		
		
		List<ListingSummary> listingSummaries = this.sumService.searchForSummary(search);
		for(int i = 0; i < listingSummaries.size(); i++)
		{
			System.out.println(listingSummaries.get(i).getName());
		}
		
		m.addAttribute("averageCost", average);
		m.addAttribute("minCost", min);
		m.addAttribute("maxCost", max);
		
		m.addAttribute("searchedText", search);

		m.addAttribute("listingItems", listingItems);
		m.addAttribute("listingSummaries", listingSummaries);
		return "viewListings.jsp";
	}
	
//	@PostMapping("/searchRequest")
//	public String searchRequest(@RequestParam("searchText")String searchText)
//	{
//		JSONObject response = this.searchService.requestSearch(searchText);
//		System.out.println(response);
//		return "searchResult.jsp";
//	}
}
