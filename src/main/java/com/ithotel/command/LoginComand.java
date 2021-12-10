package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.UserDAO;
import com.ithotel.entity.PersonalInformation;
import com.ithotel.entity.Role;
import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginComand implements Command {

    final static Logger logger = Logger.getLogger(LoginComand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        String login = req.getParameter("login");
        logger.debug("login ==>" + login);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByLogin(login);
        logger.debug(user);

        if (user.getLogin()==null){
            logger.error("user = null");
            throw new CommandException("Can not find user by Login");
        }
        else{
            logger.debug("get user not null");
            String passwordInPut = req.getParameter("password");
            logger.debug("passwordInPut ==>" + passwordInPut);
            String originalPassword = user.getPassword();
            logger.debug("originalPassword ==>" + originalPassword);

            if (passwordInPut.equals(originalPassword)){
                req.getSession().setAttribute("currentUser", user);

                PersonalInformation personalInformationByUserId = userDAO.getPersonalInformationByUserId(user.getId());
                logger.debug("user personal information ==>" + personalInformationByUserId);

                req.getSession().setAttribute("personalInformation", personalInformationByUserId);

                Role role = user.getRole();
                logger.info("LoginComand get user with role = " + role.getTitle());

                if(role.getTitle().equalsIgnoreCase("manager")) {
                    return "admin.jsp";
                }
                else if (role.getTitle().equalsIgnoreCase("client")) {
                return "controller?command=catalog&page=1"; }

            } else{
                throw new CommandException("password wrong");
            }
        }
        return "error.jsp";
    }
}
