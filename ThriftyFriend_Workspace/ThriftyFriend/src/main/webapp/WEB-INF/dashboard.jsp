<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Thrifty Friend | Dashboard</title>
    <meta name="description" content="Thrifty Friend is a tool to search for and catalog average prices of items on ebay.">
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
    <link rel="stylesheet" href="/assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="/assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="/assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="/assets/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="/assets/css/stylesheet.css">
</head>

<body>
<nav class="navbar navbar-light navbar-expand-lg navigation-clean-button" style="padding: 0;margin: 0;background: rgb(255,255,255);">
    <div class="container"><a class="navbar-brand" href="/"><img src="/assets/img/Logo_1.png"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="/">Search</a></li>
                <li class="nav-item"><a class="nav-link active" href="/dashboard">Dashboard</a></li>
            </ul>
            <span class="navbar-text actions">
                <a class="btn btn-light action-button" role="button" href="/loginReg" style="background: rgb(93,218,193);">Login | Register</a>
                <a class="nav-link" href="/logout" style="display: inline;">Log Out</a>
            </span>
        </div>
    </div>
</nav>
<div class="screen-panel">
    <div class="card card-center" style="margin: 50px auto 0;height: 60vh; overflow-y: auto;">
        <div class="card-body" style="padding: 40px;box-shadow: 0px 0px 16px rgba(33,37,41,0.28);">
            <h4 class="card-title">${user.name}'s Watchlist</h4>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user.listings}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td><a href="/summary/${item.id}/view" class="btn btn-primary">View</a> | <a href="/summary/remove/${item.id}" class="btn btn-primary">Remove</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div>
    <footer class="footer-basic">
        <div class="social"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="/">Home</a></li>
            <li class="list-inline-item"><a href="#">Services</a></li>
            <li class="list-inline-item"><a href="#">About</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        </ul>
        <p class="copyright">Thrifty Friend &#169 2021</p>
    </footer>
</div>
<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>