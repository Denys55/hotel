package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.UserDAO;
import com.ithotel.entity.PersonalInformation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//this class help get personal information about user by order id
public class PersonalInformationCommand implements Command{

    final static Logger logger = Logger.getLogger(PersonalInformationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        String stringOrderId = req.getParameter("orderId");
        int orderId = Integer.parseInt(stringOrderId);
        logger.info("order Id = " + orderId);

        UserDAO userDAO = new UserDAO();

        PersonalInformation personalInformationByOrderId = userDAO.getPersonalInformationByOrderId(orderId);
        req.setAttribute("personalInformation", personalInformationByOrderId);

        String back = req.getHeader("Referer");
        req.setAttribute("back", back);

        return "personalinformation.jsp";

    }
}
