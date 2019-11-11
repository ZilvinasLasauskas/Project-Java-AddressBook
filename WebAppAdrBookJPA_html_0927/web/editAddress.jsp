<%-- 
    Document   : editAddress
    Created on : Sep 5, 2019, 5:41:44 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Address</title>
    </head>
    <body>
        <%
            EntityManager em = (EntityManager) request.getAttribute("em");
            String idS = request.getParameter("id");
            Integer id = null;
            try {
                id = new Integer(idS);

            } catch (Exception ex) {
                // ignored
            }

            Address a = null;
            Integer pId = null;
            Person p = null;
            if (id == null) {
                String pIdS = request.getParameter("pId");
                try {
                    pId = new Integer(pIdS);
                } catch (Exception ex) {
                    //ignored
                }
                p = DB.getById(em, pId);
            } else {
                a = DB.getAddressById(em, id);
                p = a.getPerson();
            }
            if (p == null) {
                response.sendRedirect("index.jsp");
                return;
            }
        %>

        <%=p.getFirstName()%> <%=p.getLastName()%> Adreso redagavimas:
        <hr>
        <form method="POST" action= "saveAddress">
            <%  if (a != null) {
            %>
            <input type="hidden" name = "id" value ="<%=a.getId()%>">
            <%
            } else {
            %>
            <input type="hidden" name = "pId" value ="<%=p.getId()%>">
            <%
                }
            %>

            Adresas: <input name="address" value="<%=(a != null) ? a.getAddress() : ""%>">
            Miestas: <input name="city" value="<%=(a != null) ? a.getCity() : ""%>">
            Pasto kodas: <input name="postalCode" value="<%=(a != null) ? a.getPostalCode() : ""%>">
            <input type="submit" value="Save">
        </form>
        <a href="addresses.jsp?id=<%=p.getId()%>">Back</a>
    </body>
</html>
