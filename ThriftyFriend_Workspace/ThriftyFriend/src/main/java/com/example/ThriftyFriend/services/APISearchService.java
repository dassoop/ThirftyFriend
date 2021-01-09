package com.example.ThriftyFriend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.ListingItem;
import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.SummaryHistoryLog;
import com.example.ThriftyFriend.repositories.ListingSummaryRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class APISearchService 
{
	@Autowired
	private SummaryHistoryLogService logService;
	@Autowired
	private ListingSummaryRepository sumRepo;
	@Autowired
	private APICategoryService catService;
	@Autowired
	private APIAuthService authService;
	
	//Main method to send search request to the Ebay API. Returns JSON Object of product listings with prices.
    public JSONObject requestSearch(String searchText, String token)
    {    	
    	//Convert spaces in string to underscores for search query
    	List<String> searchTextSeperated = Arrays.asList(searchText.split(" "));
    	String combinedString = "";
        for(int i = 0; i < searchTextSeperated.size(); i++)
        {
        	combinedString = combinedString + "_" + searchTextSeperated.get(i);
        }
        //Start search request
        HttpResponse<JsonNode> response = null;
        JSONObject responseJSON = null;
        try 
        {
        	response = Unirest.get("https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + combinedString + "&limit=10")
                    .header("Authorization", "Bearer " + token)
                    .asJson();        
            responseJSON = response.getBody().getObject();
        } 
        catch (UnirestException e) 
        {
            e.printStackTrace();
        } 
        catch(NullPointerException n)
        {

        }
        return responseJSON;
    }
    
    //Parse through the JSON Object returned from the Ebay API and assign it to temporary ListingItem Objects
    public List<ListingItem> parseSearchJSON(JSONObject response, String token)
    {
    	if(response.has("errors"))
    	{
    		System.out.println("NO TOKEN");
    		this.authService.getAuthToken();
    	}
		//Start parsing through the JSON search response
		JSONArray jArray = response.getJSONArray("itemSummaries");
		List<ListingItem> searchResults = new ArrayList<>();
		
		for(int i = 0; i < jArray.length(); i++)
		{
			JSONObject obj = jArray.getJSONObject(i);
			String marketPrice = "";
	
			if(obj.has("marketingPrice"))
			{
				marketPrice = obj.getJSONObject("marketingPrice").getJSONObject("originalPrice").getString("value");
			}
			String title = obj.getString("title");
			double price = Double.parseDouble(obj.getJSONObject("price").getString("value"));
			String imageURL = obj.getJSONObject("image").getString("imageUrl");
			String itemWebUrl = obj.getString("itemWebUrl");
			
			//Loop through the category objects of the newly created listing item and add them as a list
			List<String> categoryIdList = new ArrayList<>();
			List<String> categoryNameList = new ArrayList<>();
//			if(obj.getJSONArray("categories")!=null)
//			{
//				JSONArray categories = obj.getJSONArray("categories");
//				
//				for(int index = 0; index < categories.length(); index++)
//				{
//					JSONObject catObj = categories.getJSONObject(index);				
//					String categoryId = catObj.getString("categoryId");
//					categoryIdList.add(categoryId);
//					String catName = this.catService.getCategoryInfo(token, categoryId);
////					categoryNameList.add(catName);
//				}
//			}

			
			//Create listing item for this search result
			ListingItem listingItem = new ListingItem(title, price, imageURL, categoryIdList, categoryNameList, itemWebUrl);	
					
			searchResults.add(listingItem);
		}
    	return searchResults;
    }
    
    //Use ListingItem Objects price value to find the min, max, and average price of the listings returned.
    public List<Double> minMaxAvgAlgo(List<ListingItem> listingItems)
    {
		//Algorithm to pull min, max, and average out of the list. 
    	List<Double> mathResults = new ArrayList<>();
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
		
		mathResults.add(min);
		mathResults.add(max);
		mathResults.add(average);
    	return mathResults;
    }
    
    //Send a new request when viewing a specific Summary to make sure the values are up to date. 
	public ListingSummary summaryUpdateAndRefresh(ListingSummary sum, String token)
	{			
		//Send fresh search request
		JSONObject response = this.requestSearch(sum.getName(), token);
//		System.out.println(response);
		List<ListingItem> listingItems = this.parseSearchJSON(response, token);
		List<Double> mathResults = this.minMaxAvgAlgo(listingItems);	
		
		//Log current price as history log
		SummaryHistoryLog log = new SummaryHistoryLog(sum.getName(), mathResults.get(2), mathResults.get(1), mathResults.get(0));
		this.logService.createHistoryLog(log);
		sum.getHistoryLogs().add(log);
		log.setSummary(sum);
		this.logService.saveLog(log);

		//Set Summary values
		sum.setAverageCost(mathResults.get(2));
		sum.setMaxCost(mathResults.get(1));
		sum.setMinCost(mathResults.get(0));
			
		return this.sumRepo.save(sum);
	}
}
