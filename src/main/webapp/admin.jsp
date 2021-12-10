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
    <link href="resources/css/admin.css" rel="stylesheet">
    <title>admin</title>
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
    <div class="afterheder">
            <div class="choosestatusorder">
            <h5 class="text-white"><fmt:message key="admin_jsp.afterheder.title"/></h5>
            <div class="input-group">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="adminPage">
                    <select list="statusOrder" name="statusOrders" id="statusOrders">
                    <option value="0" selected><fmt:message key="admin_jsp.afterheder.chooseStatus.All"/></option>
                    <option value="1"><fmt:message key="admin_jsp.afterheder.chooseStatus.New"/></option>
                    <option value="2"><fmt:message key="admin_jsp.afterheder.chooseStatus.OnConfirmation"/></option>
                    <option value="3"><fmt:message key="admin_jsp.afterheder.chooseStatus.Invoiced"/></option>
                    <option value="4"><fmt:message key="admin_jsp.afterheder.chooseStatus.Paid"/></option>
                    <option value="5"><fmt:message key="admin_jsp.afterheder.chooseStatus.Canceled"/></option>
                    </select>
                <button class="btn btn-primary" type="submit"><fmt:message key="catalog_jsp_modal.confirm"/></button>
                  </form>
            </div>
            </div>
        </div>


<section class="adminpage-order flex">
    <div class="container">
        <div class="container text-start">
            <div class="col-lg-12 col-sm-12">
                <c:forEach var="order" items="${allOrders}">
                <div class="row">
                    <hr>
                    <div class="col-lg-4 col-sm-4 justify-content-start">
                        <div>
                    <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.numberoforder"/>:</a><a>${order.id}</a>
                        </div>
                        <div>
                        <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.totalCost"/>: </a><a>${order.totalCost}</a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-4 justify-content-between">
                        <div>
                    <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.statusOrder"/>: </a><a>${order.statusOfOrder}</a>
                        </div>
                        <div>
                    <a class="text-secondary text-decoration-none"> <fmt:message key="admin_jsp.order.roomId"/>: </a><a>${order.roomId}</a>
                            </div>
                    </div>
                    <div class="col-lg-4 col-sm-4 justify-content-end">
                        <div>
                    <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.checkIn"/>: </a><a>${order.checkIN}</a>
                        </div>
                        <div>
                    <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.checkOut"/>: </a><a>${order.checkOut}</a>
                        </div>
                        <div>
                    <a class="text-secondary text-decoration-none"><fmt:message key="admin_jsp.order.dateOfInvoice"/>: </a><a>${order.timeOrder}</a>
                        </div>
                    </div>
                    <div>
                    <a href="controller?command=personalInformation&orderId=${order.id}"><fmt:message key="admin_jsp.order.infoCustomer"/></a>
                    </div>

                    <div class="order-button">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#exampleModalLong${order.id}">
                            <fmt:message key="admin_jsp.order.modal.buttomName"/>
                        </button>

                        <!-- Modal -->
                        <form class="row g-3" action="controller" method="get">
                            <input type="hidden" name="command" value="changeOrderByAdmin">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <div class="modal fade" id="exampleModalLong${order.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle${order.id}" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle${order.id}"><fmt:message key="admin_jsp.order.modal.title"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="input-date">
                                                <form>
                                                    <div class="input-statusOfOrder mt-1">
                                                        <a><fmt:message key="admin_jsp.order.modal.changeStatus"/></a>
                                                        <select list="changeStatusOrders" name="changeStatusOrders" id="changeStatusOrders">
                                                            <option value="${order.statusOfOrder.id}" selected><fmt:message key="admin_jsp.order.modal.changeStatus"/></option>
                                                            <option value="1"><fmt:message key="admin_jsp.afterheder.chooseStatus.New"/></option>
                                                            <option value="2"><fmt:message key="admin_jsp.afterheder.chooseStatus.OnConfirmation"/></option>
                                                            <option value="3"><fmt:message key="admin_jsp.afterheder.chooseStatus.Invoiced"/></option>
                                                            <option value="4"><fmt:message key="admin_jsp.afterheder.chooseStatus.Paid"/></option>
                                                            <option value="5"><fmt:message key="admin_jsp.afterheder.chooseStatus.Canceled"/></option>
                                                        </select>
                                                    </div>
                                                    <div class="input-Room mt-1">
                                                        <a><fmt:message key="admin_jsp.order.modal.changeRoom"/></a>
                                                        <select list="changeRoomId" name="changeRoomId" id="changeRoomId">
                                                            <option value="${order.roomId}" selected><fmt:message key="admin_jsp.order.modal.changeRoom"/></option>
                                                            <c:forEach var="elem" items="${allIdRooms}">
                                                            <option value="${elem}">${elem}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div>
                                                        <label for="in"><fmt:message key="admin_jsp.order.modal.chooseArrivalDate"/>:</label>
                                                        <mylibr:nowDate/>
                                                        <input id="in" type="date" min="${currentDate}" value="${order.checkIN}" name="checkin"x>
                                                    </div>
                                                    <div>
                                                        <label for="out"><fmt:message key="admin_jsp.order.modal.chooseDepartureDate"/>:</label>
                                                        <mylibr:nowDate/>
                                                        <input id="out" type="date" min="${currentDate}" value="${order.checkOut}" name="checkout">
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
                </div>
                    <hr>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="resources/js/jquery.maskedinput.min.js"></script>
</body>
</html>