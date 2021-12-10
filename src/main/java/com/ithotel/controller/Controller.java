package com.ithotel.controller;

import com.ithotel.command.Command;
import com.ithotel.command.CommandContainer;
import com.ithotel.command.CommandException;
import com.ithotel.dao.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//front controller
@WebServlet("/controller")
public class Controller extends HttpServlet {

    final static Logger logger = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set encoding
        req.setCharacterEncoding("UTF-8");
        logger.info("front controller method get have character encoding" + req.getCharacterEncoding());

        String commandName = req.getParameter("command");
        logger.debug("command ==>" + commandName);

        Command command = CommandContainer.getCommand(commandName);
        String address = "error.jsp";
        try {
            address = command.execute(req, resp);
        } catch (Exception e) { // написать сюда актуальный эксепшин
            String back = req.getHeader("Referer");
            logger.info("Controller get backlink= " + back);
            //String substring = back.substring(41);
            req.getSession().setAttribute("back", back);

            req.setAttribute("exception", e.getMessage());
        }

        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set encoding
        req.setCharacterEncoding("UTF-8");
        logger.info("front controller method post have character encoding" + req.getCharacterEncoding());

        String commandName = req.getParameter("command");

        Command command = CommandContainer.getCommand(commandName);
        String address = "error.jsp";
        try {
            address = command.execute(req, resp);
        } catch (CommandException | DAOException e) { // написать сюда актуальный эксепшин
            //update link, delete http://localhost:8080/hotel_war_exploded/;
            //get link back
            String back = req.getHeader("Referer");
            logger.info("Controller get backlink= " + back);
            //String substring = back.substring(41);
            req.getSession().setAttribute("back", back);

            //logger.info("Controller update backlink= " + substring);
            req.getSession().setAttribute("exception", e.getMessage());
        }

        resp.sendRedirect(address);
    }
}
