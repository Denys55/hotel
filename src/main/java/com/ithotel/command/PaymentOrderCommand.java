package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.User;
import com.ithotel.util.BusinessLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class PaymentOrderCommand implements Command{

    final static Logger logger = Logger.getLogger(PaymentOrderCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        int userId = currentUser.getId();
        logger.info("user id= " + userId);

        BigDecimal userBalance = currentUser.getBalance();
        logger.info("user balance= " + userBalance);

        String stringOrderId = req.getParameter("orderId");
        int orderId = Integer.parseInt(stringOrderId);
        logger.info("order id= " + orderId);

        String stringTotalCost = req.getParameter("totalCost");
        BigDecimal totalCost = new BigDecimal(stringTotalCost);
        logger.info("total cost=  " + totalCost);

        boolean resultCheckBalanceToBill = BusinessLogic.checkUserBalanceForPayingBill(userBalance, totalCost);

        if (resultCheckBalanceToBill) {
            logger.info("Balance > bill");

            currentUser.setBalance(userBalance.subtract(totalCost));

            RoomDAO roomDAO = new RoomDAO();
            int roomId = roomDAO.findRoomidByOrderId(orderId);
            logger.info("room id = " + roomId);

            roomDAO.changeOrderIdAndUserBalanceAndStatusOfRoom(userId, totalCost, orderId, roomId);
            return "thankyoupage.jsp";

        } else {
            logger.warn("You need to top up your balance");
            throw new CommandException("You need to top up your balance");
        }
    }
}
