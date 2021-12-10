package com.ithotel.filter;

import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//filter register user
@WebFilter(
        urlPatterns = {"/profile.jsp", "/clientOrders.jsp"})
public class CheckRigisterUserFilter implements Filter {
    final static Logger logger = Logger.getLogger(CheckRigisterUserFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        logger.info("CheckRigisterUserFilter get current user = " + currentUser);
        HttpServletResponse resp = (HttpServletResponse) response;

        if (currentUser != null) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect("login.jsp");
        }

    }
}
