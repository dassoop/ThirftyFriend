package com.example.TestEbayAPI.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class SearchService 
{
    public JSONObject requestSearch(String searchText)
    {

        HttpResponse<JsonNode> response = null;
        JSONObject responseJSON = null;

        try 
        {
        	response = Unirest.get("https://api.ebay.com/buy/browse/v1/item_summary/search?q=" + searchText + "&limit=100")
                    .header("Authorization", "Token goes here")
                    .asJson();
            
            responseJSON = response.getBody().getObject();
        } 
        catch (UnirestException e) 
        {
            e.printStackTrace();
        } 
        catch(NullPointerException n)
        {
            if(responseJSON.getJSONObject("annual").getJSONObject("fica").getString("amount") == null)
            {
            	
            }
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
