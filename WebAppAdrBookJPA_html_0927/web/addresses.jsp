<%-- 
    Document   : addreses
    Created on : Sep 4, 2019, 3:12:10 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.List"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adresai</title>
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
            List<Address> adrlist = DB.getPersonAddresses(em, id);
        %>

        <%=p.getFirstName()%><%=p.getLastName()%> Adresu sarasas:
        <hr>

        <% for (Address a : adrlist) {
        %>
        <%=a.getAddress()%>
        <%=a.getCity()%>
        <%=a.getPostalCode()%>

        <a href="deleteAddress?id=<%=a.getId()%>&pId=<%=p.getId()%>">Delete</a>
        <a href="editAddress.jsp?id=<%=a.getId()%>">Edit</a>
        <hr>
        <%}%>

        <a href="editAddress.jsp?pId=<%=p.getId()%>">New</a>
        <a href="index.jsp">Back</a>

    </body>
</html>
