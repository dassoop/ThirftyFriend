package com.example.ThriftyFriend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.services.APIAuthService;
import com.example.ThriftyFriend.services.APISearchService;
import com.example.ThriftyFriend.services.ListingSummaryService;

@SpringBootApplication
@EnableScheduling
public class ThriftyFriendApplication 
{
	
	@Autowired 
	APISearchService searchService;
	@Autowired
	ListingSummaryService sumService;
	@Autowired 
	APIAuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(ThriftyFriendApplication.class, args);
	}
	
	@Scheduled(fixedRate = 300000)
	public void run()
	{
		this.authService.getAuthToken();
		List<ListingSummary> summaryList = this.sumService.findAll();
		for(int i = 0; i < summaryList.size(); i++)
		{
			ListingSummary sum = summaryList.get(i);
			
			searchService.summaryUpdateAndRefresh(this.sumService.findById(summaryList.get(i).getId()), this.authService.returnToken());
		}
	}

}
