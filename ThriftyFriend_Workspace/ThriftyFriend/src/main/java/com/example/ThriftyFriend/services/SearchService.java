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
public class SearchService 
{
	@Autowired
	private SummaryHistoryLogService logService;
	@Autowired
	private ListingSummaryRepository sumRepo;
	
	private String token = "v^1.1#i^1#p^1#I^3#r^0#f^0#t^H4sIAAAAAAAAAOVYa2wUVRTuttua8qiBGJRn16lIRGf3zsx2HxN2YaFUts+lu2BbMWUed9ppZ2emM3dpN4oujRKD6A9ENCaWkkaNMfKwMYFE+UNjIGqMiFGMKeAjEEkE+UPQ+Lizu5RtJS3SDTZx/2zm3HPP/b7vnHPvnQGpktLl29dtvzrbdlfhQAqkCm02aiYoLSl+uKyocEFxAchxsA2kHkjZ+4ourDC5uKKzTdDUNdWEjt64opps2hggEobKapwpm6zKxaHJIoGNhurrWNoJWN3QkCZoCuEIVwUIxicxtNvvEWne6+b9Eraq12PGtAAhiUDyiTRFA0ECPp81bpoJGFZNxKkoQNCABiRFkzQTAx6WqWRptxOHayUcG6FhypqKXZyACKbhsum5Rg7WiaFypgkNhIMQwXCoOtoYCletbYitcOXECmZ1iCIOJcyxT2s0ETo2ckoCTryMmfZmowlBgKZJuIKZFcYGZUPXwdwG/LTUfooBgpfheej2iYyHyouU1ZoR59DEOCyLLJJS2pWFKpJRcjJFsRp8JxRQ9qkBhwhXOay/9QlOkSUZGgFi7epQSygSIYJ1nCrAaCJOxjoMWULJajLSVEUyXsbjZSoFnqR8HtqN6WUXykTLyjxupTWaKsqWaKajQUOrIUYNx2tD52iDnRrVRiMkIQtRrp/vuoY+qtVKaiaLCdShWnmFcSyEI/04eQZGZyNkyHwCwdEI4wfSEgUITtdlkRg/mK7FbPn0mgGiAyGddbl6enqcPYxTM9pdNACUq7m+Lip0wDhHYF+r1zP+8uQTSDlNRYB4pimzKKljLL24VjEAtZ0Iuhng9nizuo+FFRxv/Ychh7NrbEfkq0PcPMNwAvRQEPo5kabz0SHBbJG6LByQ55JknDO6INIVvBIp4DpLxKEhizicROPdEJKixy+Rbr8kkXyl6CEpCUIAIc8Lft//qVFutdSjUDAgykut563O9cf4bk8o3O6trauJ1SS7le5arZ0xhNpQk7eaizfWNgstLVX1MX9NS+BWu+Hm5AVNhxFNkYVkHhSwej2PKjCGGOEMlIxCRcGGKRE1LaLTK8nWfBMH4HTZaTW2U9DiLo3DO7plaksjnhLnkK6H4/EE4ngFhvOzm/9HO/lN6cn4rjOtOOH8ZRIpi5lLijOdTae5RXAa0NQSBr6fORutMzumdUEV74DI0BQFGhupKSf6TufX6vVJ9PiXh8Xtcc/fTWU61bagyLiE2qYbszuSUZmbZqcx5QE+L6jE75lT4rUmndNYcrqdQ+s0E0FxImr2R2/zWu0a+5IfLEj/qD7bB6DPdqjQZgMusJSqAPeXFG2wF81aYMoIOmVOcppyu4rfXQ3o7IJJnZONwhKbvoH7eWnOZ4WBJ8B9ox8WSouomTlfGcCiGyPF1N33zqYBRdMM8DCVtLsVVNwYtVPz7Pds8z63Z+uuwQMn/hQbPtv98dXh13e9B2aPOtlsxQX2PltBr+OXN0j9wU7tLXTm65G3v2gqv1x3dOSM7ai93Dy8vObct/XHZ5WdPfXpsV1lV/b/8MnhA70vX/lq30Vh0zz6o4XU4Mg3p5v6Nx3pXNVWOPT7I0deWba8svzNtcNP9cO5S2Zcef74j6n+VTPmtlwd/uMkVbOyYd5FvvPdlS99mFy/j2re2XZ+8/zLFb/OMV48v3DvogMV1050BfeU1XYNLV6sDL+a+su1v3xAKPtyS/Frrp8OLUtRp7ddG/z8hWPPNleVLvjtwgYImYcObmZP7l/3vf00HIk8HW9951wkuWrJEOIG91z67qzvmUsrhrbOeXyne+8pk3z+cCi2Y37/3B1P7h46uPNkhO8uE7T3M+n7Gz5lMm7wEQAA";
	
    public JSONObject requestSearch(String searchText)
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
    
    public List<ListingItem> parseSearchJSON(JSONObject response)
    {
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
	
			ListingItem listingItem = new ListingItem(title, price, imageURL);	
			searchResults.add(listingItem);
		}
		
    	return searchResults;
    }
    
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
    
	public ListingSummary summaryUpdateAndRefresh(ListingSummary sum)
	{				
		//Send fresh search request
		JSONObject response = this.requestSearch(sum.getName());
		List<ListingItem> listingItems = this.parseSearchJSON(response);
		List<Double> mathResults = this.minMaxAvgAlgo(listingItems);	
		
		//Log current price as history log
		SummaryHistoryLog log = new SummaryHistoryLog(sum.getName(), mathResults.get(2), mathResults.get(1), mathResults.get(0));
		this.logService.createHistoryLog(log);
		sum.getHistoryLogs().add(log);
		log.setSummary(sum);
		
		//Set Summary values
		sum.setAverageCost(mathResults.get(2));
		sum.setMaxCost(mathResults.get(1));
		sum.setMinCost(mathResults.get(0));
			
		return this.sumRepo.save(sum);
	}
}
