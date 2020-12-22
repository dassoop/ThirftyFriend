package com.example.TestEbayAPI.models;

public class SearchResult 
{
	public String title;
	public String price;
	public String marketPrice;
	public String imageUrl;
	
	public SearchResult()
	{
		
	}
	
	public SearchResult(String title, String price, String marketPrice, String imageURL)
	{
		this.title = title;
		this.price = price;
		this.marketPrice = marketPrice;
		this.imageUrl = imageURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	


}
