<%-- 
    Document   : edit
    Created on : Sep 4, 2019, 12:34:06 PM
    Author     : zills
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Person</title>
    </head>
    <body>
        <%
            EntityManager em = (EntityManager) request.getAttribute("em");
            String idS=request.getParameter("id");
            Person p=null;
            try{
            p=DB.getById(em, new Integer (idS));
            }catch (Exception ex){
            //ignored
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            %>          
        
        
        <form method = "POST" action="save">
            <%
            if (p!=null){
            %>
            <input type = "hidden" name="id" value="<%=p.getId()%>">
            <% } 
            %>
            
            Vardas:
            <input name ="fn" value="<%=(p!=null)?p.getFirstName():""%>">
            Pavarde:
            <input name = "ln" value="<%=(p!=null)?p.getLastName():""%>">
            Gimimo data:
            <input name = "bd" value="<%=((p!=null)&&(p.getBirthDate()!=null))?sdf.format(p.getBirthDate()):""%>">
            Alga:
            <input name = "salary" value="<%=((p!=null)&&(p.getSalary()!=null))?p.getSalary():""%>">
            
            <input type = "submit" value="Save">
            </form>
        
        <a href="index.jsp">Cancel</a>
        
    </body>
</html>
