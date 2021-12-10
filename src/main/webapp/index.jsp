<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<c:set var="currentAddressPage" value="index.jsp" scope="session"></c:set>

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
    <div class="flex">
        <div class="container text-center">
            <div class="col-md-5 col-lg-6 col-xl-7 mx-auto mt-22">
                <h1 class="text-uppercase">
                    <fmt:message key="index_jsp.textOne"/>
                </h1>
                <h3>
                    <fmt:message key="index_jsp.textTwo"/>
                </h3>
                <h3>
                    <fmt:message key="index_jsp.textThree"/>
                </h3>
            </div>
        </div>
    </div>
    </section>
    <footer class="bg-dark text-white pt-2 pb-1">
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



<script src="resources/bootstrap/bootstrap.min.js"></script>
</body>
</html>