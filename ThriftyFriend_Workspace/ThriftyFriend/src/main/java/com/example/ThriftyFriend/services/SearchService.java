package com.example.ThriftyFriend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
	
	//private String token = "v^1.1#i^1#p^1#I^3#f^0#r^0#t^H4sIAAAAAAAAAOVYfWwTZRhvu644cBiT+ZGppBxDjeSu79312utBGwtj0K1sde2Azhi83r23Hbuv3V3dSkZSFlngD0Iw8Q8MhGFiiBj5UEyEBP0LiCKi/uNXIkENQYh8RAUEo961ZXSTbMgaXGL/ae55n/d5n9/v9zzv+96BvKfmmaGlQ1drndNcw3mQdzmd+AxQ46meN7PKVV/tAGUOzuF8Q949WHV2gcHKksa0Q0NTFQN6+2VJMZiCMYxkdYVRWUM0GIWVocGYHJOMLoszBAYYTVdNlVMlxBtrDCMZQPozIAj8APJkEPotq3IzZkoNIwRFkxxPCX6OotgARVjjhpGFMcUwWcW0xgEBUJxACTIFaIakGYrCaJLoRLzLoW6IqmK5YACJFNJlCnP1slzHT5U1DKibVhAkEos2JduiscbFrakFvrJYkRIPSZM1s8bop0UqD73LWSkLx1/GKHgzySzHQcNAfJHiCqODMtGbydxF+gWq/RmC4DMsIYT4IE4BuiJUNqm6zJrj52FbRB4VCq4MVEzRzE3EqMVGZjXkzNJTqxUi1ui1/57LspIoiFAPI4sXRtPRRAKJxFmFg8msjKa6dVEwc01oor0RJYNkIEhSXAbF6QDht+CVFipGK9E8ZqVFqsKLNmmGt1U1F0IraziWG6KMG8upTWnTo4JpZ1Tmh4ObHBJ0py1qUcWs2a3YukLZIsJbeJxYgZHZpqmLmawJRyKMHShQFEZYTRN5ZOxgoRZL5dNvhJFu09QYn6+vrw/rIzFV7/IRAOC+lcviSa4byixi+dq9XvQXJ56AigUoHLRmGiJj5jQrl36rVq0ElC4k4ieBPxAs8T46rchY6z8MZZh9ozuiUh2CUywXoDmBxv00DPqFSnRIpFSkPjsPmGFzqMzqPdDUJJaDKGfVWVaGusgzJCUQJC1AlA+EBNQfEgQ0Q/EBFBcgBBBmMlyI/j81yp2WehJyOjQrUusVq3NtRaY3EI11BVvizanmXK/U26J2kTrXEm0PNrFyW8tKLp1uXJYKNafDd9oNtwfPqRpMqJLI5SrAgN3rFWSB1PkEq5u5JJQkyzApoIYNdGqJbM83rACsJmJ2Y2OcKvtU1trRbdOqQsaTwhzVtJgsZ002I8FYZXbz/2gnvy080brrTClMln5FIUW+eEnBCmpixkscpkNDzerW/Qxrs8/slNoDFWsHNHVVkqC+HJ+00PdaX7vXJ+DjXx4Wd4e9cjeVqVTbnCRaJbRqqiG7J4qK7BQ7jfEAoIM4YUGcFK5FBU1Tual2Di1VDRPy40FzL7nLa7Vv9Et+xFH44YPO98Cgc7/L6QQ+MBefA2Z7qjrcVffXG6IJMZEVMEPsUqx3Vx1iPTCnsaLu8ji1Dvbc3LLPCsMvgEdHPizUVOEzyr4ygMdvjVTjDzxSSwCcIEhAkzRFdYI5t0bd+MPuuidWbNp/YmNf6tvj59e6v3p2WvuNs2lQO+LkdFY73INOR+uxD/ADQx2r1+yWB+6rp3tbhrTfapec/zy0a+eBupPfadebux8zhKptbq/n2o0P9zh6wCXPnovX/qjl8I8uf3+27s939YGOjfiRfReuyOtf/vTI3sP1dXvn10ca/pLnr0PfmP701UNHT7wy4+uEuuPJDcdOr5338U/7IrucOzpX/pB//puDq5tW1Hz2VrW/Zy07Cz207dyG9NtbauKLLzVdiU6f232qPb99+rU3Zw/83HbhzC814FfHRsc7W188PDS8vmGeN3AwHU8MHHlqZ6jvlGuA3/P++Qdd607vPt5M5vrJq1/OvCx9cr3l6N5XNw///tCx/h3bN2/98eKsTXHP7u1fnDl4asvrO1+LnVxTlO9vnP7UhPARAAA=";
	private String token = "";
	
	public JSONObject getAuthToken() {
		HttpResponse<JsonNode> response = null;
		JSONObject jsonResponse = null;
		final String credentials = "KellyHow-ThriftyF-PRD-a736b19af-f0dcc13b:PRD-736b19afa887-f072-4d6e-a737-7572";
			try {
				response = Unirest.post("https://api.ebay.com/identity/v1/oauth2/token")
						   .header("Content-Type", "application/x-www-form-urlencoded")
						   .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString(credentials.getBytes()))
						   .body("grant_type=client_credentials&scope=https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope")
						   .asJson();
				//assertEquals(201, response.getStatus());
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonResponse = response.getBody().getObject();
			token = jsonResponse.getString("access_token"); 
			return jsonResponse;
	}
	//Main method to send search request to the Ebay API. Returns JSON Object of product listings with prices.
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
    
    //Parse through the JSON Object returned from the Ebay API and assign it to temporary ListingItem Objects
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
