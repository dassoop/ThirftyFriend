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
	
	private String token = "v^1.1#i^1#r^0#I^3#p^1#f^0#t^H4sIAAAAAAAAAOVYW2wUVRjebbdFhNZoCCDhYZnaxEh39szMXmYHdnXptrC90NLdIq0iOTNzth06OzPMnHXZarQ0oYIYLppACD40RIEgkvBAUAQvPKBBJAYQQ4QHEy+EFy+JEpSiM7tL2VbSIt1gE/dlM//5z3++//v+/5wzA/rKpz4xsGTgWoV9SslgH+grsdupaWBqedn8ytKSOWU2UOBgH+x7rM/RX3ploQGTssa1IUNTFQM51yZlxeCyxiCR0hVOhYZkcApMIoPDAhcLNzdxNAk4TVexKqgy4YxGgoRIB2ivCAUa8QHE8oxpVW7FjKtBghUYnmV8kPWyrIf3Bcxxw0ihqGJgqOAgQQMauCjaRdNxGnBelqMpkmGoTsK5HOmGpCqmCwmIUBYul52rF2AdGyo0DKRjMwgRiobrYy3haKRuaXyhuyBWKM9DDEOcMkY+1aoici6HcgqNvYyR9eZiKUFAhkG4Q7kVRgblwrfA3AP8LNWMTwSQ9gf4AKS8LPIUhcp6VU9CPDYOyyKJrkTWlUMKlnBmPEZNNvjVSMD5p6VmiGjEaf0tS0FZSkhIDxJ1i8Id4dZWItQEFQHFUklXvFuXEjhT72pti7gYP+PzM16Bd1Gsj/aY6eUXykXL0zxqpVpVESWLNMO5VMWLkIkajebGU8CN6dSitOjhBLYQFfrRtzikfZ2WqDkVU7hbsXRFSZMIZ/ZxfAWGZ2OsS3wKo+EIoweyFAUJqGmSSIwezNZivnzWGkGiG2ONc7vT6TSZZkhV73LTAFDuFc1NMaEbJSFh+lq9nvOXxp/gkrKpCMicaUgczmgmlrVmrZoAlC4i5GGAx+fP8z4SVmi09R+GgpzdIzuiWB0SoER/ghKAX4BIFGFROiSUL1K3hQPxMONKQr0HYU2GAnIJZp2lkkiXRI7xJmiGTSCX6AskXJ5AIuHivaLPRSUQAgjxvBBg/0+NcrelHkOCjnBRar1oda49za/xhaNd/samhnhDZo28plHtYnShMdzmr4fJlsYVQkdHpDkeaOgI3m033Dl5QdVQqypLQqYIDFi9XkQWGF1shTrOxJAsm4YJJWpYiU4uka35hhkAahJpNTYpqEm3Cs0d3TKtyiKeUM5hTYsmkykMeRlFi7Ob/0c7+R3Tk8y7zqTKydQvJ6Qk5i4pZFZN0nheIHVkqCndvJ+RLdaZHVd7kGLugFhXZRnpy6kJC32/9bV6fRw+/uVhcW+5F++mMplqW5Als4RWTbbM7ouiEpxkpzHlA6zPD/xgYl1am9U0npls59AS1cBIHCs1x+J7vFa7R77kh2zZH9VvPwz67YdK7HbgBtVUFZhXXtruKJ0+x5AwIiWYIA2pSzHfXXVE9qCMBiW9pNyutcOr1QWfFQZXgtnDHxamllLTCr4ygLm3R8qoh2ZV0ICizR/wsjTVCapujzqomY4Zv1W89eSyy5VHBjafPXNw3rEv+Gd9GqgYdrLby2yOfrttR3D+jup1J+Tt8kvbt9y8dkn59NC0We4a5/lH3vlr30Av9ejNuuCRG5u+eXnvxqO/zv6guaHv6vr4/vlnWofOHb9gO6l9uelhZ+zzmR+2whsqfg29uvOPBav9+3bM3d225fqywaEj7s7ptmORfWD1ninvpRtjjv01B7ZWHjizMKANfVT+QuLi9++/uO3jvbM3rHSUHIyTwtfHNsDMxoYDm3+pe6Z3l3+z7RO29ujZB6tORNyn1lcGN7W/XnbueEXvU75TP9kbTp7u+eHn3RffPv/GlW+HpsxbmV7/+/V3Q901fw6dDly1P36pfc+KGTvZPbueqz7cW1PFffXdZ+kfccvWxRcuPbBgnfNyf/cr0Tdz8v0NbKoa1fARAAA=";
			
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
