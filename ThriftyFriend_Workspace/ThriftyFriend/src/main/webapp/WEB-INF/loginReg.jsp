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
    <title>ThriftyFriend</title>
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
    <div class="container"><a class="navbar-brand" href="#"><img src="/assets/img/Logo_1.png"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="#">Search</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Dashboard</a></li>
            </ul><span class="navbar-text actions"> <a class="btn btn-light action-button" role="button" href="#" style="background: rgb(93,218,193);">Login | Register</a></span>
        </div>
    </div>
</nav>
<section class="login-clean">
    <form action="/login" method="post">
        <h2 style="font-size: 24px;text-align: center;">Login</h2>
        <div class="illustration"><i class="icon ion-person" style="color: rgb(93,218,193);border-color: rgb(93,218,193);"></i></div>
        <div class="mb-3">
            <input class="form-control" type="email" name="email" placeholder="Email">
        </div>
        <div class="mb-3">
            <input class="form-control" type="password" name="password" placeholder="Password">
        </div>
        <div class="mb-3">
            <button class="btn btn-primary d-block w-100" type="submit" style="background: rgb(93,218,193);">Log In</button>
        </div>
        <a class="forgot" href="#">Forgot your email or password?</a>
    </form>

</section>
<section class="register-photo">
    <div class="form-container">
        <div class="image-holder"></div>
        <form:form action="/register" method="POST" modelAttribute="user">
            <h2 class="text-center"><strong>Create</strong> an account.</h2>
            <div class="mb-3">
                <form:input path="name" class="form-control" type="text" placeholder="Username"/>
            </div>
            <div class="mb-3">
                <form:input path="email" class="form-control" type="email" name="email" placeholder="Email"/>
            </div>
            <div class="mb-3">
                <form:input path="password" class="form-control" type="password" name="password" placeholder="Password" value=" "/>
            </div>
            <div class="mb-3">
                <form:input path="passwordConfirmation" class="form-control" type="password" name="password-repeat" placeholder="Password (repeat)"/>
            </div>
            <div class="mb-3">
                <div class="form-check">
                    <label class="form-check-label"><input class="form-check-input" type="checkbox">I agree to the license terms.</label>
                </div>
            </div>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit" style="background: rgb(93,218,193);">Sign Up</button></div><a class="already" href="#">You already have an account? Login here.</a>
        </form:form>
    </div>
</section>
<footer class="footer-basic">
    <div class="social"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div>
    <ul class="list-inline">
        <li class="list-inline-item"><a href="#">Home</a></li>
        <li class="list-inline-item"><a href="#">Services</a></li>
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="#">Terms</a></li>
        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
    </ul>
    <p class="copyright">Company Name © 2021</p>
</footer>
<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>