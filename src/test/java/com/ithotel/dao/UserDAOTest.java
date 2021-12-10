package com.ithotel.dao;

import com.ithotel.entity.User;
import com.ithotel.util.QuerySQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.sql.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class UserDAOTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    User user;
    UserDAO userDAO;

    @Before
    public void setUp() throws SQLException, DAOException {

        user = new User();
        user.setLogin("denis");
        user.setPassword("1111");
        user.setRole(1);

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        userDAO = new UserDAO();
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void insertTest() throws SQLException, DAOException {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        boolean insert = userDAO.insert(user);

        Assert.assertTrue(insert);

    }

    @Test
    public void getByIdTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("login")).thenReturn("denis");
        when(rs.getString("password")).thenReturn("1111");
        when(rs.getInt("role_id")).thenReturn(1);
        when(rs.getBigDecimal("balance")).thenReturn(new BigDecimal(100));


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_USER_BY_ID)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        User byId = userDAO.getById(1);
        String actual = byId.getLogin();
        String expected = "denis";

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void getByLoginTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("login")).thenReturn("denis");
        when(rs.getString("password")).thenReturn("1111");
        when(rs.getInt("role_id")).thenReturn(1);
        when(rs.getBigDecimal("balance")).thenReturn(new BigDecimal(100));


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_USER_BY_LOGIN)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        User getByLogin = userDAO.getByLogin("denis");
        Integer actual = getByLogin.getId();
        Integer expected = 1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void updateUserByIdTest() throws SQLException, DAOException {

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeUpdate()).thenReturn(1);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.UPDATE_USER_BY_ID)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        user.setId(4);
        boolean actual = userDAO.updateUserById(user);

        Assert.assertTrue(actual);
    }

}