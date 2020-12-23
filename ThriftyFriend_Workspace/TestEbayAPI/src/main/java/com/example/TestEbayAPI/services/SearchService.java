package com.example.TestEbayAPI.services;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class SearchService 
{
	
	private String token = "v^1.1#i^1#p^1#I^3#r^0#f^0#t^H4sIAAAAAAAAAOVYa2wUVRTu9JlSqjaC5aF0mcoj6MzemdnnpLthaaksu33QXWqLGnJ35k47dl6dO0u7iFobJYaKBk1MVEhKAiYaTDCVKD+IIcEfhgQ1QUk08ZFofIQfJAYJEcSZ3aVsK2mRbrCJ+2cz55577vd955x77wwYqaxet3vT7ku1RFXp+AgYKSUIpgZUV1Y8dFdZ6bKKElDgQIyPPDhSPlr2SxOGqmLwXQgbuoaRa1hVNMxnjSEybWq8DrGMeQ2qCPOWwCcibXGepQFvmLqlC7pCuqItIZJjBQ6CgOQXQEoEImtbtesxk3qIFIKS3yNxoj8QTAVSnGSPY5xGUQ1bULNCJAtYQDEsxXJJAHjWz7OA9gbZbaSrG5lY1jXbhQZkOAuXz841C7DODBVijEzLDkKGo5HWREck2rKxPdnkLogVzuuQsKCVxlOfmnURubqhkkYzL4Oz3nwiLQgIY9Idzq0wNSgfuQ7mNuDnpA4EJV9AknwpJIleQSiKlK26qUJrZhyORRYpKevKI82SrcxsitpqpJ5EgpV/ardDRFtczt+WNFRkSUZmiNy4IdIb6ewkw3GoCSiRVqlkvylLVqaV6uxqoTg/5/NzXiFFMQEf67Hp5RfKRcvLPG2lZl0TZUc07GrXrQ3IRo2ma8MUaGM7dWgdZkSyHESFfux1DQPBbU5Sc1lMW/2ak1ek2kK4so+zZ2BytmWZciptockI0weyEoVIaBiySE4fzNZivnyGcYjstyyDd7uHhoboIY7WzT43CwDj7mmLJ4R+pELS9nV6Pecvzz6BkrNUBGTPxDJvZQwby7BdqzYArY8Mezjg8fnzuk+FFZ5u/YehgLN7akcUq0MkjygJAZ/o9QIAEULF6JBwvkjdDg6UghlKheYAsgwFCogS7DpLq8iURZ7zSiwXkBAl+oIS5QlKEpXyij6KkRACCKVSQjDwf2qUWy31BBJMZBWl1otW58ajqUFfJNrnj8U3JzdnBpXBmN7HmUIs0uVvhWpHrEfo7W1pSwY394ZutRtuTl7QDdSpK7KQKYICTq8XUQXOFDuhaWUSSFFsw5yIYofo/EqyMx/bAaAh005j04KuunVo7+iOaXsW8Zw4RwwjqqppC6YUFC3Obv4f7eQ3pSfbd515xcnOXy6Rspi7pNDZbNJ4h0CbCOtp076f0R3OmZ3UB5Bm74CWqSsKMruZOSf6TufX6fVZ9PiXh8XtcS/eTWU+1bagyHYJbZ9vzO5IRmU4z05jxgcCvgBnM5sTr+ZsTpOZ+XYObdKxhcSZqJU/cpvXavfUl/xwSfbHjBLHwCjxfilBADdYxTSClZVlW8vLFi7DsoVoGUo0lvs0+93VRPQAyhhQNksrCWMr/G1VwWeF8SfAkskPC9VlTE3BVwZw/42RCubu+loWMCzLAcD6WbANNN4YLWfuK19Uf/TwnrHu0/UT+3aO/7HrdwY0flYKaiedCKKipHyUKNnx0/jln3euh/Gjy2O47sTQyYanLiD33uEVb64xVvy6f7CuVXx27xVi/44jRxaUyMSEwgd3ucGEp2HxV8vv+fY8r515Jdh07oeYvyROn+xcfYEiDrwrv6C+tPL1PYuO3nsNfPTwB1ef878cXLL7a1TX8snFhVcf2xpp+n6w5vTBfatPLBtZsyTcY2oXOl4bfaNeO4ef3tP8xdnj6jH5y0vPM0T76tDasfUPfEi+ejF9/uOzTUNterTm1LnP60bh2IvHr6xbHEv++MK1xwd4rqqKTH3XkKxdnlz6KXPqYODthsojYEtPTc839Jn2CfY9eGhtV/Pl7sONhw+NHTr/zIF3/vqzumpBfOlbufT9Dd235TDwEQAA";
			
    public JSONObject requestSearch(String searchText)
    {
    	List<String> searchTextSeperated = Arrays.asList(searchText.split(" "));
    	String combinedString = "";
        for(int i = 0; i < searchTextSeperated.size(); i++)
        {
        	combinedString = combinedString + "_" + searchTextSeperated.get(i);
        }	

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
//            if(responseJSON.getJSONObject("marketingPrice")== null)
//            {
//            	
//            }
        }

        return responseJSON;
    }
    
    
//  public JSONObject requestToken()
//  {
//
//      HttpResponse<JsonNode> response = null;
//      JSONObject responseJSON = null;
//      String body = "body here";
//
//      try 
//      {
//      	response = Unirest.get("")
//                  .header("Content-Type", "application/x-www-form-urlencoded")
  
//                  .asJson();
//          
//          responseJSON = response.getBody().getObject();
//      } 
//      catch (UnirestException e)
//      {
//          e.printStackTrace();
//      } 
//      catch(NullPointerException n)
//      {
//          if(responseJSON.getJSONObject("annual").getJSONObject("fica").getString("amount") == null)
//          {
//          	
//          }
//      }
//
//
//      return responseJSON;
//  }	
}
