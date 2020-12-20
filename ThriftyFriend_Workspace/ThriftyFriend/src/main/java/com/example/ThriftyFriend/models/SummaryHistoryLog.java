package com.example.ThriftyFriend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="summaryHistoryLogs")
public class SummaryHistoryLog 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double averageCost;
	private double minCost;
	private double maxCost;
	
	@Column(updatable=false)
	private Date createdAt;
	
	@PrePersist
	protected void createdAt()
	{
		this.createdAt = new Date();
	}
	
	public SummaryHistoryLog()
	{
		
	}
	
	public SummaryHistoryLog(String name, double averageCost, double minCost, double maxCost)
	{
		this.name = name;
		this.averageCost = averageCost;
		this.minCost = minCost;
		this.maxCost = maxCost;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}
	public double getMinCost() {
		return minCost;
	}
	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}
	public double getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(double maxCost) {
		this.maxCost = maxCost;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
