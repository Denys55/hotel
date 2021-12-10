package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.PersonalInformationDAO;
import com.ithotel.dao.UserDAO;
import com.ithotel.entity.PersonalInformation;
import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.PreparedStatement;

public class CreateUserCommand implements Command{

    final static Logger logger = Logger.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        User user = new User();
        UserDAO userDAO = new UserDAO();
        String login = req.getParameter("login");
        User byLogin = userDAO.getByLogin(login);

        //check login is exist
        if(byLogin.getLogin()!=null){
            logger.error("login is " + byLogin.getLogin());
            throw new CommandException("Login already exists");
        }

        PersonalInformation personalInformation = new PersonalInformation();
        String email = req.getParameter("email");

        PersonalInformationDAO pesInfDAO = new PersonalInformationDAO();

        //check emais is exist
        boolean existEmail = pesInfDAO.isExistEmail(email);

        if(existEmail){
            logger.error("email is exist");
            throw new CommandException("Email already exists");
        }

        String phone = req.getParameter("phone");

        //check phone is exist
        boolean existPhone = pesInfDAO.isExistPhone(phone);

        if(existPhone){
            logger.error("phone is exist");
            throw new CommandException("Phone already exists");
        }

        user.setLogin(login);
        logger.info("CreateUserCommand - set login ");


        user.setPassword(req.getParameter("password"));
        logger.info("CreateUserCommand - set password ");
        logger.info(user);
        user.setRole(1);
        user.setBalance(new BigDecimal(0.0));

        personalInformation.setFirstName(req.getParameter("firstname"));
        logger.info("CreateUserCommand - set firstname ");
        personalInformation.setLastName(req.getParameter("lastname"));
        logger.info("CreateUserCommand - set lastname ");
        personalInformation.setPhone(phone);
        logger.info("CreateUserCommand - set phone ");
        personalInformation.setEmail(email);
        logger.info("CreateUserCommand - set email ");
        logger.info(personalInformation);

        boolean userWithPersonalInformation = userDAO.createUserWithPersonalInformation(user, personalInformation);
        logger.info("CreateUserCommand create user - " + userWithPersonalInformation);

        if (userWithPersonalInformation) {
            req.getSession().setAttribute("currentUser", user);
            req.getSession().setAttribute("personalInformation", personalInformation);
            return "profile.jsp";
        } else {
            throw new DAOException("Can not create user");
        }

    }
}
