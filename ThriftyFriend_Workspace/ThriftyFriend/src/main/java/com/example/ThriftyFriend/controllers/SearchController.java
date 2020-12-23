package com.example.ThriftyFriend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.services.ListingSummaryService;
import com.example.ThriftyFriend.services.UserService;

@Controller
public class SearchController 
{
	@Autowired
	private UserService uService;
	@Autowired
	private ListingSummaryService sumService;
	
//FAKE SEARCH REQUEST - Mapping to simulate data being returned from an Ebay JSON response and then calculated into Average, Min, and Max values
	@PostMapping("/fakeSearchRequest")
	public String fakeSearchRequest(@RequestParam("search")String search, Model m, HttpSession session)
	{
		//Create list to add Ebay listing items info to.
		List<ListingItem> listingItems = new ArrayList<>();
		
		//Create listing items and assign dummy data. These parameter assignments will be 
		//Replaced with the output of our Ebay API call as we loop through the JSON array. 
		listingItems.add(new ListingItem("iphone", 300.50));
		listingItems.add(new ListingItem("iphone", 270));
		listingItems.add(new ListingItem("iphone", 400));
		listingItems.add(new ListingItem("iphone", 150));
		
		
		//Algorithm to pull min, max, and average out of the list. 
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
		
		
		//Search the Thrifty DataBase for a summary that already matches the name 
		List<ListingSummary> listingSummaries = this.sumService.searchForSummary(search);
		
		
		//Check if there is a User logged in to display their name
		if(session.getAttribute("user_id") != null)
		{
			User u = this.uService.findById((Long)session.getAttribute("user_id"));
			m.addAttribute("user", u);
		}

		//Pass on all data that will be displayed on the page 
		m.addAttribute("averageCost", average);
		m.addAttribute("minCost", min);
		m.addAttribute("maxCost", max);
		
		m.addAttribute("searchedText", search);
		m.addAttribute("listingItems", listingItems);
		m.addAttribute("listingSummaries", listingSummaries);
		return "viewListings.jsp";
	}
	
//FAKE SEARCH GET MAPPING - The same functionality as the POST version, but intended to be used with <a> links as oppsed to forms, to refresh values when viewing the Summaries. 
	@GetMapping("/viewListings/{name}")
	public String viewListings(Model m, @PathVariable("name")String search, HttpSession session)
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
		
		//Check if there is a User logged in to display their name
		if(session.getAttribute("user_id") != null)
		{
			User u = this.uService.findById((Long)session.getAttribute("user_id"));
			m.addAttribute("user", u);
		}

		m.addAttribute("averageCost", average);
		m.addAttribute("minCost", min);
		m.addAttribute("maxCost", max);
		
		m.addAttribute("searchedText", search);

		m.addAttribute("listingItems", listingItems);
		m.addAttribute("listingSummaries", listingSummaries);
		return "viewListings.jsp";
	}
}
