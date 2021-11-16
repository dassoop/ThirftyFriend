<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Thrifty Friend</title>
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
                <li class="nav-item"><a class="nav-link active" href="/">Search</a></li>
                <li class="nav-item"><a class="nav-link" href="/dashboard">Dashboard</a></li>
            </ul>
            <span class="navbar-text actions">
                <a class="btn btn-light action-button" role="button" href="/loginReg" style="background: rgb(93,218,193); display: inline;">Login | Register</a>
                <a class="nav-link" href="/logout" style="display: inline;">Log Out</a>
            </span>
        </div>
    </div>
</nav>
<header class="text-center text-white masthead">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-xl-9 mx-auto position-relative">
                <h2 class="mb-5">Start your search here</h2>
            </div>
            <div class="col-md-10 col-lg-8 col-xl-7 mx-auto position-relative">
                <form action="/searchRequest/name" method="GET">
                    <div class="row">
                        <div class="col-12 col-md-9 mb-2 mb-md-0">
                            <input class="form-control form-control-lg" name="search" type="text" placeholder="Product name..."></div>
                        <div class="col-12 col-md-3">
                            <button class="btn btn-primary btn-lg" type="submit">Search</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</header>
<section class="text-center bg-light features-icons">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                    <div class="d-flex features-icons-icon"><i class="icon-magnifier .icon" style="margin: auto;color: #5ddac1;"></i></div>
                    <h3>Comprehensive Search</h3>
                    <p class="lead mb-0">Search every listing on eBay. Narrow your search with specific categories.</p>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                    <div class="d-flex features-icons-icon"><i class="icon-graph .icon" style="margin: auto;color: #5ddac1;"></i></div>
                    <h3>Track Listings</h3>
                    <p class="lead mb-0">Add listings you are interested in to your watch list and keep track of their average prices over time.</p>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                    <div class="d-flex features-icons-icon"><i class="icon-check" style="margin: auto;color: #5ddac1;"></i></div>
                    <h3>Easy to Use</h3>
                    <p class="lead mb-0">Simply type your product name in the search to get started. Checking info on a product doesn't require an account.</p>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="text-center text-white call-to-action">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-xl-9 mx-auto position-relative">
                <h2 class="mb-4">Ready to get started? Sign up now!</h2>
            </div>
            <div class="col-md-10 col-lg-8 col-xl-7 mx-auto position-relative">
                <form>
                    <div class="row">
                        <div class="col-12 col-md-9 mb-2 mb-md-0"><input class="form-control form-control-lg" type="email" placeholder="Enter your email..."></div>
                        <div class="col-12 col-md-3"><button class="btn btn-primary btn-lg" type="submit">Sign up!</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
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