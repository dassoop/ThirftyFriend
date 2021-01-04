<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
			<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
				rel="stylesheet" 
				integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
				crossorigin="anonymous">
			<link rel="stylesheet" href="/css/style.css">
			<link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">
<title>Wrapper</title>
</head>
	<body>
	<div class="wrapper">
		<div class="container-fluid banner bg-transparent">
		
		
			<div class="row">
				<div class="col-md-12">
					<nav class="navbar bg-white">
					
						<div class="navbar-brand bg-white">			
							<img src="/img/Logo_1.png" alt="Italian Trulli" style="height:80px; padding:0px; margin:0px;">
						</div>
						
						<ul class="nav">
						
							<li class="nav-item">
								<a class="nav-link" href="/">Home</a>
							</li>
						
							<li class="nav-item">
								<a class="nav-link" href="/dashboard">Dashboard</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link" href="/loginReg">Login/Reg</a>
							</li>
						
							<li class="nav-item">
								<a class="nav-link" href="/logout">Logout</a>
							</li>
						</ul>					
							
							
						
					</nav>
				</div>
			</div>
			
			<div class="row">
				<div class="col-10">
				
				</div>
				<div class="col-2">
					<p class="redText" id="notLoggedError">${notLoggedError}</p>
					<c:choose>
							<c:when test="${user.name != null}">
								<p><b>Logged in as - </b>${user.name}</p>
							</c:when>
							<c:otherwise>
								<p><b>No user logged in</b></p>
							</c:otherwise>
						</c:choose>	
				</div>
					
			</div>
			
			
		
		
		
		
		
		
		
		
		
			<div class="row">
				
<%-- 				<div class="col">
					<div id="user-name-display">
						<c:choose>
							<c:when test="${user.name != null}">
								<h4>Logged in as ${user.name}</h4>
							</c:when>
							<c:otherwise>
								<h4>No user logged in</h4>
							</c:otherwise>
						</c:choose>	
						<p class="redText">${notLoggedError}</p>
					</div>
				</div>
				<hr>
			</div> --%>
	
				<jsp:doBody/>
				
			
			<div class="row">
				<div id="footer">
					
				
				</div>
			
			</div>	
		</div>
	</div>	
	</div>
	</body>

</html>
