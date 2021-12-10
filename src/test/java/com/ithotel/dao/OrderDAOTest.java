package com.ithotel.dao;

import com.ithotel.entity.Order;
import com.ithotel.util.QuerySQL;
import org.junit.*;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderDAOTest {
    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;

    @Before
    public void setUp() throws SQLException {

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }


    @Test
    public void getByIdtest() throws DAOException, SQLException {
        /*

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getInt("number_place")).thenReturn(2);
        when(rs.getDate("time_order")).thenReturn(Date.valueOf("2021-11-11"));
        when(rs.getDate("check_in")).thenReturn(Date.valueOf("2021-11-12"));
        when(rs.getDate("check_out")).thenReturn(Date.valueOf("2021-11-13"));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_order_id")).thenReturn(1);
        when(rs.getBigDecimal("total_cost")).thenReturn(BigDecimal.valueOf(100));
        when(rs.getInt("room_id")).thenReturn(1);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ORDER_BY_ID)).thenReturn(ps);

        DataBaseConnection ds = mock(DataBaseConnection.class);
        when(ds.getConnection()).thenReturn(con);

        MockedStatic<DataBaseConnection> dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

         */

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getInt("number_place")).thenReturn(2);
        when(rs.getDate("time_order")).thenReturn(Date.valueOf("2021-11-11"));
        when(rs.getDate("check_in")).thenReturn(Date.valueOf("2021-11-12"));
        when(rs.getDate("check_out")).thenReturn(Date.valueOf("2021-11-13"));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_order_id")).thenReturn(1);
        when(rs.getBigDecimal("total_cost")).thenReturn(BigDecimal.valueOf(100));
        when(rs.getInt("room_id")).thenReturn(1);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ORDER_BY_ID)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);


        OrderDAO orderDAO = new OrderDAO();

        Order byId = orderDAO.getById(1);

        Integer actual = byId.getId();
        Integer expected = 1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getAllTest() throws DAOException, SQLException {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getInt("number_place")).thenReturn(2);
        when(rs.getDate("time_order")).thenReturn(Date.valueOf("2021-11-11"));
        when(rs.getDate("check_in")).thenReturn(Date.valueOf("2021-11-12"));
        when(rs.getDate("check_out")).thenReturn(Date.valueOf("2021-11-13"));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_order_id")).thenReturn(1);
        when(rs.getBigDecimal("total_cost")).thenReturn(BigDecimal.valueOf(100));
        when(rs.getInt("room_id")).thenReturn(1);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ALL_ORDERS)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);


        OrderDAO orderDAO = new OrderDAO();

        List<Order> all = orderDAO.getAll();
        Integer actual = all.size();
        Integer expected = 1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getOrdersByOrdersStatus() throws DAOException, SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getInt("number_place")).thenReturn(2);
        when(rs.getDate("time_order")).thenReturn(Date.valueOf("2021-11-11"));
        when(rs.getDate("check_in")).thenReturn(Date.valueOf("2021-11-12"));
        when(rs.getDate("check_out")).thenReturn(Date.valueOf("2021-11-13"));
        when(rs.getInt("class_id")).thenReturn(1);
        when(rs.getInt("statuses_order_id")).thenReturn(1);
        when(rs.getBigDecimal("total_cost")).thenReturn(BigDecimal.valueOf(100));
        when(rs.getInt("room_id")).thenReturn(1);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_ORDERS_BY_ORDERS_STATUS)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);


        OrderDAO orderDAO = new OrderDAO();
        List<Order> ordersByOrdersStatus = orderDAO.getOrdersByOrdersStatus(1);

        Integer actual = ordersByOrdersStatus.size();
        Integer expected = 1;

        Assert.assertEquals(actual, expected);
    }


}