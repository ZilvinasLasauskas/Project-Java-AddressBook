<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Person List</title>
    </head>
    <body>
        <div class="content">
            <h1>Person List:</h1>
            <a href="./?ord=fn">Order By First Name</a>
            <a href="./?ord=ln">Order By Last Name</a>
            <hr>
            <form action="./">
                <input type="text" name="filter">
                <input type="submit" value="filter">
            </form>
            <br>
            <c:forEach var="p" items="${list}">
                <span><b>ID: </b>${p.id}</span>
                <span><b>Name: </b>${p.firstName}</span>
                <span><b>Last Name: </b>${p.lastName}</span>
                <span><b>Birth Date: </b><fmt:formatDate value="${p.birthDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                <span><b>Salary: </b>${p.salary}</span>
                <a href="delete?id=${p.id}">Delete</a>
                <a href="edit?id=${p.id}">Edit</a>
                <a href="personAddressList?id=${p.id}">Show Address List</a>
                <a href="personContactList?id=${p.id}">Show Contact List</a>
                <hr>
            </c:forEach>
            <a href="edit">New</a>
        </div>
    </body>
</html>
