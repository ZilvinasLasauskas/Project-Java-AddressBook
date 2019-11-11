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
            <a class="green new" href="./?ord=fn">Order By First Name</a>
            <a class="green new" href="./?ord=ln">Order By Last Name</a>
            <a class="green new" href="./?ord=salary">Order By Pinigų</a>
            <span style="display: block;">Filter by name or last name:</span> 
            <form action="./">
                <input style="margin: 10px;" type="text" name="filter">
                <input class="btn" type="submit" value="FILTER">
            </form>
            <c:forEach var="p" items="${list}">
                <span><b>ID: </b>${p.id}</span>
                <span><b>Vardas: </b>${p.firstName}</span>
                <span><b>Pavarde: </b>${p.lastName}</span>
                <span><b>Metai: </b><fmt:formatDate value="${p.birthDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                <span><b>Pinigu: </b>${p.salary}</span>
                <a class="red" href="delete?id=${p.id}">Delete</a>
                <a class="blue" href="edit?id=${p.id}">Edit</a>
                <a class="yellow" href="personAddressList?id=${p.id}">Show Address List</a>
                <a class="yellow" href="personContactList?id=${p.id}">Show Contact List</a>
                <hr>
            </c:forEach>
            <a class="green new" href="edit">New</a>
            <a class="red" href="logout">Log Out</a>
        </div>
    </body>
</html>
