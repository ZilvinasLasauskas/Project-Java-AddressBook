<%-- 
    Document   : index
    Created on : Sep 3, 2019, 2:21:55 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adresu knygute</title>
    </head>
    <body>
        <%
            EntityManager em = (EntityManager) request.getAttribute("em");
            for (Person p : DB.getAll(em)){
        %>

        <%=p.getId()%> 
        <%=p.getFirstName()%>
        <%=p.getLastName()%> 
        <%=p.getBirthDate() != null?p.getBirthDate():""%>
        <%=p.getSalary() !=null?p.getSalary():""%>
        
        <a href="delete?id=<%=p.getId()%>">Delete</a>
        <a href ="edit.jsp?id=<%=p.getId()%>">Edit</a>
        <a href ="addresses.jsp?id=<%=p.getId()%>">Address</a>
        <a href ="contacts.jsp?id=<%=p.getId()%>">Contacts</a>

        <hr>
        <%
            }
        %>

        <a href ="edit.jsp">New</a>

    </body>
</html>
