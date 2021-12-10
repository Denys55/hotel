package com.ithotel.util;

import com.ithotel.command.CommandException;
import com.ithotel.dao.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAOUtils {

    final static Logger logger = Logger.getLogger(DAOUtils.class);

    //method help close connection, statement and result set
    public static void close(AutoCloseable cl) throws DAOException {
        logger.debug("Try to close"+cl);
        if(cl!=null) {
            try {
                cl.close();
            } catch (Exception e) {
                logger.error("Can not to close"+cl, e);
                throw new DAOException("Can not close - ", e);
            }
        }
    }

    //method rollback for connection
    public static void rollback(Connection con) throws DAOException{
        if (con != null) {
            try {
                con.rollback();
                logger.info("to do rollback for transaction");
            } catch (SQLException e) {
                throw new DAOException("Can not do rollback for transaction");
            }
        }
    }

    public static void setAutoCommit(Connection con, boolean b) throws DAOException {
        if (con != null) {
            try {
                con.setAutoCommit(b);
                logger.info("setAutocomit - " + b);
            } catch (SQLException e) {
                throw new DAOException("Can not setAutocomit - " + b);
            }
        }
    }

    //method convector String to Date
    public static Date convectorStringToDate(String s) throws CommandException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = format.parse(s);
            sqlDate = new java.sql.Date(utilDate.getTime());
            logger.info("sql Date = " + sqlDate);
        } catch (ParseException e) {
            logger.warn("can not converting String to Date");
            throw new CommandException("can not converting String to Date");
        }
        return sqlDate;
    }
}
