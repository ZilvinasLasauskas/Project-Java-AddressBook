<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person address list</title>
    </head>
    <body>
        <h2>Person's Addresses List:</h2>
        <c:forEach var="a" items="${addressList}">
            <b>Address: </b> ${a.address}
            <b>City: </b> ${a.city}
            <b>Postal Code: </b> ${a.postalCode}
            <a style="margin-right: 10px;" href="deleteAddress?id=${a.id}">Delete</a>
            <a style="margin-right: 10px;" href="editAddress?id=${a.id}">Edit</a>
            <hr>
        </c:forEach>
        <a href="editAddress?personId=${personId}">New</a>
        <a href="./">Cancel</a> 
    </body>
</html>
