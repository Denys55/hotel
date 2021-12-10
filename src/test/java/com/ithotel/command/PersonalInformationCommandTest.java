package com.ithotel.command;


import com.ithotel.dao.DAOException;
import com.ithotel.dao.DataBaseConnection;
import com.ithotel.dao.UserDAO;
import com.ithotel.entity.PersonalInformation;
import com.ithotel.entity.User;
import com.ithotel.util.QuerySQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import static org.mockito.Mockito.*;

public class PersonalInformationCommandTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;

    @Before
    public void setUp() throws SQLException, DAOException {

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
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("orderId")).thenReturn("1");

        PersonalInformation persInf = new PersonalInformation();
        persInf.setFirstName("Denis");
        persInf.setLastName("Cherep");
        persInf.setEmail("den@gmail.com");
        persInf.setPhone("0991112233");


        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getString("firstname")).thenReturn(persInf.getFirstName());
        when(rs.getString("lastname")).thenReturn(persInf.getLastName());
        when(rs.getString("phone")).thenReturn(persInf.getPhone());
        when(rs.getString("email")).thenReturn(persInf.getEmail());

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(ps.getGeneratedKeys()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(QuerySQL.GET_PERSONAL_INFORMATION_BY_ORDER_ID)).thenReturn(ps);

        when(ds.getConnection()).thenReturn(con);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.getPersonalInformationByUserId(1)).thenReturn(persInf);

        when(req.getHeader("Referer")).thenReturn("http://ithotel/index.jsp");

        PersonalInformationCommand psCommand = new PersonalInformationCommand();

        String actual = psCommand.execute(req, resp);
        String expected = "personalinformation.jsp";

        Assert.assertEquals(actual, expected);

    }

}