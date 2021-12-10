package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.OrderDAO;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.Order;
import com.ithotel.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//class responsible for admin page
public class AdminPageCommand implements Command{

    final static Logger logger = Logger.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        //check role
        User user = (User) req.getSession().getAttribute("currentUser");

        if(user==null){
            return "login.jsp";
        }

        String role = user.getRole().getTitle();

        if(!role.equalsIgnoreCase("manager")) {
            return "login.jsp";
        }

        //get status order for get orders with this status
        String statusOrders = req.getParameter("statusOrders");
        logger.info("String status for order = " + statusOrders);

        OrderDAO orderDAO = new OrderDAO();
        RoomDAO roomDAO = new RoomDAO();

        List<Integer> allIdRooms = roomDAO.getAllIdRooms();

        req.setAttribute("allIdRooms", allIdRooms);

        //if we can not parameter statusOrders
        if(statusOrders==null || statusOrders.equals("0")) {
            List<Order> allOders = orderDAO.getAll();
            req.setAttribute("allOrders", allOders);

            return "admin.jsp";
        } else {
            int idStatusOfOrders = Integer.parseInt(statusOrders);
            logger.info("status for order = " + idStatusOfOrders);
            List<Order> ordersByOrdersStatus = orderDAO.getOrdersByOrdersStatus(idStatusOfOrders);
            req.setAttribute("allOrders", ordersByOrdersStatus);

            return "admin.jsp";
        }
    }
}
