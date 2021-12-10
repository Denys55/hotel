package com.ithotel.util;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Timer;

//util class for business logic methods
public class BusinessLogic {

    final static Logger logger = Logger.getLogger(BusinessLogic.class);

    private BusinessLogic() {
    }

    // method tha checking the users balance for the possibility of paying the bill
    public static boolean checkUserBalanceForPayingBill(BigDecimal balance, BigDecimal bill) {
        int i = balance.compareTo(bill);
        logger.info("result for compare balance to bill = " + i);

        if(i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    // the method calculates the amount of payment for the order
    public static BigDecimal calsulateTotalCost(BigDecimal price, Date checkIn, Date checkOut) {
        logger.info("go to calsulateTotalCost");
        long time1 = checkIn.getTime();
        long time2 = checkOut.getTime();
        long days = (time2 - time1)/86400/1000;
        logger.info("calsulateTotalCost days = " + days);
        BigDecimal result = price.multiply(BigDecimal.valueOf(days));
        logger.info("calsulateTotalCost result = " + result);
        return result;

    }

    //method that launch timer for check order status (invoiced)
    public static void launchTimer(int orderId, int roomId) {

        TimerForPay timerForPay = new TimerForPay();
        timerForPay.setOrderId(orderId);
        logger.info("BusinessLogic#launchTimer set orderId " + timerForPay.getOrderId());

        timerForPay.setRoomId(roomId);
        logger.info("BusinessLogic#launchTimer set roomId " + timerForPay.getRoomId());

        Timer timer = new Timer(false);
        logger.info("BusinessLogic#launchTimer createTimer " + timer);

        //time for schedule
        long twoDays = 86400000l * 2;

        //create schedule
        timer.schedule(timerForPay, twoDays);
    }
}
