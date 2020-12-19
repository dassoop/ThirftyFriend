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
			
<title>ThriftyFriend - Login/Register</title>
</head>
<body>
<t:wrapper>
<%-- Date: <fmt:formatDate value="${event.date}" pattern="EEEE, MMM-dd-yyyy"/> --%>

<div class="container">
	<div class="row">
		<div class="col">
			<div class="box">
				<div class="shadow-lg p-3 mb-5 bg-transparent rounded">
					<div class="form">
							<h1>Register User</h1>
						
							<!-- TEXT -->
							<form:form action="/register" method="POST" modelAttribute="user">
							<div class="form-group">
								<form:label path="name">Name:</form:label>
								<form:errors path="name" class="redText"/>
								<form:input path="name" class="form-control"/>	
							</div>
															
							<!-- EMAIL -->
							<div class="form-group">
								<form:label path="email">Email:</form:label> 
								<form:errors path="email" class="redText"/>
								<form:input path="email" class="form-control"/>
							</div>
							
							<!-- PASSWORD -->
							<div class="form-group">
								<form:label path="password">Password:</form:label> 
								<form:errors path="password" class="redText"/>
								<form:password path="password" class="form-control"/>
							</div>
							<!-- PASSWORD CONFIRMATION -->
							<div class="form-group">
								<form:label path="passwordConfirmation">Password Confirmation:</form:label> 
								<form:errors path="passwordConfirmation" class="redText"/>
								<form:password path="passwordConfirmation" class="form-control"/>
							</div>
							
								<button class="btn btn-primary mintButton">Register User</button>
							</form:form>
					</div>
				</div> 
			</div>
		</div>
		
		<div class="col">
		<div class="box">
			<div class="shadow-lg p-3 mb-5 bg-transparent rounded">
				<h1>Login</h1>
				
				<form action="/login" method="POST">
					<div class="form-group">
						<label for="email">Email:</label><span class="redText">${loginError} ${alreadyLoggedError} ${loginBlankError} ${userNotFoundError}</span>
						<input id="email" name="email" class="form-control"></textarea> 
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<input type="password" id="password" name="password" class="form-control"></textarea> 
					</div>
					
					<button class="btn btn-primary mintButton">Login</button>
				</form> 
				
			</div>
		</div>
		</div>
	</div>
</div>

</t:wrapper>
</body>
</html>