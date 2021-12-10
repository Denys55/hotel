package com.ithotel.command;


import com.ithotel.dao.DAOException;
import com.ithotel.dao.DataBaseConnection;
import com.ithotel.dao.OrderDAO;
import com.ithotel.entity.Order;
import com.ithotel.entity.User;
import com.ithotel.util.QuerySQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientOrdersCommandTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    User user;

    @Before
    public void setUp() throws SQLException, DAOException {

        user = new User();
        user.setId(1);
        user.setLogin("denis");
        user.setPassword("1111");
        user.setRole(1);

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }



     @Test
    public void executeTest() throws DAOException, CommandException, SQLException {
         HttpSession session = mock(HttpSession.class);
         when(session.getAttribute("currentUser")).thenReturn(user);

         HttpServletRequest req = mock(HttpServletRequest.class);
         HttpServletResponse resp = mock(HttpServletResponse.class);
         when(req.getSession()).thenReturn(session);


         Order order = new Order();
         order.setId(1);
         order.setRoomId(1);
         order.setTimeOrder(Date.valueOf("2021-11-11"));
         order.setStatusOfOrder(1);
         order.setNumbersOfPlace(2);
         order.setClassOfRoomById(1);
         order.setCheckIN(Date.valueOf("2021-11-12"));
         order.setCheckOut(Date.valueOf("2021-11-13"));
         order.setTotalCost(new BigDecimal(100));

         List<Order> orders = new ArrayList<>();
         orders.add(order);

         OrderDAO orderDAO = mock(OrderDAO.class);
         when(orderDAO.findOrderStatusinvoicedByUserId(user.getId())).thenReturn(orders);

         ResultSet rs = mock(ResultSet.class);
         when(rs.next())
                 .thenReturn(true)
                 .thenReturn(false);

         when(rs.getInt("id")).thenReturn(order.getId());
         when(rs.getInt("number_place")).thenReturn(2);
         when(rs.getDate("time_order")).thenReturn(order.getTimeOrder());
         when(rs.getDate("check_in")).thenReturn(order.getCheckIN());
         when(rs.getDate("check_out")).thenReturn(order.getCheckOut());
         when(rs.getInt("class_id")).thenReturn(1);
         when(rs.getInt("statuses_order_id")).thenReturn(1);
         when(rs.getBigDecimal("total_cost")).thenReturn(order.getTotalCost());
         when(rs.getInt("room_id")).thenReturn(order.getRoomId());

         PreparedStatement ps = mock(PreparedStatement.class);
         when(ps.executeQuery()).thenReturn(rs);

         Connection con = mock(Connection.class);
         when(con.prepareStatement(QuerySQL.FIND_ORDER_STATUS_INVOICED_BY_USER_ID)).thenReturn(ps);

         when(ds.getConnection()).thenReturn(con);


         ClientOrdersCommand command = new ClientOrdersCommand();
         String actual = command.execute(req, resp);
         String expected = "clientOrders.jsp";

         Assert.assertEquals(actual, expected);
     }
}