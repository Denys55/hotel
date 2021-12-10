<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mylibr" uri="http://com.ithotel" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="currentAddressPage" value="catalog.jsp" scope="session"></c:set>

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
                    <li class="nav-item">
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
                    </li>
                </ul>
            </div>
            <mylib:languages></mylib:languages>
        </div>
    </nav>
</header>
<section class="catalog-sort sort-order dropdown-menu-lg-start">
    <div class="container mt-1">
        <div class="container text-start">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="catalog_jsp.buttom.sortBy"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="controller?command=catalog&page=1&sort=price"><fmt:message key="catalog_jsp.sort.dropdown.price"/></a>
            <a class="dropdown-item" href="controller?command=catalog&page=1&sort=numbers_bed"><fmt:message key="catalog_jsp.sort.dropdown.beds"/></a>
            <a class="dropdown-item" href="controller?command=catalog&page=1&sort=class_id"><fmt:message key="catalog_jsp.sort.dropdown.class"/></a>
            <a class="dropdown-item" href="controller?command=catalog&page=1&sort=statuses_room_id"><fmt:message key="catalog_jsp.sort.dropdown.status"/></a>
        </div>
    </div>
        </div>
    </div>
</section>
<section class="main-content">
    <div class="container">
        <div class="container text-start">
            <div class="col-lg-12 col-sm-12">
                <c:forEach var="room" items="${currentRooms}">
                <div class="product-card">
                    <div class="text-uppercase">
                        <h3>${room.id}</h3>
                    </div>
                    <div class="text-uppercase">
                        <h3>${room.name}</h3>
                    </div>
                    <div class="text-warning">
                        <h4><fmt:message key="catalog_jsp_card.statusRoom"/> - ${room.statusOfRoom}</h4>
                    </div>
                    <div class="text">
                        <h4><fmt:message key="catalog_jsp_card.classRoom"/> - ${room.classOfRoom}</h4>
                    </div>
                    <div class="text">
                        <h4><fmt:message key="catalog_jsp_card.bed"/> - ${room.numbersOfBed}</h4>
                    </div>
                    <div class="product-button-price">
                        <div class="product-price">
                            <h5><fmt:message key="catalog_jsp_card.price"/>:</h5>
                            <h4 class="text-warning">${room.price}</h4>
                        </div>
                    </div>

                    <div class="button-book">
                        <c:choose>
                            <c:when test="${not empty currentUser }">
                                <div class="product-button">
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#exampleModalLong${room.id}">
                                        <fmt:message key="catalog_jsp_buttom.book"/>
                                    </button>

                                    <!-- Modal -->
                                    <form class="row g-3" action="controller" method="get">
                                        <input type="hidden" name="command" value="order">
                                        <input type="hidden" name="sourceOfOrder" value="catalog">
                                        <input type="hidden" name="userId" value=${currentUser.id}>
                                        <input type="hidden" name="roomId" value=${room.id}>
                                        <input type="hidden" name="class" value=${room.classOfRoom}>
                                        <input type="hidden" name="numbersOfPlace" value=${room.numbersOfBed}>
                                        <div class="modal fade" id="exampleModalLong${room.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle${room.id}" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLongTitle${room.id}"><fmt:message key="catalog_jsp_modal.title"/></h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="input-date">
                                                            <form>
                                                                <div>
                                                                    <label for="in"><fmt:message key="catalog_jsp_modal.arrivalDate"/>:</label>
                                                                    <mylibr:nowDate/>
                                                                    <input id="in" type="date" min="${currentDate}" name="checkin">
                                                                </div>
                                                                <div>
                                                                    <label for="out"><fmt:message key="catalog_jsp_modal.departureDate"/>:</label>
                                                                    <mylibr:nowDate/>
                                                                    <input id="out" type="date" min="${currentDate}" name="checkout">
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="catalog_jsp_modal.close"/></button>
                                                        <button type="submit" class="btn btn-primary"><fmt:message key="catalog_jsp_modal.confirm"/></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="product-button">
                                    <a class="btn btn-outline-dark" href="login.jsp" role="button"><fmt:message key="catalog_jsp_buttom.book"/></a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
                </c:forEach>
            </div>
            <div class="catalog-pagination">
                <nav aria-label="page-navigation">
                    <ul class="pagination justify-content-center">
                        <c:choose>
                            <c:when test="${sort == null}">
                                <c:choose>
                                    <c:when test="${page - 1 > 0}">
                                        <li class="page-item">
                                            <a href="controller?command=catalog&page=${page-1}" class="page-link"><fmt:message key="catalog_jsp.pagination.pre"/></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link"><fmt:message key="catalog_jsp.pagination.pre"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${page + 1 <= countPage}">
                                        <li class="page-item">
                                            <a href="controller?command=catalog&page=${page+1}" class="page-link"><fmt:message key="catalog_jsp.pagination.next"/></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link"><fmt:message key="catalog_jsp.pagination.next"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${page - 1 > 0}">
                                        <li class="page-item">
                                            <a href="controller?command=catalog&page=${page-1}&sort=${sort}" class="page-link"><fmt:message key="catalog_jsp.pagination.pre"/></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link"><fmt:message key="catalog_jsp.pagination.pre"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${page + 1 <= countPage}">
                                        <li class="page-item">
                                            <a href="controller?command=catalog&page=${page+1}&sort=${sort}" class="page-link">Next</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link"><fmt:message key="catalog_jsp.pagination.next"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        </ul>
                </nav>
            </div>
        </div>
    </div>
</section>
<section>
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
</section>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>