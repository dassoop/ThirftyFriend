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
			<link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
			<link rel="stylesheet" href="/assets/fonts/ionicons.min.css">
			<link rel="stylesheet" href="/assets/fonts/simple-line-icons.min.css">
			<link rel="stylesheet" href="/assets/css/Footer-Basic.css">
			<link rel="stylesheet" href="/assets/css/Login-Form-Clean.css">
			<link rel="stylesheet" href="/assets/css/Navigation-with-Button.css">
			<link rel="stylesheet" href="/assets/css/Registration-Form-with-Photo.css">
			<link rel="stylesheet" href="/assets/css/stylesheet.css">
			<link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">
<title>Wrapper</title>
</head>
	<body>
	<div class="wrapper">
		<nav class="navbar navbar-light navbar-expand-lg navigation-clean-button" style="padding: 0;margin: 0;background: rgb(255,255,255);">
			<div class="container"><a class="navbar-brand" href="/"><img src="/assets/img/Logo_1.png"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
				<div class="collapse navbar-collapse" id="navcol-1">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" href="/">Search</a></li>
						<li class="nav-item"><a class="nav-link" href="/dashboard">Dashboard</a></li>
					</ul><span class="navbar-text actions"> <a class="btn btn-light action-button" role="button" href="#" style="background: rgb(93,218,193);">Login | Register</a></span>
				</div>
			</div>
		</nav>
				
				<!-- </div> -->
				<div class="col" id="user-status">
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

			<div class="container">
				<jsp:doBody/>
				<div class="row">

					<div id="footer">

					</div>

				</div>
			</div>
<!-- bootstrap js -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



</body>
</html>
 