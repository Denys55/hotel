<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mylibr" uri="http://com.ithotel" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="currentAddressPage" value="admin.jsp" scope="session"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="image/x-icon" href="resources/img/favicon.ico" rel="shortcut icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="resources/css/mainstyle.css" rel="stylesheet">
    <title>ithotel</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">IT HOTEL</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="controller?command=catalog&page=1"><fmt:message key="header_jsp.catalog"/></a>
                    </li>
                    <c:choose>
                        <c:when test="${not empty currentUser }">
                            <li class="nav-item">
                                <a class="nav-link" href="profile.jsp"><fmt:message key="header_jsp.profile"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp"><fmt:message key="header_jsp.login"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <mylib:languages></mylib:languages>
        </div>
    </nav>
</header>
<section class="main-content">
    <div class="container">
        <div class="newuser-form">
            <div class="mt-lg-5 justify-content-center">
        <form class="row g-3" action="controller" method="post">
            <input type="hidden" name="command" value="createUser">
            <div class="col-md-6">
                <label for="inputLogin4" class="form-label"><fmt:message key="header_jsp.login"/></label>
                <input type="login" name="login" class="form-control" id="inputLogin4" required pattern="\w+|\d+" placeholder="Login">
                <div class="form-text"><fmt:message key="newuser_jsp.loginUnderText"/></div>

            </div>
            <div class="col-md-6">
                <label for="inputPassword4" class="form-label"><fmt:message key="Password"/></label>
                <input type="password" name="password" class="form-control" id="inputPassword4" required minlength="4" maxlength="18" placeholder="Password">
                <div class="form-text"><fmt:message key="login_jsp.textUnderPassword"/></div>
            </div>
            <div class="col-md-6">
                <label for="inputFirstName4" class="form-label"><fmt:message key="firstName"/></label>
                <input type="firstname" name="firstname" class="form-control" id="inputFirstName4" required pattern="\D+" placeholder="First Name">
            </div>
            <div class="col-md-6">
                <label for="inputLastName4" class="form-label"><fmt:message key="lastName"/></label>
                <input type="lastname" name="lastname" class="form-control" id="inputLastName4" required pattern="\D+" placeholder="Last Name">
            </div>
            <div class="col-md-6">
                <label for="inputEmail4" class="form-label"><fmt:message key="Email"/></label>
                <input type="email" name="email" class="form-control" id="inputEmail4" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" placeholder="email@gmail.com">
                <div class="form-text"><fmt:message key="newuser_jsp.underEmail"/></div>
            </div>
            <div class="col-md-6">
                <label for="inputPhone4" class="form-label"><fmt:message key="Phone"/></label>
                <input type="phone" name="phone" class="form-control" id="inputPhone4" placeholder="(999) 999-9999">
            </div>
            <div class="col-12">
                <button type="submit" class="btn btn-primary"><fmt:message key="createProfile"/></button>
            </div>
        </form>
        </div>
        </div>
    </div>
</section>
<footer class="bg-dark text-white pt-2 pb-1 mt-lg-5">
    <div class="container text-center text-md-left">
        <div class="row text-center text-md-left">
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-2 font-weight-bold text-warning">IT HOTEL</h5>
                <p><fmt:message key="footer.textForUs"/></p>
            </div>
            <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-2 font-weight-bold text-warning"><fmt:message key="footer.Contact"/></h5>
                <p>
                    <i class="fas fa-home mr-1">
                        <fmt:message key="footer.address"/>
                    </i>
                </p>
                <p>
                    <i class="fas fa-home mr-1">
                        +38 099 111 22 33
                    </i>
                </p>
                <p>
                    <i class="fas fa-home mr-1">
                        ithotel@gmail.com
                    </i>
                </p>
            </div>
            <div>
                <hr class="mb-2">
                <div class="row align-items-center">
                    <div class="col-md-2 col-lg-3">
                        <p class="text-warning">
                            Make by Denis Cherep
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>



<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.maskedinput.min.js"></script>

<script>
    jQuery(function($){
        $("#inputPhone4").mask("(999) 999-9999");
    });
</script>
</body>
</html>