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
    <title>ThriftyFriend | View Listings</title>
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
                <li class="nav-item"><a class="nav-link" href="/dashboard">Dashboard</a></li>
            </ul>
            <span class="navbar-text actions">
                <a class="btn btn-light action-button" role="button" href="/loginReg" style="background: rgb(93,218,193);">Login | Register</a>
                <a class="nav-link" href="/logout" style="display: inline;">Log Out</a>
            </span>
        </div>
    </div>
</nav>
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-12 m-auto" style="width: 500px;">
            <h3>Showing results for : ${searchedText}</h3>
        </div>
    </div>
    <h4 class="section-heading">Listings from ebay</h4>
    <div class="row">
        <div class="col-12 m-auto" style="width: 750px; height: 600px; overflow-y: auto">
            <form action="/searchRequest/name" method="GET">
                <div class="row">
                    <div class="col-12 col-md-9 mb-2 mb-md-0">
                        <input class="form-control form-control-lg" name="search" type="text" placeholder="Product name..."></div>
                    <div class="col-12 col-md-3">
                        <button class="btn btn-primary btn-lg" type="submit">Search</button></div>
                </div>
            </form>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Product Price</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listingItems}" var="listing">
                            <tr>
                                <td>${listing.name} <p><a href="${listing.linkUrl}" id="mintbutton2" class="btn btn-primary mintButton">View</a></p></td>
                                <td><fmt:formatNumber value = "${listing.price}" type="currency"/></td>
                                <td><a href="${listing.linkUrl}"><img src="${listing.imgUrl}" class="itemImg"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col">
            <div class="table-responsive" style="border-top-style: none;">
                <table class="table">
                    <thead style="border-top-style: none;">
                    <tr></tr>
                    </thead>
                    <tbody>
                        <tr class="trprices">
                            <th>Average Cost</th>
                            <td><fmt:formatNumber value = "${averageCost}" type="currency"/></td>
                        </tr>

                        <tr class="trprices">
                            <th>Minimum Cost</th>
                            <td><fmt:formatNumber value = "${minCost}" type="currency"/></td>
                        </tr>

                        <tr class="trprices">
                            <th>Maximum Cost</th>
                            <td><fmt:formatNumber value = "${maxCost}" type="currency"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <h4 class="section-heading">Refine your search</h4>
    <div class="row">
        <div class="col">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Suggested categories for your search</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td>
                                <form action="/searchRequest/name" method="GET">

                                    <label for="search"></label>
                                    <input type="hidden" name="search" id="search" value="${searchedText}"/>
                                    <label for="catId"></label>
                                    <input type="hidden" name="catId" id="catId" value="${category.key}"/>
                                    <input type="submit" value="${category.value}" class="btn btn-primary mintButton"/>
                                    <a></a>
                                </form>
                            </td> <!-- link will go to get mapping in controller -->
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <h4 class="section-heading">Listings in ThriftyFriend database</h4>
    <div class="row">
        <div class="col">
            <div class="table-responsive">
                <table id="summaryTable" class="table">
                    <thhead>
                        <th>Summary Name</th>
                    </thhead>

                    <tbody>
                    <c:forEach items="${listingSummaries}" var="summary">
                        <tr>
                            <td><a class="btn btn-dark" href="/summary/${summary.id}/view" style="font-size: 20px;">${summary.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <h4 class="section-heading">If your item is not in the data base, add it here</h4>
    <a href="/summary/create/${searchedText}/${averageCost}/${minCost}/${maxCost}" class="btn btn-primary" type="button">Create listing summary</a>
</div>
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
<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>