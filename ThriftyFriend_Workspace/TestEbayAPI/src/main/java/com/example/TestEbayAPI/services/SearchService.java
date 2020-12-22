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
                    .header("Authorization", "Bearer v^1.1#i^1#r^0#p^1#f^0#I^3#t^H4sIAAAAAAAAAOVYa2wUVRTubh9aCiIvIcXIOogBzMzeeexrwq4slMK2hZbuFiimwTuzd9qB2Zlh5i7bjSKlwf4RCCaCiZhQCD9ARK1ArDEGH0B4/UOjJKhRiBKDIUQTNFTjzO5StpW0SDfYxP2zmXPPPff7vnPOvXcGdJSVz+1a0nVznOMhZ3cH6HA6HHQFKC8rfeaRYmdlaRHIc3B0dzzVUdJZfHWeCROKzjciU9dUE7naE4pq8hljkEgaKq9BUzZ5FSaQyWORj4aX1vEMBXjd0LAmagrhilQFCZ8g+QAXQCLnjyPWz1pW9XbMmBYkGM7j5yQBQollOYGmrXHTTKKIamKoYmscMICkGZJhYsDLA45nGYr1c6sJ1wpkmLKmWi4UIEIZuHxmrpGHdWio0DSRga0gRCgSro7WhyNVi5bF5rnzYoVyOkQxxElz4NNCLY5cK6CSREMvY2a8+WhSFJFpEu5QdoWBQfnwbTD3AT8jdQCInMCIYpyhJRYKXEGkrNaMBMRD47AtcpyUMq48UrGM08MpaqkhrEUizj0ts0JEqlz23/IkVGRJRkaQWLQg3BxuaCBCdVAVUTSZIGNthizhdDXZ0FhFsj7W62M9okDSfi/DWfRyC2Wj5WQetNJCTY3Ltmima5mGFyALNRqojYf35GljOdWr9UZYwjaifD9/v4ZgtZ3UbBaTuE2184oSlhCuzOPwGeifjbEhC0mM+iMMHshIFCSgrstxYvBgphZz5dNuBok2jHXe7U6lUlSKpTSj1c0AQLtXLa2Lim0oAQnL1+71rL88/ARSzlARkTXTlHmc1i0s7VatWgDUViLEsYDz+nK6D4QVGmz9hyGPs3tgRxSqQ2iaRgEx7vXQXujxev2F6JBQrkjdNg4kwDSZgMY6hHUFiogUrTpLJpAhx3nWIzGsX0Jk3BuQSC4gSaTgiXtJWkIIICQIYsD/f2qUey31KBINhAtS6wWrc32lsN4bjrT6autqYjXp9cr6Wq2VNcTacKOvGibqa1eJzc1VS2OBmubgvXbD3cmLmo4aNEUW0wVQwO71AqrAGvEGaOB0FCmKZRgRUdMmOrqSbM83rQBQlym7sSlRS7g1aO3otmlNBvGIOId1PZJIJDEUFBQpzG7+H+3kd6UnW3edUcXJyl82kXI8e0mhMtmkzA0iZSBTSxrW/Yyqt8/smLYOqdYOiA1NUZCxgh5xoh90fu1eH0aPf3lY3B/3wt1URlNti4psldCa0cbsgWRUhqPsNKa9wO+lfcDHjIjXwkxOY+nRdg4t0UyM4kNRK1l8n9dq98CX/FBR5kd3Oo6BTkeP0+EAbjCLngmeLCtuKikeW2nKGFEylChTblWtd1cDUetQWoey4Sxz6E3w51l5nxW6W8C0/g8L5cV0Rd5XBvD4nZFSevzUcQygGYYBXsCxzGow885oCf1YyeRvrjJzesY8N4PY9lfljWOR9o/dk26Bcf1ODkdpUUmno+hV6iP9HNju2Jc6yVw71NvmnNq9ALy777B/TnnXF6d+OOPsCVds3n+kacPx9PhL+xvP9809eiLsu7D7jbWHTh9x7DprvHnx8636gdKuaWSndHnTcvx8S7jpwtmyw7NuvXPpx6NnN0ZPUi98uejghb7fN1RNmPCteaSSujbx4KO9x2v6ftmbPvG96DlTOWPK4pWvHKqY3v313qef6Hsx1TJm98Tf9sx+Sy7zTN313vIpH1xs4fR5D2/evmP6hIsvq1vaZ5c3XTn3bLtrR+TyZ2MPTLq55av4fO2lmpi6re3T1/68fuX6tV0V4Z3n+aL3p/16qmHjTpya/9Op714fv6n3kz/e7jl9aQ87/cPYjW62d3I2fX8DaYiXT/ARAAA=")
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
