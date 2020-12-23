package com.example.ThriftyFriend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.services.ListingSummaryService;
import com.example.ThriftyFriend.services.SearchService;
import com.example.ThriftyFriend.services.UserService;

@Controller
public class SearchController 
{
	@Autowired
	private UserService uService;
	@Autowired
	private ListingSummaryService sumService;
	@Autowired
	private SearchService searchService;
	
	
	//Mapping for search query. Homepage search bar uses the request parameter as input. 
	//Otherwise refresh mappings are done with the path variable when viewing summaries for up to date information.
	//Can only use one or the other, never both PathVariable and RequestParam. 
	@GetMapping("/searchRequest/{name}")
	public String refreshSearch(@PathVariable("name")String name, @RequestParam(value = "search", required=false)String searchText, Model m, HttpSession session)
	{	
		if(searchText != null)
		{
			searchText = searchText.trim();
			if(searchText.length() < 1)
			{
				return "redirect:/";			
			}
			else
			{
				name = searchText;
			}
		}
		
		//Get response data from Ebay API call in service	
		JSONObject response = this.searchService.requestSearch(name);
		List<ListingItem> listingItems = this.searchService.parseSearchJSON(response);	
		List<Double> mathResults = this.searchService.minMaxAvgAlgo(listingItems);	
		
		//Search the Thrifty DataBase for a summary that already matches the name 
		List<ListingSummary> listingSummaries = this.sumService.searchForSummary(name);
		
		//Check if there is a User logged in to display their name
		if(session.getAttribute("user_id") != null)
		{
			User u = this.uService.findById((Long)session.getAttribute("user_id"));
			m.addAttribute("user", u);
		}
		m.addAttribute("minCost", mathResults.get(0));
		m.addAttribute("maxCost", mathResults.get(1));
		m.addAttribute("averageCost", mathResults.get(2));		
		m.addAttribute("searchedText", name);
		m.addAttribute("listingItems", listingItems);
		m.addAttribute("listingSummaries", listingSummaries);
		return "viewListings.jsp";
	}
}
