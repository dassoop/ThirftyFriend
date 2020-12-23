package com.example.ThriftyFriend.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="listingSummaries")
public class ListingSummary 
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
	private Date updatedAt;
	
	@OneToMany(mappedBy="summary", fetch = FetchType.LAZY)
	private List<SummaryHistoryLog> historyLogs;
	
	//Watchlist - Table of many ListingSummaries to many users
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="watchlist",
				joinColumns = @JoinColumn(name="summary_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<ListingSummary> watchlist;
	
	@PrePersist
	protected void createdAt()
	{
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void updatedAt()
	{
		this.updatedAt = new Date();
	}
	
	public ListingSummary()
	{
		
	}
	
	public ListingSummary(String name, double averageCost, double minCost, double maxCost)
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
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<SummaryHistoryLog> getHistoryLogs() {
		return historyLogs;
	}
	public void setHistoryLogs(List<SummaryHistoryLog> historyLogs) {
		this.historyLogs = historyLogs;
	}
}
