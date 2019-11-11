<%-- 
    Document   : contacts
    Created on : Sep 4, 2019, 3:12:27 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.List"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Contact"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kontaktai</title>
    </head>
    <body>
        <%
            EntityManager em = (EntityManager) request.getAttribute("em");
            String idS = request.getParameter("id");
            if (idS == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            Integer id = null;
            try {
                id = new Integer(idS);
            } catch (Exception ex) {
                response.sendRedirect("index.jsp");
                return;
            }
            Person p = DB.getById(em, id);
            if (p == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            List<Contact> cntlist = DB.getPersonContacts(em,id);
        %>

        <%=p.getFirstName()%><%=p.getLastName()%> Kontaktu sarasas:
        <hr>

        <% for (Contact c : cntlist) {
        %>
        <%=c.getType()%>
        <%=c.getContact()%> 
        <a href="deleteContact?id=<%=c.getId()%>&pId=<%=p.getId()%>">Delete</a>
        <a href="editContact.jsp?id=<%=c.getId()%>">Edit</a>
        <hr>
        <%}%>

        <a href="editContact.jsp?pId=<%=p.getId()%>">New</a>
        <a href="index.jsp">Back</a>

    </body>
</html>
