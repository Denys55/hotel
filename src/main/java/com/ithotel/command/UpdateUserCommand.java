package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.UserDAO;
import com.ithotel.entity.PersonalInformation;
import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

//Update User
public class UpdateUserCommand implements Command{

    final static Logger logger = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        UserDAO userDAO = new UserDAO();

        //get parameter what update
        String updateUserOrPersonalInfo = req.getParameter("update");
        logger.info("UpdateUserCommand update user or personal information - " + updateUserOrPersonalInfo);

        //get current user
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        logger.info("UpdateUserCommand" + currentUser);

        if (updateUserOrPersonalInfo.equals("mainInfo")) {
            //get new login
            String login = req.getParameter("login");
            logger.info("UpdateUserCommand " + login);

            //get new password
            String password = req.getParameter("password");

            //set new login and password
            currentUser.setLogin(login);
            currentUser.setPassword(password);

            //get request to Data Base
            userDAO.updateUserById(currentUser);
            req.getSession().setAttribute("currentuser", currentUser);
            return "profile.jsp";
        }

        if (updateUserOrPersonalInfo.equals("secondaryInfo")) {

            //get first name
            String firstName = req.getParameter("firstName");
            logger.info("UpdateUserCommand " + firstName);

            //get last name
            String lastName = req.getParameter("lastName");
            logger.info("UpdateUserCommand " + lastName);

            //get email
            String email = req.getParameter("email");
            logger.info("UpdateUserCommand " + email);

            //get phone
            String phone = req.getParameter("phone");
            logger.info("UpdateUserCommand " + phone);

            //get current personal information
            PersonalInformation personalInformation = (PersonalInformation) req.getSession().getAttribute("personalInformation");
            logger.info("UpdateUserCommand" + personalInformation);

            //set new login and password
            personalInformation.setFirstName(firstName);
            personalInformation.setLastName(lastName);
            personalInformation.setEmail(email);
            personalInformation.setPhone(phone);

            //get request to Data Base
            userDAO.updatePersonalInformationByUserId(personalInformation, currentUser.getId());
            req.getSession().setAttribute("personalInformation", personalInformation);
            return "profile.jsp";
        }

        if (updateUserOrPersonalInfo.equals("balanceUpdate")) {
            //get balance
            String balanceString = req.getParameter("balance");
            logger.info("UpdateUserCommand balanceString " + balanceString);

            BigDecimal balance = new BigDecimal(balanceString);
            logger.info("UpdateUserCommand balance BigDecimal" + balance);

            //get request to Data Base
            userDAO.updateBalanceByUserId(balance, currentUser.getId());

            BigDecimal balance1 = currentUser.getBalance();

            balance = balance.add(balance1);

            currentUser.setBalance(balance);
            req.getSession().setAttribute("currentuser", currentUser);
            return "profile.jsp";


        }
        return "error.jsp";

    }
}
