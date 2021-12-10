package com.ithotel.util;

import com.ithotel.command.ChangeOrderByAdminCommand;
import com.ithotel.dao.DAOException;
import com.ithotel.dao.OrderDAO;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.Order;
import org.apache.log4j.Logger;

import java.util.TimerTask;

//Class for timer, which will launch, when status order will change on 3 (invoiced)
public class TimerForPay extends TimerTask {


    final static Logger logger = Logger.getLogger(TimerForPay.class);


    private int orderId;
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        try {
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getById(orderId);
            logger.info("TimerForPay#run get order = " + order);

            RoomDAO roomDAO = new RoomDAO();

            if(order.getStatusOfOrder().getId()==3) {
                order.setStatusOfOrder(5);
                boolean updateOrderStatus = orderDAO.update(order);
                logger.debug("TimerForPay#run update status of order = " + updateOrderStatus);

                boolean updateStatusOfRoom = roomDAO.updateRoomStatusInRommByRoomId(roomId, 1);
                logger.debug("TimerForPay#run update status of order = " + updateStatusOfRoom);

            } else {
                Thread.currentThread().interrupt();
            }

        } catch (DAOException e) {
            logger.info("TimerForPay#run can not get OrderDau");
        }



    }
}
