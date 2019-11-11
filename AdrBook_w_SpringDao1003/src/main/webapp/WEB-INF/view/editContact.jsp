<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit contact page</title>
    </head>
    <body>
        <form method="POST" action="saveContact">
            <c:if test="${contact != null}">
                <input type="hidden" name="id" value="${contact.id}">
            </c:if>
            <input type = "hidden" name="personId" value="${personId}">
            Type: <input name="type" value="${contact.type}"> 
            Contact: <input name="contact" value="${contact.contact}">
            <input type="submit" value="save">
        </form>
        <a href="./personContactList?id=${personId}">Cancel</a>
    </body>
</html>
