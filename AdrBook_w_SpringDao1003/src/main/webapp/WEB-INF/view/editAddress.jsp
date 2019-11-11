<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit address page</title>
    </head>
    <body>
        <form method="POST" action="saveAddress">
            <c:if test="${address != null}">
                <input type="hidden" name="id" value="${address.id}">
            </c:if>
            <input type = "hidden" name="personId" value="${personId}">
            Address: <input name="address" value="${address.address}">
            City: <input name="city" value="${address.city}">
            Postal Code: <input name="postalCode" value="${address.postalCode}"> 
            <input type="submit" value="save">
        </form>
        <a href="./personAddressList?id=${personId}">Cancel</a>
    </body>
</html>
