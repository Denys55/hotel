
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="locales">
    <form action="changeLocale.jsp" method="get">
        <input type="hidden" name="command" value="changeLanguage">
        <c:forEach items="${applicationScope.locales}" var="locale">
            <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
            <button class="btn btn-primary" type="submit" value="${locale.key}" name="locale">${locale.key}</button>
        </c:forEach>
    </form>
</div>
