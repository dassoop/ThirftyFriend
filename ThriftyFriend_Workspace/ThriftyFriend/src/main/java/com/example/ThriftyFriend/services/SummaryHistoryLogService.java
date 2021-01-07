package com.example.ThriftyFriend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.SummaryHistoryLog;
import com.example.ThriftyFriend.repositories.SummaryHistoryLogRepository;

@Service
public class SummaryHistoryLogService 
{
	@Autowired
	private SummaryHistoryLogRepository logRepo;
	
	public List<SummaryHistoryLog> findAllBySummary(ListingSummary listingSummary)
	{
		return this.logRepo.findAllBySummaryOrderByCreatedAtDesc(listingSummary);
	}
	
	public SummaryHistoryLog findById(Long id)
	{
		return this.logRepo.findById(id).orElse(null);
	}
	
	public SummaryHistoryLog createHistoryLog(SummaryHistoryLog log)
	{
		return this.logRepo.save(log);
	}
	
	
}
