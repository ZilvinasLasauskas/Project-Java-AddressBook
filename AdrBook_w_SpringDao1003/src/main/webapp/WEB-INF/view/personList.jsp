<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person list</title>
    </head>
    <body>
        <h2>Persons List:</h2>
        <c:forEach var="p" items="${list}">
            <b>Id: </b>${p.id}
            <b>Name: </b> ${p.firstName}
            <b>Last Name: </b> ${p.lastName}
            <b>Birth Date: </b><fmt:formatDate value="${p.birthDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
            <b>Salary: </b> ${p.salary}
            <a style="margin-right: 10px;" href="delete?id=${p.id}">Delete</a>
            <a style="margin-right: 10px;" href="edit?id=${p.id}">Edit</a>
            <a style="margin-right: 10px;" href="personAddressList?id=${p.id}">Show Address List</a>
            <a style="margin-right: 10px;" href="personContactList?id=${p.id}">Show Contact List</a>
            <hr>
        </c:forEach>
        <a href="edit">New</a>
    </body>
</html>
