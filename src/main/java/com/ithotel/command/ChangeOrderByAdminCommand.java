package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.OrderDAO;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.ClassRoom;
import com.ithotel.entity.Order;
import com.ithotel.entity.Room;
import com.ithotel.util.BusinessLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

//class help admin changed order
public class ChangeOrderByAdminCommand implements Command{

    final static Logger logger = Logger.getLogger(ChangeOrderByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        String stringOrderId = req.getParameter("orderId");
        int orderId = Integer.parseInt(stringOrderId);
        logger.info("ChangeOrderByAdminCommand get oderId= " + orderId);

        String stringRoomId = req.getParameter("changeRoomId");

        int roomId = Integer.parseInt(stringRoomId);
        logger.info("ChangeOrderByAdminCommand get roomId= " + roomId);

        RoomDAO roomDAO = new RoomDAO();
        Room room = roomDAO.getById(roomId);
        logger.info("ChangeOrderByAdminCommand get room by id" + room);

        ClassRoom classOfRoom = room.getClassOfRoom();
        int classRoomId = classOfRoom.getId();

        int numbersOfBed = room.getNumbersOfBed();

        BigDecimal price = room.getPrice();
        logger.info("ChangeOrderByAdminCommand get room price" + price);

        String stringCheckIn =  req.getParameter("checkin");
        Date checkIn = Date.valueOf(stringCheckIn);
        logger.info("ChangeOrderByAdminCommand get check in " + checkIn);

        String stringCheckOut = req.getParameter("checkout");
        Date checkOut = Date.valueOf(stringCheckOut);
        logger.info("ChangeOrderByAdminCommand get check out " + checkOut);

        BigDecimal totalCost = BusinessLogic.calsulateTotalCost(price, checkIn, checkOut);

        String stringStatusOrder = req.getParameter("changeStatusOrders");
        int statusOrder = Integer.parseInt(stringStatusOrder);
        logger.info("ChangeOrderByAdminCommand get status of order " + statusOrder);


        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getById(orderId);
        logger.info("ChangeOrderByAdminCommand get order= " + order);

        if (statusOrder == 3) {
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            order.setTimeOrder(date);
            BusinessLogic.launchTimer(orderId, roomId);

        }

        order.setStatusOfOrder(statusOrder);
        order.setCheckIN(checkIn);
        order.setCheckOut(checkOut);
        order.setTotalCost(totalCost);
        order.setClassOfRoomById(classRoomId);
        order.setNumbersOfPlace(numbersOfBed);

        // make update

        orderDAO.updateOrderAndOrderHasRoom(order, roomId);

        //get link back
        String back = req.getHeader("Referer");
        logger.info("ChangeOrderByAdminCommand get backlink= " + back);

        //update link, delete http://localhost:8080/ithotel/;
        String substring = back.substring(30);
        logger.info("ChangeOrderByAdminCommand update backlink= " + substring);


        return substring;
    }
}
