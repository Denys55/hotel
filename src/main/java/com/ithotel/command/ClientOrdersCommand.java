package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.OrderDAO;
import com.ithotel.entity.Order;
import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//get order to user
public class ClientOrdersCommand implements Command{

    final static Logger logger = Logger.getLogger(ClientOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        if(currentUser == null ){
            return "login.jsp";
        }

        int id = currentUser.getId();
        logger.info("current user id= " + id);

        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderStatusinvoicedByUserId = new ArrayList<>();
        //get order with status invoiced by user id
        try {
            orderStatusinvoicedByUserId = orderDAO.findOrderStatusinvoicedByUserId(id);
            logger.info(orderStatusinvoicedByUserId);
        } catch (DAOException e) {
            logger.info(e);
        }

        //write invoiced orders to session
        req.getSession().setAttribute("ordersInvoiced", orderStatusinvoicedByUserId);

        return "clientOrders.jsp";
    }
}
