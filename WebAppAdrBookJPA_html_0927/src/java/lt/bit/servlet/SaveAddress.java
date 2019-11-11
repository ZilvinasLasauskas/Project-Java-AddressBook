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
import lt.bit.data.Address;
import lt.bit.data.Person;
import lt.bit.db.DB;

/**
 *
 * @author zills
 */
@WebServlet(name = "SaveAddress", urlPatterns = {"/saveAddress"})
public class SaveAddress extends HttpServlet {

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
        Address a = null;
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
            a = DB.getAddressById(em, id);
            p = DB.getByAddress(em, a);
        }
        if (p == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        if (a == null) {
            a = new Address(address, city, postalCode);
            DB.addAddress(em, p.getId(), a);
        } else {
            a.setAddress(address);
            a.setCity(city);
            a.setPostalCode(postalCode);
            DB.updateAddress(em, a);
        }
        response.sendRedirect("addresses.jsp?id=" + p.getId());
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
