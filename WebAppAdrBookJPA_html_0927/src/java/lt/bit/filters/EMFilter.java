/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author zills
 */
@WebFilter(filterName = "EMFilter", urlPatterns = {"/*"})
public class EMFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    /**
     *
     * @param request
     * @param response
     * @param fc
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        ServletContext sc = request.getServletContext();
        EntityManagerFactory emf = (EntityManagerFactory) sc.getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        request.setAttribute("em", em);
        try {
            fc.doFilter(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
    }
}
