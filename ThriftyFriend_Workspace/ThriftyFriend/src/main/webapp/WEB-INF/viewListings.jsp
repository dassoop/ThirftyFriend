<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
			<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
				rel="stylesheet" 
				integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
				crossorigin="anonymous">
			<link rel="stylesheet" href="/css/style.css">
			<link rel="preconnect" href="https://fonts.gstatic.com">
			<link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">
<!-- ^ COPY HERE AND UP ^	 -->
			
<title>ThriftyFriend - Found Listings</title>
</head>
<body>
<t:wrapper>


<div class="container">

	<div class="shadow-lg p-3 mb-5 bg-white rounded">
		<div class="searchtitle">
			<h3>You searched for: ${searchedText}</h3>	
		</div>
	</div>

	<p>Listings from Ebay</p>
	
	<div class="shadow-lg p-6 mb-5 bg-white rounded">
		<div class="row ebayListings">
			<div class="col-7 listingsTable">
				<table class="table table-light bg-white"> 

					<thhead>
						<th>View on Ebay</th>
						<th>Product Name</th>
						<th>Product Price</th>
						<th></th>
						<%-- <th>Category Name</th>
						<th>Category ID</th> --%>
					</thhead>
					
					<tbody>
						<c:forEach items="${listingItems}" var="listing">
							<tr>
								<td><a href="${listing.linkUrl}">View</a>
								<td>${listing.name}</td>
								<td>${listing.price}</td>
								<td><img src="${listing.imgUrl}" class="itemImg"></td>
								<%-- <td>
									<ul>
										<c:forEach items="${listing.categoryNameList}" var="catId"> 
											<li>${catId}</li>
										</c:forEach>
									</ul>
								</td>
								<td>
									<ul>
										<c:forEach items="${listing.categoryIdList}" var="catId"> 
											<li>${catId}</li>
										</c:forEach>
									</ul>
								</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="col-5 bg-white">

				<table class="table table-light"> 
					<tbody>
						<tr>
							<th>Average Cost</th>
							<td>${averageCost}</td>
						</tr>
						
						<tr>
							<th>Minimum Cost</th>
							<td>${minCost}</td>
						</tr>
						
						<tr>
							<th>Maximum Cost</th>
							<td>${maxCost}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<p>Listings in ThriftyFriend Database</p>
	
	<div class="row databaseListings">
		<div class="col">
			<div class="shadow-lg p-6 mb-5 bg-transparent rounded">	
				<table class="table table-light"> 
						<thhead>
							<th>Summary Name</th>
						</thhead>
						
						<tbody>
							<c:forEach items="${listingSummaries}" var="summary">
								<tr>
									<td><a href="/summary/${summary.id}/view">${summary.name}</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</div>
	
	<div class="createsummaryfoot">
		<p class="redText">${summaryExistsError}</p>
		<h5>If your item does not already exist in our Database, go ahead and add it here.</h5>

		<a href="/summary/create/${searchedText}/${averageCost}/${minCost}/${maxCost}" id="mintbutton1" class="btn btn-primary mintButton">Create Summary</a>
	</div>
	
	<div class="footer">
	
	
	</div>
	
</div>

</t:wrapper>
</body>
</html>