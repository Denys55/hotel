package com.ithotel.dao;

import com.ithotel.entity.Room;
import com.ithotel.entity.User;
import com.ithotel.util.QuerySQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RoomDAOTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    RoomDAO roomDAO;

    @Before
    public void setUp() throws SQLException {

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        roomDAO = new RoomDAO();
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void getByIdTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("name")).thenReturn("roomOne");
        when(rs.getInt("numbers_bed")).thenReturn(2);
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal(10));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_room_id")).thenReturn(1);


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ROOM_BY_ID)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        Room byId = roomDAO.getById(1);
        String actual = byId.getName();
        String expected = "roomOne";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getAllIdRoomsTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("name")).thenReturn("roomOne");
        when(rs.getInt("numbers_bed")).thenReturn(2);
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal(10));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_room_id")).thenReturn(1);


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ALL_ID_ROOMS)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        List<Integer> allIdRooms = roomDAO.getAllIdRooms();
        boolean result = allIdRooms.isEmpty();

        Assert.assertFalse(result);

    }

}