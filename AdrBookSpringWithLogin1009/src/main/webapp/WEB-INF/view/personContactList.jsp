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
            <span>Filter by contact type:</span>
            <form action="personContactList">
                <input type="hidden" name="id" value="${personId}">
                <input style="margin: 10px;" type="text" name="filterType">
                <input class="btn" type="submit" value="FILTER">
            </form>
            <c:forEach var="c" items="${contactList}">
                <span><b>ID: </b>${c.id}</span>
                <span><b>Type: </b>${c.type}</span>
                <span><b>Contact: </b>${c.contact}</span>
                <a class="red" href="deleteContact?id=${c.id}">Delete</a>
                <a class="blue" href="editContact?id=${c.id}">Edit</a>
                <hr>
            </c:forEach>
            <a class="green new" href="editContact">New</a>
            <a class="red new" href="./">Cancel</a> 
        </div>
    </body>
</html>
