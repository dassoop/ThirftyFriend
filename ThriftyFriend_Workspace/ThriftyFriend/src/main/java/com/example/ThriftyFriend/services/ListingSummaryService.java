package com.example.ThriftyFriend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
