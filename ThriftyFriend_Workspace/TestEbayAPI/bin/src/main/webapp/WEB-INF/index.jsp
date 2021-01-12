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
<title>Ebay API Test Index</title>
</head>
<body>
<div class="container">

	<div class="row">
	
		<div class="col" style="margin-top:20%;">
			<form action="/searchRequest" method="POST">
				<div class="form-group">
					<input id="search" name="searchText" class="form-control"/>
					<button class="btn btn-primary">Search</button>
				</div>
			</form> 
		
		</div>
	
	</div>

</div>
	

</body>
</html>