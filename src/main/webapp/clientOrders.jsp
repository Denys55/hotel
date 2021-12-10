<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mylibr" uri="http://com.ithotel" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="currentAddressPage" value="clientOrders.jsp" scope="session"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="image/x-icon" href="resources/img/favicon.ico" rel="shortcut icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="resources/css/mainstyle.css" rel="stylesheet">
    <link href="resources/css/profile.css" rel="stylesheet">
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
        <div class="main">
            <div class="topbar">
                <a class="topbar text-uppercase"><fmt:message key="clientOrders_jsp.main-content.Hello"/> ${currentUser.login}</a>
            </div>
            <div class="row">
                <div class="col-md-4 mt-1">
                    <div class="card text-center sidebar">
                        <div class="card-body">
                            <img src="resources/img/avatar.png" class="rounded-circle" width="150">
                            <div class="mt-3">
                                <div class="balance-card">
                                    <h3>
                                        <fmt:message key="clientOrders_jsp.main-content.sidebar.TextBalance"/>
                                    </h3>
                                    <h4 class="text-warning">
                                        ${currentUser.balance}
                                    </h4>
                                    <div class="balance-button">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong3">
                                            <fmt:message key="clientOrders_jsp.main-content.sidebar.modal.buttomTitle"/>
                                        </button>

                                        <!-- Modal -->
                                        <form class="row g-3" action="controller" method="post">
                                            <input type="hidden" name="command" value="updateUser">
                                            <input type="hidden" name="update" value="balanceUpdate">
                                            <div class="modal fade" id="exampleModalLong3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle3" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLongTitle3"><fmt:message key="clientOrders_jsp.main-content.sidebar.modal.Title"/></h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="input-login">
                                                                <label for="inputBalance3" class="form-label"><fmt:message key="header_jsp.login"/></label>
                                                                <input type="balance" name="balance" class="form-control" id="inputBalance3" required pattern="\d+" value="0">
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
                                </div>
                                <div class="order-button mt-5">

                                    <div class="makeOrder-button">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#ModalLong">
                                            <fmt:message key="makeOrder.modal.buttom.makeOrder"/>
                                        </button>

                                        <!-- Modal -->
                                        <form class="row g-3" action="controller" method="get">
                                            <input type="hidden" name="command" value="order">
                                            <input type="hidden" name="sourceOfOrder" value="profile">
                                            <input type="hidden" name="userId" value=${currentUser.id}>

                                            <div class="modal fade" id="ModalLong" tabindex="-1" role="dialog" aria-labelledby="ModalLongTitle" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="ModalLongTitle"><h3 class="text-black-50 text-start"><fmt:message key="makeOrder.modal.title"/></h3></h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="input-numberBeds">
                                                                <label for="numberBeds" class="form-label"><h5 class="text-black-50 text-start" ><fmt:message key="makeOrder.modal.numberOfBeds"/></h5></label>
                                                                <input type="numberBeds" name="numberBeds" class="form-control" id="numberBeds" required pattern="\d+" value="0">
                                                            </div>
                                                            <div class="input-classRoom justify-content-start mt-2">
                                                                <div class="row">
                                                                    <h5 class="text-black-50 text-start" ><fmt:message key="makeOrder.modal.chooseClassRoom"/>:</h5>
                                                                    <select name="classRoom" required="required">
                                                                        <option value="">Choose class</option>
                                                                        <option value="economy">economy</option>
                                                                        <option value="standard">standard</option>
                                                                        <option value="lux">lux</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="input-date justify-content-start mt-2">
                                                                <div class="row">
                                                                    <form>
                                                                        <div>
                                                                            <label for="in1"><h5 class="text-black-50 text-start"><fmt:message key="admin_jsp.order.modal.chooseArrivalDate"/>:</h5></label>
                                                                            <input id="in1" type="date" name="checkin" required>
                                                                        </div>
                                                                        <div>
                                                                            <label for="out1"><h5 class="text-black-50 text-start"><fmt:message key="admin_jsp.order.modal.chooseDepartureDate"/>:</h5></label>
                                                                            <input id="out1" type="date" name="checkout" required>
                                                                        </div>
                                                                    </form>
                                                                </div>
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
                                </div>
                                <div class="mt-5">
                                    <div>
                                        <a href="profile.jsp"><fmt:message key="header_jsp.profile"/></a>
                                    </div>
                                    <div class="button mt-lg-5">
                                        <form class="row g-lg-5" action="controller" method="get">
                                            <input type="hidden" name="command" value="logout">
                                            <button type="submit" class="btn btn-info btn-lg"><fmt:message key="Logout"/></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<c:choose>
<c:when test="${not empty ordersInvoiced }">
                <div class="col-md-8 mt-1">
                    <div class="card mb-3 content">
                        <h1 class="m-3 pt-3"><fmt:message key="clientOrders_jsp.textOrdersforpayment"/></h1>
                        <div class="card-body">
                            <hr>
                            <div class="row">
                                <div class="col-md-12">
<c:forEach var="orderInvoiced" items="${ordersInvoiced}">
    <div class="order-card">
        <hr>
        <div class="text-uppercase">
            <p class="text-secondary">#${orderInvoiced.id}</p>
        </div>
        <div class="text-uppercase">
            <p><fmt:message key="makeOrder.modal.numberOfBeds"/>: ${orderInvoiced.numbersOfPlace}</p>
        </div>
        <div class="text-uppercase">
            <p><fmt:message key="clientOrders_jsp.TimeToOrders"/>: ${orderInvoiced.timeOrder}</p>
        </div>
        <div class="text-uppercase">
            <p><fmt:message key="admin_jsp.order.checkIn"/>: ${orderInvoiced.checkIN}</p>
        </div>
        <div class="text-uppercase">
            <p><fmt:message key="admin_jsp.order.checkOut"/>:${orderInvoiced.checkOut}</p>
        </div>
        <div class="text-uppercase">
            <p><fmt:message key="classOfRoom"/>: ${orderInvoiced.classOfRoom}</p>
        </div>
        <div class="text-uppercase">
            <h5><fmt:message key="needToPay"/>: </h5>
            <h5 class="text-warning">${orderInvoiced.totalCost}</h5>
        </div>
        <div class="button mt-2 col-md-3">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="paymentOrder">
            <input type="hidden" name="orderId" value=${orderInvoiced.id}>
            <input type="hidden" name="totalCost" value=${orderInvoiced.totalCost}>
            <button type="submit" class="btn btn-primary"><fmt:message key="pay"/></button>
        </form>
        </div>
        <div class="button mt-2 col-md-3">
            <form action="controller" method="get">
                <input type="hidden" name="command" value="canceledOrder">
                <input type="hidden" name="orderId" value=${orderInvoiced.id}>
                <button type="submit" class="btn btn-danger"><fmt:message key="Canceled"/></button>
            </form>
        </div>
        <hr>
    </div>
</c:forEach>
</c:when>
<c:otherwise>
    <div class="col-md-8 mt-1">
        <div class="card mb-3 content">
    <hr>
    <h1 class="m-3 pt-3">
        <fmt:message key="clientorders_jsp.textYouCanNotHaveOrdersToPay"/>.
    </h1>
    <hr>
        </div>
    </div>
</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">

                            </div>
                        </div>
                    </div>
                </div>
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
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="resources/js/jquery.maskedinput.min.js"></script>

<script>
    jQuery(function($){
        $("#inputphone3").mask("${personalInformation.phone}");
    });
</script>
</body>
</html>
