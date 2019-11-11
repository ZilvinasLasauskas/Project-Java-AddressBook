<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Person Edit</title>
    </head>
    <body>
        <div class="content short-content">
            <h1>Person Edit:</h1>
            <form method="POST" action="save">
                <c:if test="${person != null}">
                    <input type="hidden" name="id" value="${person.id}">
                </c:if> 
                <b>Name: </b><input name="fn" value="${person.firstName}"> 
                <b>Last Name: </b><input name="ln" value="${person.lastName}">
                <b>Birth Date:</b> <input name="bd" value="<fmt:formatDate value="${person.birthDate}" pattern="yyyy-MM-dd"></fmt:formatDate>"> 
                <b>Salary: </b><input name="salary" value="${person.salary}">
                <input type="submit" value="save">
            </form>
            <a href="./">Cancel</a>
        </div>>
    </body>
</html>