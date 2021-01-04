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
			
<title>ThriftyFriend - View Summary</title>
</head>
<body>
<t:wrapper>

<div class="container">

	<div class="row">
	
		<div class="col shadow-lg rounded">
			<h5>${summary.name}</h5>
				<table class="table table-light"> 
					<tbody>
						<tr>
							<th>Average Cost</th>
							<td>${summary.averageCost}</td>
						</tr>
						
						<tr>
							<th>Minimum Cost</th>
							<td>${summary.minCost}</td>
						</tr>
						
						<tr>
							<th>Maximum Cost</th>
							<td>${summary.maxCost}</td>
						</tr>
						
						<tr>
							<th>Updated At</th>
							<td><fmt:formatDate value="${summary.updatedAt}" type="both"/></td>
						</tr>
					</tbody>
				</table>
				
				<div class="historyLogDisplay">
					<table class="table table-light"> 
						<thead>
							<th>Date</th>
							<th>Amount</th>
						</thead>
					
						<tbody>
							<c:forEach items="${historyLogs}" var="log">
								<tr>
									<td><fmt:formatDate value="${log.createdAt}" type="both"/></td>
									<td>${log.averageCost}</td>
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

</t:wrapper>
</body>
</html>