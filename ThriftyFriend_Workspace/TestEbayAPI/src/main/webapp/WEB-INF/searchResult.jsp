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
<title>Ebay API Test Search Result</title>
</head>
<body>
<div class="container">

	<div class="row">
	
		<div class="col" style="margin-top:20%;">
			<h4>Search Result</h4>
			
			<table class="table table-striped table-dark">
				<thead>
					<th>Name</th>
					<th>Price</th>
					<th>Market Price</th>
					<th>Img URL</th>
				</thead>
				
				<tbody>
				
				<c:forEach items="${searchResults}" var="result">
					<tr>
						<td>${result.title}</td>
						<td>${result.price}</td>
						<td>${result.marketPrice}</td>
						<td><img src="${result.imageUrl}" alt="product image"></td>
					</tr>
				</c:forEach>
				
				</tbody>
			</table>
		
		</div>
	
	</div>

</div>
	

</body>
</html>