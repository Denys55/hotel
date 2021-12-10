package com.ithotel.command;


import com.ithotel.dao.DAOException;
import com.ithotel.dao.DataBaseConnection;
import com.ithotel.dao.RoomDAO;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class PaymentOrderCommandTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    User user;
    RoomDAO roomDAO;
    Connection con;
    PreparedStatement psOne;
    PreparedStatement psTwo;
    PreparedStatement psThree;

    @Before
    public void setUp() throws SQLException, DAOException {
        user = new User();
        user.setId(1);
        user.setLogin("denis");
        user.setPassword("1111");
        user.setRole(1);
        user.setBalance(new BigDecimal(1000));

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        roomDAO = new RoomDAO();
        con = mock(Connection.class);

        psOne = mock(PreparedStatement.class);
        psTwo = mock(PreparedStatement.class);
        psThree = mock(PreparedStatement.class);
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void executeTest() throws DAOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(req.getParameter("orderId")).thenReturn("1");

        when(req.getParameter("total_cost")).thenReturn("10");

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(QuerySQL.SUBTRACK_USER_BALANCE)).thenReturn(psOne);
        when(psOne.executeUpdate()).thenReturn(1);

        when(con.prepareStatement(QuerySQL.UPDATE_ORDER_STATUS_ORDER)).thenReturn(psTwo);
        when(psTwo.executeUpdate()).thenReturn(1);

        when(con.prepareStatement(QuerySQL.UPDATE_STATUS_OF_ROOM)).thenReturn(psThree);
        when(psThree.executeUpdate()).thenReturn(1);

        boolean result = roomDAO.changeOrderIdAndUserBalanceAndStatusOfRoom(user.getId(), new BigDecimal(10), 1, 1);
        Assert.assertTrue(result);

    }
}