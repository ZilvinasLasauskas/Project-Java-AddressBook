<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Person Contact List</title>
    </head>
    <body>
        <div class="content short-content">
            <h1>Person Contact List:</h1>
            <c:forEach var="c" items="${contactList}">
                <span><b>Type: </b>${c.type}</span>
                <span><b>Contact: </b>${c.contact}</span>
                <a href="deleteContact?id=${c.id}">Delete</a>
                <a href="editContact?id=${c.id}">Edit</a>
                <hr>
            </c:forEach>
            <a href="editContact?personId=${personId}">New</a>
            <a href="./">Cancel</a>
        </div>
    </body>
</html>
