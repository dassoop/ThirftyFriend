package com.example.ThriftyFriend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.repositories.ListingSummaryRepository;

@Service
public class ListingSummaryService 
{
	@Autowired
	private ListingSummaryRepository sumRepo;
	
	public ListingSummary findById(Long id)
	{
		return this.sumRepo.findById(id).orElse(null);
	}
	
	public ListingSummary createListingSummary(String name, double averageCost, double minCost, double maxCost)
	{
		ListingSummary sum = new ListingSummary();
		
		sum.setName(name);
		sum.setAverageCost(averageCost);
		sum.setMinCost(minCost);
		sum.setMaxCost(maxCost);
		
		return this.sumRepo.save(sum);
	}
	
	public List<ListingSummary> searchForSummary(String productName)
	{
		return this.sumRepo.findByNameContaining(productName);
	}
	
	public boolean existsByName(String name)
	{
		return this.sumRepo.existsByName(name);
	}
	
	public ListingSummary summaryUpdateAndRefresh(ListingSummary sum)
	{
		//TESTING LOGIC IN PLACE OF SEARCH RESULTS
		List<ListingItem> listingItems = new ArrayList<>();
		
		listingItems.add(new ListingItem("iphone", 300.50));
		listingItems.add(new ListingItem("iphone", 270));
		listingItems.add(new ListingItem("iphone", 500));
		listingItems.add(new ListingItem("iphone", 100));
		
		//Algo to determine min, max, and avg
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
		
		sum.setAverageCost(average);
		sum.setMinCost(min);
		sum.setMaxCost(max);
		
		return this.sumRepo.save(sum);
	}
	
	
}
