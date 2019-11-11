/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import lt.bit.db.DB;

/**
 *
 * @author zills
 */
@WebServlet(name = "SaveContact", urlPatterns = {"/saveContact"})
public class SaveContact extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = (EntityManager) request.getAttribute("em");
        String idS = request.getParameter("id");
        Integer id = null;
        try {
            id = new Integer(idS);
        } catch (Exception ex) {
            // ignored
        }
        Contact c = null;
        Person p = null;
        Integer pId = null;
        if (id == null) {
            String pIdS = request.getParameter("pId");
            try {
                pId = new Integer (pIdS);
            } catch (Exception ex) {
                // ignored
            }
            p = DB.getById(em, pId);
        } else {
            c = DB.getContactById(em, id);
            p = DB.getByContact(em, c);
        }
        if (p == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String type = request.getParameter("type");
        String contact = request.getParameter("contact");
        if (c == null) {
            c = new Contact(type, contact);
            DB.addContact(em, p.getId(), c);
        } else {
            c.setType(type);
            c.setContact(contact);
            DB.updateContact(em, c);
        }
        response.sendRedirect("contacts.jsp?id=" + p.getId());

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
