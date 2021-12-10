package com.ithotel.dao;

import com.ithotel.entity.Room;
import com.ithotel.util.DAOUtils;
import com.ithotel.util.QuerySQL;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements DAO<Room, Integer> {
    private final DataBaseConnection ds;
    final static Logger logger = Logger.getLogger(RoomDAO.class);

    public RoomDAO() {
        this.ds = DataBaseConnection.getInstance();
        logger.info("Created RoomDao and given Data Source");
    }

    @Override
    public boolean insert(Room model) throws DAOException {
        return false;
    }

    @Override
    public Room getById(Integer id) throws DAOException {
        Room room = new Room();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            logger.info("room by id get connection" + con);

            ps = con.prepareStatement(QuerySQL.GET_ROOM_BY_ID);
            logger.info("room by id get connection" + ps);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                room = mapRoom(rs);
            }

        } catch (SQLException e) {
            logger.warn("Can not get room by id=" + id);
            throw new DAOException("Can not get room by id=" + id);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return room;
    }

    @Override
    public List<Room> getAll() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> list = new ArrayList<>();
        try {
            con = ds.getConnection();
            logger.info("getAllRoom - created Connection");
            ps = con.prepareStatement(QuerySQL.GET_ALL_ROOM);
            logger.info("getAllRoom - created statement");
            rs = ps.executeQuery();
            logger.info("getAllRoom - executeQuery");
            while (rs.next()) {
                Room room = mapRoom(rs);
                list.add(room);
            }
        } catch (SQLException e) {
            logger.error("allGet Room - catch SQLException", e);
            throw new DAOException("Can not get all Room. ", e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return list;
    }

    public List<Room> getAllWithLimit(int start, int count) throws DAOException {
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> list = new ArrayList<>();
        try {
            con = ds.getConnection();
            logger.info("getAllWithLimit - created Connection");
            ps = con.prepareStatement(QuerySQL.GET_ALL_ROOM_LIMIT);
            logger.info("getAllWithLimit - created statement");
            ps.setInt(1, start);
            ps.setInt(2, count);
            rs = ps.executeQuery();
            logger.info("getAllWithLimit - executeQuery");
            while (rs.next()) {
                Room room = mapRoom(rs);
                list.add(room);
            }
        } catch (SQLException e) {
            logger.error("allGet Room - catch SQLException", e);
            throw new DAOException("Can not get all Room. ", e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return list;
    }

    public List<Room> getAllWithLimitAndOrderBy(int start, int count, String sort) throws DAOException {
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> list = new ArrayList<>();
        try {
            con = ds.getConnection();
            logger.info("getAllWithLimitAndOrderBy - created Connection");
            String query = QuerySQL.SORT_ROOM + sort + QuerySQL.LIMIT;
            ps = con.prepareStatement(query);
            logger.info("getAllWithLimitAndOrderBy - created statement");
            ps.setInt(1, start);
            ps.setInt(2, count);
            logger.info(ps);
            logger.info("getAllWithLimitAndOrderBy - sort= " + sort);
            rs = ps.executeQuery();
            logger.info("getAllWithLimitAndOrderBy - executeQuery");
            while (rs.next()) {
                Room room = mapRoom(rs);
                list.add(room);
            }
        } catch (SQLException e) {
            logger.error("getAllWithLimitAndOrderBy Room - catch SQLException", e);
            throw new DAOException("Can not get room. ", e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        logger.info("get list in getAllWithLimitAndOrderBy" + list);
        return list;
    }

    private void sortBy(Connection con, String sort) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("sortBy- created Connection");
            ps = con.prepareStatement(QuerySQL.SORT_ROOM);
            logger.info("sortBy - created statement");
            ps.setString(1, sort);
            rs = ps.executeQuery();
            logger.info("sortBy - executeQuery");
        } catch (SQLException e) {
            logger.error("sortBy - catch SQLException", e);
            throw new DAOException("Can not sort by " + sort + " room");
        }
    }

    //get count room
    public int getCountRoom() throws DAOException {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("getCountRoom - created Connection");
            ps = con.prepareStatement(QuerySQL.COUNT_OF_ROOM);
            logger.info("getCountRoom - created statement");
            rs = ps.executeQuery();
            logger.info("getCountRoom - executeQuery");
            if (rs.next()) {
                result = rs.getInt(1);
                logger.info("getCountRoom - get int from query");
            }
        } catch (SQLException e) {
            logger.error("getCountRoom - catch SQLException", e);
            throw new DAOException("Can not get count Room");
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return result;
    }

    public boolean changeOrderIdAndUserBalanceAndStatusOfRoom(int userId, BigDecimal bill, int orderId, int roomId) throws DAOException {
        //status order id paid
        int statusOrderId = 4;

        //
        int statusRoomid = 3;

        Connection con = null;
        PreparedStatement psChangeUserBalance = null;
        PreparedStatement psChangeOrderId = null;
        PreparedStatement psChangeStatusRoom = null;

        try {
            con = ds.getConnection();
            logger.info("changeOrderIdAndUserBalance - created Connection");
            con.setAutoCommit(false);
            psChangeUserBalance = con.prepareStatement(QuerySQL.SUBTRACK_USER_BALANCE);
            psChangeUserBalance.setBigDecimal(1, bill);
            psChangeUserBalance.setInt(2, userId);
            int result = psChangeUserBalance.executeUpdate();
            logger.info("change user balance, result = " + result);

            psChangeOrderId = con.prepareStatement(QuerySQL.UPDATE_ORDER_STATUS_ORDER);
            psChangeOrderId.setInt(1, statusOrderId);
            psChangeOrderId.setInt(2, orderId);
            int result2 = psChangeOrderId.executeUpdate();
            logger.info("change order id in order, result = " + result2);

            psChangeStatusRoom = con.prepareStatement(QuerySQL.UPDATE_STATUS_OF_ROOM);
            psChangeStatusRoom.setInt(1, statusRoomid);
            psChangeStatusRoom.setInt(2, roomId);
            int result3 = psChangeOrderId.executeUpdate();
            logger.info("change status room, result = " + result3);
            con.commit();
            return true;
        } catch (SQLException e) {
            DAOUtils.rollback(con);
            logger.warn("Can not paid for order");
            throw new DAOException("Can not paid for order");
        } finally {
            DAOUtils.setAutoCommit(con, true);
            DAOUtils.close(psChangeStatusRoom);
            DAOUtils.close(psChangeOrderId);
            DAOUtils.close(psChangeUserBalance);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }


    private Room mapRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        logger.info("room - set id");
        room.setName(rs.getString("name"));
        logger.info("room - set name");
        room.setNumbersOfBed(rs.getInt("numbers_bed"));
        logger.info("room - set numbers_bed");
        room.setPrice(rs.getBigDecimal("price"));
        logger.info("room - set price");
        room.setClassOfRoom(rs.getInt("class_id"));
        logger.info("room - set class_id");
        room.setStatusOfRoom(rs.getInt("statuses_room_id"));
        logger.info("room - set statuses_room_id");
        return room;
    }

    //method get all id rooms
    public List<Integer> getAllIdRooms() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Integer> listId = new ArrayList<>();

        try {
            con = ds.getConnection();
            logger.info("getAllIdRooms - created Connection");

            ps = con.prepareStatement(QuerySQL.GET_ALL_ID_ROOMS);
            rs = ps.executeQuery();
            logger.info("getAllIdRooms maked result set = " + rs);

            while (rs.next()) {
                listId.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            logger.info("Can not get all rooms id");
            throw new DAOException("Can not get all rooms id");
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return listId;
    }

    public int findRoomidByOrderId(int orderId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = ds.getConnection();
            logger.info("findRoomidByOrderId - created Connection");
            ps = con.prepareStatement(QuerySQL.FIND_ROOM_ID_BY_ORDER_ID);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            logger.warn("cant fint room id by order id = " + orderId);
            throw new DAOException("cant fint room id by order id = " + orderId);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return result;
    }

    public boolean updateRoomStatusInRommByRoomId(int roomId, int statusRoom) throws DAOException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            logger.info("RoomDAO#updateRoomStatusInRommByRoomId - created Connection");
            ps = con.prepareStatement(QuerySQL.UPDATE_STATUSROOM_IN_ROOM_BY_ID);
            ps.setInt(1, statusRoom);
            ps.setInt(2, roomId);

            int result = ps.executeUpdate();
            logger.info("RoomDAO#updateRoomStatusInRommByRoomId result = " + result);

            return true;
        } catch (SQLException e) {
            logger.warn("updateRoomStatusInRommByRoomId can not update status of room");
            throw new DAOException("updateRoomStatusInRommByRoomId can not update status of room");
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }

    @Override
    public boolean delete(Room model) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Room model) throws DAOException {
        return false;
    }
}
