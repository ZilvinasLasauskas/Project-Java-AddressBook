<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Address Edit</title>
    </head>
    <body>
        <div class="content short-content">
            <h1>Address Edit:</h1>
            <form method="POST" action="saveAddress">
                <c:if test="${address != null}">
                    <input type="hidden" name="id" value="${address.id}">
                </c:if> 
                <input type="hidden" name="personId" value="${personId}">
                <b>Address: </b><input name="address" value="${address.address}"> 
                <b>City: </b><input name="city" value="${address.city}">
                <b>Postal Code: </b><input name="postalCode" value="${address.postalCode}"> 
                <input type="submit" value="save">
            </form>
            <a href="./personAddressList?id=${personId}">Cancel</a>
        </div>>
    </body>
</html>
