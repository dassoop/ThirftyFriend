package com.example.ThriftyFriend.models;

import java.util.List;

public class ListingItem 
{
	private String name;
	private double price;
	private String imgUrl;
	private List<String> categoryIdList;

	public ListingItem()
	{
		
	}
	
	public ListingItem(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	
	public ListingItem(String name, double price, String imgUrl, List<String> categoryIdList)
	{
		this.name = name;
		this.price = price;
		this.imgUrl = imgUrl;
		this.categoryIdList = categoryIdList;
	}
	
	//GETTERS AND SETTERS
	public String getName() 
	{
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<String> getCategoryIdList() {
		return categoryIdList;
	}

	public void setCategoryIdList(List<String> categoryIdList) {
		this.categoryIdList = categoryIdList;
	}

	

}
