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
			
<title>ThriftyFriend - Home Page</title>
</head>
<body>
<t:wrapper>


<div class="container">

	<div class="row">
		<div class="col-md-8 offset-md-2" align="center">
				<form action="/fakeSearchRequest" method="POST">
					<div class="form-group">
						<label for="search"></label>
						<input id="search" name="search" class="form-control" placeholder="Search for your product...">	
						<button class="btn btn-primary mintButton">Search</button>
					</div>
					


				</form> 
			
		</div>
	</div>
</div>

</t:wrapper>
</body>
</html>