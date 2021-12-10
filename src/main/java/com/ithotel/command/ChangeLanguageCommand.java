package com.ithotel.command;

import com.ithotel.dao.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {

    final static Logger logger = Logger.getLogger(ChangeLanguageCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        String pathInfo = req.getQueryString();
        String result = "http://localhost:8080" + pathInfo;
        req.setAttribute("currentAddressPage", result);
        logger.info("servletPath = " + result);


        return "changeLocale.jsp";
    }
}
