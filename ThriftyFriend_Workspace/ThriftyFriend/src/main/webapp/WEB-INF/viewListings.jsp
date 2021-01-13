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
			<h3>Showing results for: ${searchedText}</h3>	
		</div>
	</div>

	<p>Listings from Ebay</p>
	
	<div class="shadow-lg p-6 mb-5 bg-white rounded">
		<div class="row ebayListings bg-white rounded" id="ebaylistings">
			<div class="col-9 listingsTable">
				<table class="table table-light bg-white rounded" > 
				
					<thead class="bg-white" id="tableheadlistings">
						<tr id="trlistings">
							<%-- <th>View on Ebay</th> --%>
							<th class="thlistings">Product Name</th>
							<th class="thlistings">Product Price</th>
							<th class="thlistings"></th>
							
							<%-- <th>Category Name</th>
							<th>Category ID</th> --%>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${listingItems}" var="listing">
							<tr>
								
								<td>${listing.name} <p><a href="${listing.linkUrl}" id="mintbutton2" class="btn btn-primary mintButton">View</a></p></td>
								<td><fmt:formatNumber value = "${listing.price}" type="currency"/></td>
								<td><a href="${listing.linkUrl}"><img src="${listing.imgUrl}" class="itemImg"></a></td>
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
			
			<div class="col-3 bg-white" id="averageprices">

				<table class="table table-light bg-transparent" id="averagepricestable"> 
					<tbody>
						<tr class="trprices">
							<th>Average Cost</th>
							<td><fmt:formatNumber value = "${averageCost}" type="currency"/></td>
						</tr>
						
						<tr class="trprices">
							<th>Minimum Cost</th>
							<td><fmt:formatNumber value = "${minCost}" type="currency"/></td>
						</tr>
						
						<tr class="trprices">
							<th>Maximum Cost</th>
							<td><fmt:formatNumber value = "${maxCost}" type="currency"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
		<p>Refine your search: <p>
	
	<div class="row shadow-lg p-6 mb-5 bg-white rounded" id="categoriesforsearch">
	<table> 
						<thead>
							<tr>
								<th id="categorytablehead">Suggested categories for your search</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${categories}" var="category">
								<tr>
									<td>
										<form action="/searchRequest/name" method="GET">

												<label for="search"></label>
												<input type="hidden" name="search" id="search" value="${searchedText}"/>
												<label for="catId"></label>
												<input type="hidden" name="catId" id="catId" value="${category.key}"/>
												<input type="submit" value="${category.value}" id="mintbutton3" class="btn btn-primary mintButton"/>
												<a></a>	
										</form>
									</td> <!-- link will go to get mapping in controller -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
	</div>
	
	<p>Listings in ThriftyFriend Database</p>
	<div class="row databaseListings bg-transparent" id="summaryname">
		<div class="col bg-transparent" >
			<div class="shadow-lg p-6 mb-5 bg-white rounded" id="summariesonsearch">	
			
				<table class="table table-light rounded"> 
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