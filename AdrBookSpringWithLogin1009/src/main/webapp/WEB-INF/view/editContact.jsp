<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <title>Contact Edit</title>
    </head>
    <body>
        <div class="content short-content">
            <h1>Contact Edit:</h1>
            <form method="POST" action="saveContact">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${contact != null}">
                    <input type="hidden" name="id" value="${contact.id}">
                </c:if> 
                <b>Savininko ID: </b><input name="personId" value="${contact.person.id}">
                <b>Kontakto Tipas: </b><input name="type" value="${contact.type}"> 
                <b>Kontaktas: </b><input name="contact" value="${contact.contact}">
                <input class="btn" type="submit" value="save">
            </form>
            <a class="red new" href="./personContactList?id=${personId}">Cancel</a>
        </div>
    </body>
</html>