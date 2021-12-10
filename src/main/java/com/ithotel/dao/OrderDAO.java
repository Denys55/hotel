package com.ithotel.dao;

import com.ithotel.entity.Order;
import com.ithotel.util.DAOUtils;
import com.ithotel.util.QuerySQL;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//class get query to Data Base table User
public class OrderDAO implements DAO<Order, Integer> {

    private final DataBaseConnection ds;
    final static Logger logger = Logger.getLogger(OrderDAO.class);

    //create class and get Data Source if is not null
    public OrderDAO() throws DAOException {
        this.ds = DataBaseConnection.getInstance();
        logger.info("Created OrderDAO and given Data Source");
    }

    //for test
    public OrderDAO(DataBaseConnection dataBase) throws DAOException {
        this.ds = dataBase.getInstance();
        logger.info("Created OrderDAO and given Data Source");
    }


    @Override
    public boolean insert(Order order) throws DAOException {
        return false;
    }

    @Override
    public Order getById(Integer id) throws DAOException {
        Order order = new Order();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            logger.info("orderDao#getById get connection " + con);

            ps = con.prepareStatement(QuerySQL.GET_ORDER_BY_ID);
            ps.setInt(1, id);
            logger.info("orderDao#getById maked prepared statement " + ps);

            rs = ps.executeQuery();
            logger.info("get order by id, maked result set = " + rs);

            if (rs.next()) {
                order = mapOrder(rs);
            }

        } catch (SQLException e) {
            logger.warn("Can not get order by id= " + id);
            throw new DAOException("Can not get order by id= " + id);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> orders = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(QuerySQL.GET_ALL_ORDERS);
            logger.info("get all orders create prepared statement = " + ps);

            rs = ps.executeQuery();
            logger.info("get all orders create result set = " + rs);

            while (rs.next()) {
                Order order = mapOrder(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.warn("Can not get all orders");
            throw new DAOException("Can not get all orders");
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return orders;
    }

    public List<Order> getOrdersByOrdersStatus(int statusId) throws DAOException {
        List<Order> orders = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(QuerySQL.GET_ORDERS_BY_ORDERS_STATUS);
            ps.setInt(1, statusId);
            logger.info("get orders by status id create prepared statement = " + ps);

            rs = ps.executeQuery();
            logger.info("get orders by status id create result set = " + rs);

            while (rs.next()) {
                Order order = mapOrder(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.warn("Can not get orders by status id");
            throw new DAOException("Can not get orders by status id");
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return orders;
    }

    private Order mapOrder(ResultSet rs) throws SQLException {

        Order order = new Order();
        order.setId(rs.getInt("id"));
        logger.info("order set id");

        order.setNumbersOfPlace(rs.getInt("number_place"));
        logger.info("order set number of place");

        order.setTimeOrder(rs.getDate("time_order"));
        logger.info("order set time order");

        order.setCheckIN(rs.getDate("check_in"));
        logger.info("order set check in");

        order.setCheckOut(rs.getDate("check_out"));
        logger.info("order set check out");

        order.setClassOfRoomById(rs.getInt("class_id"));
        logger.info("order set class of room");

        order.setStatusOfOrder(rs.getInt("statuses_order_id"));
        logger.info("order set status of order");

        order.setTotalCost(rs.getBigDecimal("total_cost"));
        logger.info("order set total cost");

        order.setRoomId(rs.getInt("room_id"));
        logger.info("order set id room");

        return order;
    }

    @Override
    public boolean delete(Order order) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Order order) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            logger.info("orderDao#update get connection = " + con);

            ps = con.prepareStatement(QuerySQL.UPDATE_ORDER_BY_ID);
            logger.info("orderDao#update get prepared statement = " + ps);

            ps.setInt(1, order.getStatusOfOrder().getId());
            ps.setDate(2, order.getCheckIN());
            ps.setDate(3, order.getCheckOut());
            ps.setBigDecimal(4, order.getTotalCost());
            ps.setInt(5, order.getClassOfRoom().getId());
            ps.setInt(6, order.getNumbersOfPlace());
            ps.setDate(7, order.getTimeOrder());
            ps.setInt(8, order.getId());

            int result = ps.executeUpdate();
            logger.info("orderDao#update maked executeUpdate result= " + result);
            return true;
        } catch (SQLException e) {
            logger.warn("orderDao#update can not update order");
            throw new DAOException("can not update order");
        } finally {
            DAOUtils.close(ps);
            DAOUtils.close(con);
        }

    }


    public boolean updateOrderAndOrderHasRoom(Order order, int roomId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        logger.info("orderDao#update orderId = " + order.getId());
        logger.info("orderDao#update room id = " + roomId);

        try {
            con = ds.getConnection();
            logger.info("orderDao#update get connection = " + con);
            con.setAutoCommit(false);

            ps = con.prepareStatement(QuerySQL.UPDATE_ORDER_BY_ID);
            logger.info("orderDao#update get prepared statement = " + ps);

            ps.setInt(1, order.getStatusOfOrder().getId());
            ps.setDate(2, order.getCheckIN());
            ps.setDate(3, order.getCheckOut());
            ps.setBigDecimal(4, order.getTotalCost());
            ps.setInt(5, order.getClassOfRoom().getId());
            ps.setInt(6, order.getNumbersOfPlace());
            ps.setDate(7, order.getTimeOrder());
            ps.setInt(8, order.getId());

            int result = ps.executeUpdate();
            logger.info("orderDao#update maked executeUpdate result= " + result);

            ps2 = con.prepareStatement(QuerySQL.UPDATE_ORDER_HAS_ROOM_ROOM_ID_BY_ORDER_ID);
            logger.info("orderDao#update maked prepared statement #2= " + ps2);
            ps2.setInt(1, roomId);
            ps2.setInt(2, order.getId());

            int result2 = ps2.executeUpdate();
            logger.info("orderDao#update maked executeUpdate result #2= " + result2);

            con.commit();
        } catch (SQLException e) {
            DAOUtils.rollback(con);
            logger.warn("orderDao#update can not update order");
            throw new DAOException("can not update order");
        } finally {
            DAOUtils.setAutoCommit(con, true);
            DAOUtils.close(rs);
            DAOUtils.close(ps2);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return true;
    }

    //create new Order
    public boolean createOrder(Order order, int userId, int roomId) throws DAOException {

        //set status of room booked
        int statusBooked = 2;

        Connection con = null;
        PreparedStatement psOrder = null;
        PreparedStatement psOrdersUsers = null;
        PreparedStatement psOrdersRooms = null;
        PreparedStatement psStatusBooked = null;
        ResultSet rsOrder = null;
        try {
            con = ds.getConnection();
            logger.info("createOrder - created Connection");

            con.setAutoCommit(false);

            psOrder = con.prepareStatement(QuerySQL.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            psOrder.setDate(1, order.getCheckIN());
            logger.info("create preparedStatement setDate check in");
            psOrder.setDate(2, order.getCheckOut());
            logger.info("create preparedStatement setDate check out");
            psOrder.setInt(3, order.getClassOfRoom().getId());
            logger.info("create preparedStatement setInt class room id");
            psOrder.setInt(4, order.getStatusOfOrder().getId());
            logger.info("create preparedStatement setInt status of order");
            psOrder.setInt(5, order.getNumbersOfPlace());
            logger.info("create preparedStatement setInt number place");

            int result = psOrder.executeUpdate();
            logger.info("createOrder - executeUpdate");
            if (result > 0) {
                rsOrder = psOrder.getGeneratedKeys();
                if (rsOrder.next()) {
                    order.setId(rsOrder.getInt(1));
                }

                logger.info("createOrder - set Id order");
            } else {
                logger.warn("createOrder - can not set Id order");
            }

            psOrdersUsers = con.prepareStatement(QuerySQL.INSERT_ORDER_AND_USER);
            psOrdersUsers.setInt(1, order.getId());
            psOrdersUsers.setInt(2, userId);
            int result2 = psOrdersUsers.executeUpdate();
            logger.info("result for insert table users_orders" + result2);

            psOrdersRooms = con.prepareStatement(QuerySQL.INSERT_ORDER_AND_ROOM);
            psOrdersRooms.setInt(1, order.getId());
            psOrdersRooms.setInt(2, roomId);
            int result3 = psOrdersRooms.executeUpdate();
            logger.info("result for insert table users_rooms" + result3);

            psStatusBooked = con.prepareStatement(QuerySQL.UPDATE_STATUS_OF_ROOM);
            psStatusBooked.setInt(1, statusBooked);
            psStatusBooked.setInt(2, roomId);
            int result4 = psStatusBooked.executeUpdate();
            logger.info("result changed status of room " + result4);

            con.commit();
            return true;

        } catch (SQLException e) {
            DAOUtils.rollback(con);
            logger.warn("Can not createOrder " + e);
            throw new DAOException("Can not createOrder");
        } finally {
            DAOUtils.setAutoCommit(con, true);
            DAOUtils.close(rsOrder);
            DAOUtils.close(psStatusBooked);
            DAOUtils.close(psOrdersRooms);
            DAOUtils.close(psOrdersUsers);
            DAOUtils.close(psOrder);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }

    //create new Order from profile
    public boolean createOrderFromProfile(Order order, int userId) throws DAOException {

        Connection con = null;
        PreparedStatement psOrder = null;
        PreparedStatement psOrdersUsers = null;
        ResultSet rsOrder = null;
        try {
            con = ds.getConnection();
            logger.info("createOrderFromProfile - created Connection");
            con.setAutoCommit(false);
            psOrder = con.prepareStatement(QuerySQL.INSERT_ORDER_FOR_MAKE_ORDER_FROM_PROFILE, Statement.RETURN_GENERATED_KEYS);

            psOrder.setInt(1, order.getNumbersOfPlace());
            logger.info("create preparedStatement setInt numbers of bed");
            psOrder.setDate(2, order.getCheckIN());
            logger.info("create preparedStatement setDate check in");
            psOrder.setDate(3, order.getCheckOut());
            logger.info("create preparedStatement setDate check out");
            psOrder.setInt(4, order.getClassOfRoom().getId());
            logger.info("create preparedStatement setInt class room id");
            psOrder.setInt(5, order.getStatusOfOrder().getId());
            logger.info("create preparedStatement setInt status of order");

            int result = psOrder.executeUpdate();
            logger.info("createOrderFromProfile - executeUpdate");
            if (result > 0) {
                rsOrder = psOrder.getGeneratedKeys();
                if (rsOrder.next()) {
                    order.setId(rsOrder.getInt(1));
                }

                logger.info("createOrderFromProfile - set Id order");
            } else {
                logger.warn("createOrderFromProfile - can not set Id order");
            }

            psOrdersUsers = con.prepareStatement(QuerySQL.INSERT_ORDER_AND_USER);
            psOrdersUsers.setInt(1, order.getId());
            psOrdersUsers.setInt(2, userId);
            int result2 = psOrdersUsers.executeUpdate();
            logger.info("result for insert table users_orders" + result2);

            con.commit();
            return true;

        } catch (SQLException e) {
            DAOUtils.rollback(con);
            logger.warn("Can not createOrderFromProfile " + e);
            throw new DAOException("Can not createOrderFromProfile");
        } finally {
            DAOUtils.setAutoCommit(con, true);
            DAOUtils.close(rsOrder);
            DAOUtils.close(psOrdersUsers);
            DAOUtils.close(psOrder);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }

    //get list orders with status invoiced that find by user id
    public List<Order> findOrderStatusinvoicedByUserId(int id) throws DAOException {
        logger.info("entrance to findOrderStatusinvoicedByUserId");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            con = ds.getConnection();
            logger.info("findOrderStatusinvoicedByUserId - created Connection");
            ps = con.prepareStatement(QuerySQL.FIND_ORDER_STATUS_INVOICED_BY_USER_ID);
            logger.info("findOrderStatusinvoicedByUserId result set = " + ps);
            ps.setInt(1, id);
            logger.info("findOrderStatusinvoicedByUserId result set = " + ps);

            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setNumbersOfPlace(rs.getInt("number_place"));
                order.setTimeOrder(rs.getDate("time_order"));
                order.setCheckIN(rs.getDate("check_in"));
                order.setCheckOut(rs.getDate("check_out"));
                order.setClassOfRoomById(rs.getInt("class_id"));
                order.setStatusOfOrder(rs.getInt("statuses_order_id"));
                order.setTotalCost(rs.getBigDecimal("total_cost"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.warn("can not find order with status invoiced by user id= " + id);
            throw new DAOException("can not find order with status invoiced by user id= " + id);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }

        if (orders.isEmpty()) {
            logger.debug("can not find order with status invoiced by user id= " + id);
            throw new DAOException("can not find order with status invoiced by user id= " + id);
        } else {
            return orders;
        }
    }


}
