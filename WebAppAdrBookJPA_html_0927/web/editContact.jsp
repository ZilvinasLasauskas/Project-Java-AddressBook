<%-- 
    Document   : editContact
    Created on : Sep 5, 2019, 1:08:53 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.Contact"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Contact</title>
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

            Contact c = null;
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
                c = DB.getContactById(em, id);
                p = c.getPerson();
            }
            if (p == null) {
                response.sendRedirect("index.jsp");
                return;
            }
        %>

        <%=p.getFirstName()%> <%=p.getLastName()%> Kontakto redagavimas:
        <hr>
        <form method="POST" action= "saveContact">
            <%  if (c != null) {
            %>
            <input type="hidden" name = "id" value ="<%=c.getId()%>">
            <%  } else {
            %>
            <input type="hidden" name = "pId" value ="<%=p.getId()%>">
            <%
                }
            %>

            Tipas: <input name="type" value="<%=(c != null) ? c.getType() : ""%>">
            Kontaktas: <input name="contact" value="<%=(c != null) ? c.getContact() : ""%>">
            <input type="submit" value="Save">
        </form>
        <a href="contacts.jsp?id=<%=p.getId()%>">Back</a>

    </body>
</html>
