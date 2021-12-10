package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.OrderDAO;
import com.ithotel.entity.ClassRoom;
import com.ithotel.entity.Order;
import com.ithotel.util.DAOUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


//Command that make order
public class OrderCommand implements Command {

    final static Logger logger = Logger.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        Order order = new Order();
        OrderDAO orderDAO = new OrderDAO();

        //Check sourse of order
        String sourceOfOrder = req.getParameter("sourceOfOrder");
        logger.info("source of order is " + sourceOfOrder);

        //Get parameters user id
        String userIdString = req.getParameter("userId");
        int userId = Integer.parseInt(userIdString);
        logger.info("User id = " + userId);

        //Get parameters checkIn
        String checkin = req.getParameter("checkin");
        logger.info("check in = " + checkin);

        //Get parameters checkOut
        String checkout = req.getParameter("checkout");
        logger.info("check out = " + checkout);

        //Set to order checkIn and checkOut
        order.setCheckIN(DAOUtils.convectorStringToDate(checkin));
        order.setCheckOut(DAOUtils.convectorStringToDate(checkout));


        if (sourceOfOrder.equals("catalog")) {
            String roomIdStrint = req.getParameter("roomId");
            int roomId = Integer.parseInt(roomIdStrint);
            logger.info("roomId = " + roomId);

            String classOfRoom = req.getParameter("class");
            logger.info("classOfRoom = " + classOfRoom);

            String beds = req.getParameter("numbersOfPlace");
            int numbersOfPlace = Integer.parseInt(beds);
            logger.info("numbers of beds " + numbersOfPlace);

            order.setClassOfRoom(classOfRoom);
            order.setNumbersOfPlace(numbersOfPlace);
            order.setStatusOfOrder(1);

            orderDAO.createOrder(order, userId, roomId);
            return "thankyoupage.jsp";
        }

        else if (sourceOfOrder.equals("profile")) {
            //get parameter numberBeds
            String stringNumersOfBed = req.getParameter("numberBeds");
            int numbersOfbeds = Integer.parseInt(stringNumersOfBed);
            logger.info("numbersOfbeds = " + numbersOfbeds);

            //get parameter classRoom
            String classOfRoom = req.getParameter("classRoom");
            logger.info("classOfRoom = " + classOfRoom);

            //get parameter classRoom
            ClassRoom classRoom = ClassRoom.valueOf(classOfRoom.toUpperCase(Locale.ROOT));
            int idClassofroom = classRoom.getId();
            logger.info("classRoom = " + idClassofroom);

            order.setNumbersOfPlace(numbersOfbeds);
            order.setClassOfRoom(classOfRoom);
            order.setClassOfRoom(classOfRoom);
            order.setStatusOfOrder(1);

            //get request to Data Base
            orderDAO.createOrderFromProfile(order, userId);
            return "thankyoupage.jsp";
        } else {
            throw new CommandException("Can not make order");
        }
    }
}
