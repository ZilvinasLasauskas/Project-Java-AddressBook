<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Person Address List</title>
    </head>
    <body>
        <div class="content short-content">
            <h1>Person Address List:</h1>
            <a href="./personAddressList/?ordA=adr">Order By Address</a>
            <a href="./personAddressList/?ordA=cit">Order By City</a>
            <hr>
            <form action="personAddressList">
                <input type="hidden" name="id" value="${personId}">
                <input type="text" name="filterA">
                <input type="submit" value="Filter">
            </form>
            <br>
            
            <c:forEach var="a" items="${addressList}">
                <span><b>Address: </b>${a.address}</span>
                <span><b>City: </b>${a.city}</span>
                <span><b>Postal Code: </b>${a.postalCode}</span>
                <a href="deleteAddress?id=${a.id}">Delete</a>
                <a href="editAddress?id=${a.id}">Edit</a>
                <hr>
            </c:forEach>
            <a href="editAddress?personId=${personId}">New</a>
            <a href="./">Cancel</a>
        </div>
    </body>
</html>
