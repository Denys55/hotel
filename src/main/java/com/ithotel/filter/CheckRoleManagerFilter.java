package com.ithotel.filter;

import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//filter check role user, need to be manager
@WebFilter(urlPatterns = {"/admin.jsp", "/personalinformation.jsp"})
public class CheckRoleManagerFilter implements Filter {
    final static Logger logger = Logger.getLogger(CheckRoleManagerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User currentUser = (User)req.getSession().getAttribute("currentUser");
        HttpServletResponse resp = (HttpServletResponse) response;

        if(currentUser==null) {
            resp.sendRedirect("login.jsp");
        } else {

            String role = currentUser.getRole().getTitle();

            if (role.equalsIgnoreCase("manager")) {
                chain.doFilter(req, response);
            } else {
                resp.sendRedirect("login.jsp");
            }
        }
    }
}
