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
			<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
			<link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">
			
<title>ThriftyFriend - View Summary</title>
</head>
<body>
<t:wrapper>

<div class="container" id="summarytop">

	<div class="row">
	
		<div class="col shadow-lg rounded bg-white" id="summarycontent">
			<h5>${summary.name}</h5>
				<table class="table table-light"> 
					<tbody>
						<tr>
							<th>Average Cost</th>
							<td><fmt:formatNumber value = "${summary.averageCost}" type="currency"/></td>
						</tr>
						
						<tr>
							<th>Minimum Cost</th>
							<td><fmt:formatNumber value = "${summary.minCost}" type="currency"/></td>
						</tr>
						
						<tr>
							<th>Maximum Cost</th>
							<td><fmt:formatNumber value = "${summary.maxCost}" type="currency"/></td>
						</tr>
						
						<%-- <tr>
							<th>Updated At</th>
							<td><fmt:formatDate value="${summary.updatedAt}" type="both"/></td>
						</tr> --%>
					</tbody>
				</table>
				<c:choose>
					<c:when test="${summary.user_watchlist.contains(user)}">
						<a href="/summary/remove/${summary.id}" id="mintbutton1" class="btn btn-primary mintButton">Remove From Watch List</a>
					</c:when>
					<c:otherwise>
						<a href="/summary/add/${summary.id}" id="mintbutton1" class="btn btn-primary mintButton">Add To Watch List</a>
					</c:otherwise>
				</c:choose>
				<span class="redText">${watchlistError}</span>
				<div class="historyLogDisplay" style="display:none">
					<table class="table table-light" > 
						<thead>
							<th>Date</th>
							<th>Amount</th>
						</thead>
					
						<tbody>
							<c:forEach items="${summary.historyLogs}" var="log">
								<tr>
									<td class = 'columnDate'><fmt:formatDate value="${log.createdAt}" type="both"/></td>
									<td class = 'columnPrice'>${log.averageCost}</td>
								</tr>   
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
		
		</div>
		
		<div class="col">
		
		
		
		</div>
		
	</div> 

</div>
<div class = "container" id="summarygraph">
	<canvas id="chart"></canvas>
</div>
</t:wrapper>
<script src="/Javascript/graph.js"></script>
</body>
</html>