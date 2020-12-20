package com.example.ThriftyFriend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.SummaryHistoryLog;
import com.example.ThriftyFriend.repositories.SummaryHistoryLogRepository;

@Service
public class SummaryHistoryLogService 
{
	@Autowired
	private SummaryHistoryLogRepository logRepo;
	
	public SummaryHistoryLog findById(Long id)
	{
		return this.logRepo.findById(id).orElse(null);
	}
	
	public SummaryHistoryLog createHistoryLog(SummaryHistoryLog log)
	{
		return this.logRepo.save(log);
	}
	
	
}
