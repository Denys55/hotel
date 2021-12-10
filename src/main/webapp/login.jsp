
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mylibr" uri="http://com.ithotel" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="currentAddressPage" value="login.jsp" scope="session"></c:set>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="resources/css/mainstyle.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid h-100">
        <div class="row align-items-center h-100">
            <div class="col-sm-12">
                <div class="row justify-content-center">
                    <div class="col-6">
        <form class="login-form" action="controller" method="post">
            <div class="mb-3">
                <input type="hidden" name="command" value="login">
                <label for="exampleInputEmail1" class="form-label text-white"><fmt:message key="header_jsp.login"/></label>
                <input type="login" name="login" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required minlength="4" maxlength="18">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label text-white"><fmt:message key="Password"/></label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="password" required minlength="4" maxlength="18">
                <div class="form-text"><fmt:message key="login_jsp.textUnderPassword"/></div>
            </div>
            <button type="submit" class="btn btn-secondary btn-lg"><fmt:message key="header_jsp.login"/></button>
            <a class="text-lg-end justify-content-center" href="newuser.jsp"><fmt:message key="createProfile"/></a>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a class="btn btn-secondary btn-sm" href="index.jsp" role="button"><fmt:message key="login_jsp.buttomBack"/></a>
            </div>
        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
