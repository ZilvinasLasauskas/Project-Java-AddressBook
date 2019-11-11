<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person contact list</title>
    </head>
    <body>
        <h2>Person's Contacts List:</h2>
        <c:forEach var="c" items="${contactList}">
            <b>Type: </b>${c.type}
            <b>Contact: </b>${c.contact}
            <a style="margin-right: 10px;" href="deleteContact?id=${c.id}">Delete</a>
            <a style="margin-right: 10px;" href="editContact?id=${c.id}">Edit</a>
            <hr>
        </c:forEach>
        <a href="editContact?personId=${personId}">New</a>
        <a href="./">Cancel</a>
    </body>
</html>
