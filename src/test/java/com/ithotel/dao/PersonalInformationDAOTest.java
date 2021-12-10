package com.ithotel.dao;


import com.ithotel.entity.PersonalInformation;
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

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class PersonalInformationDAOTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    PersonalInformationDAO psDAO;

    @Before
    public void setUp() throws DAOException {

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        psDAO = new PersonalInformationDAO();
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void isExistEmailTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getString("email")).thenReturn("den@test.ua");


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_EMAIL)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        boolean existEmail = psDAO.isExistEmail("den@test.ua");

        Assert.assertTrue(existEmail);
    }

    @Test
    public void isExistPhoneTest() throws SQLException, DAOException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getString("phone")).thenReturn("0991112233");


        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_PHONE)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        boolean result = psDAO.isExistPhone("0991112233");

        Assert.assertTrue(result);
    }

}