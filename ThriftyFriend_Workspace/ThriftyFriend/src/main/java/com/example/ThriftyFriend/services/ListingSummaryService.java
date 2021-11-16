package com.example.ThriftyFriend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.SummaryHistoryLog;
import com.example.ThriftyFriend.repositories.ListingSummaryRepository;

@Service
public class ListingSummaryService 
{
	@Autowired
	private ListingSummaryRepository sumRepo;
	@Autowired
	private SummaryHistoryLogService logService;
	
	public ListingSummary findById(Long id)
	{
		return this.sumRepo.findById(id).orElse(null);
	}
	
	public List<ListingSummary> findAll()
	{
		return this.sumRepo.findAll();
	}
	
	public List<ListingSummary> searchForSummary(String productName)
	{
		return this.sumRepo.findByNameContaining(productName);
	}
	
	public boolean existsByName(String name)
	{
		return this.sumRepo.existsByName(name);
	}
	
	public ListingSummary createListingSummary(String name, double averageCost, double minCost, double maxCost)
	{
		ListingSummary sum = new ListingSummary();
		
		sum.setName(name);
		sum.setAverageCost(averageCost);
		sum.setMinCost(minCost);
		sum.setMaxCost(maxCost);
		
		SummaryHistoryLog log = new SummaryHistoryLog(name, averageCost, minCost, maxCost);
		this.logService.createHistoryLog(log);
		
		//TODO: Create and add SummaryHistoryLog Object
		
		return this.sumRepo.save(sum);
	}

	public ListingSummary findByName(String name)
	{
		ListingSummary sum = this.sumRepo.findByName(name);
		return sum;
	}

}
