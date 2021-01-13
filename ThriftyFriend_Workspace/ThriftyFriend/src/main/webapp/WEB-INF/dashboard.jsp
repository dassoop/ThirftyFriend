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
			
<title>ThriftyFriend - Search Listings</title>
</head>
<body>
<t:wrapper>

<div class="container" id="biggerwatchlist">
	<div class="row">
	
		<div class="col shadow-lg rounded bg-white" id="watchlistcontainer">
			<h3>Watchlist</h3>
				<table class="table table-light"> 
					<thead>
						<th>Product Name</th>
						<th>Action</th>
					</thead>
				
					<tbody>
					<c:forEach items="${user.listings}" var="item">
					<tr>
						<td>${item.name}</td>
						<td><a href="/summary/${item.id}/view" id="mintbutton1" class="btn btn-primary mintButton">View</a> | <a href="/summary/remove/${item.id}" id="mintbutton1" class="btn btn-primary mintButton">Remove From Watch List</a></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
		
		
		</div>
<!-- 		
		<div class="col">
		
		
		
		</div>
		 -->
	</div> 
</div>

</t:wrapper>
</body>
</html>