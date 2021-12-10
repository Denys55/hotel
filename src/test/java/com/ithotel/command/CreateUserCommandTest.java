package com.ithotel.command;


import com.ithotel.dao.DAOException;
import com.ithotel.dao.DataBaseConnection;
import com.ithotel.dao.PersonalInformationDAO;
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
import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.mockito.Mockito.*;

public class CreateUserCommandTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    User user;
    UserDAO userDAO;
    PersonalInformation psInf;
    PersonalInformationDAO psDAO;
    PreparedStatement psUserDao;
    ResultSet rsUserDao;
    Connection con;
    PreparedStatement psPIDao;
    ResultSet rsPIDao;
    PreparedStatement psPIDaoPhone;
    ResultSet rsPIDaoPhone;
    PreparedStatement psUserDaoCreateOne;
    ResultSet rsUserDaoCreateOne;
    PreparedStatement psUserDaoCreateTwo;
    ResultSet rsUserDaoCreateTwo;

    @Before
    public void setUp() throws SQLException, DAOException {

        user = new User();
        user.setLogin("denis");
        user.setPassword("1111");
        user.setRole(1);
        user.setId(1);

        psInf = new PersonalInformation();
        psInf.setFirstName("Denis");
        psInf.setLastName("Cherep");
        psInf.setEmail("den@gmail.com");
        psInf.setPhone("0991112233");

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        userDAO = mock(UserDAO.class);
        psDAO = mock(PersonalInformationDAO.class);

        psUserDao = mock(PreparedStatement.class);
        rsUserDao = mock(ResultSet.class);
        con = mock(Connection.class);

        psPIDao = mock(PreparedStatement.class);
        rsPIDao = mock(ResultSet.class);

        psPIDaoPhone = mock(PreparedStatement.class);
        rsPIDaoPhone = mock(ResultSet.class);

        psUserDaoCreateOne = mock(PreparedStatement.class);
        rsUserDaoCreateOne = mock(ResultSet.class);
        psUserDaoCreateTwo = mock(PreparedStatement.class);
        rsUserDaoCreateTwo = mock(ResultSet.class);

    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void executeTest() throws SQLException, DAOException, CommandException {

        HttpSession session = mock(HttpSession.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(ds.getConnection()).thenReturn(con);

        when(req.getSession()).thenReturn(session);

        when(rsUserDao.next()).thenReturn(true).thenReturn(false);
        when(rsUserDao.getInt("id")).thenReturn(0);
        when(rsUserDao.getString("login")).thenReturn(null);
        when(rsUserDao.getString("password")).thenReturn(null);
        when(rsUserDao.getInt("role_id")).thenReturn(1);
        when(rsUserDao.getBigDecimal("balance")).thenReturn(null);
        when(rsUserDaoCreateOne.next()).thenReturn(true);
        when(rsUserDaoCreateOne.getInt(1)).thenReturn(1);


        when(rsPIDao.next()).thenReturn(true).thenReturn(false);
        when(rsPIDao.getString("email")).thenReturn(null);
        when(rsPIDaoPhone.getString("phone")).thenReturn(null);

        when(con.prepareStatement(QuerySQL.GET_USER_BY_LOGIN)).thenReturn(psUserDao);
        when(con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_EMAIL)).thenReturn(psPIDao);
        when(con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_PHONE)).thenReturn(psPIDaoPhone);
        when(con.prepareStatement(QuerySQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS)).thenReturn(psUserDaoCreateOne);
        when(con.prepareStatement(QuerySQL.INSERT_PERSONAL_INFORMATION_BY_ID_USER)).thenReturn(psUserDaoCreateTwo);
        when(psUserDao.executeQuery()).thenReturn(rsUserDao);
        when(psPIDao.executeQuery()).thenReturn(rsPIDao);
        when(psPIDaoPhone.executeQuery()).thenReturn(rsPIDaoPhone);
        when(psUserDaoCreateOne.executeUpdate()).thenReturn(1);
        when(psUserDaoCreateOne.getGeneratedKeys()).thenReturn(rsUserDaoCreateOne);


        when(req.getParameter("login")).thenReturn(user.getLogin());
        when(req.getParameter("email")).thenReturn(psInf.getEmail());
        when(req.getParameter("phone")).thenReturn(psInf.getPhone());
        when(psDAO.isExistEmail(psInf.getEmail())).thenReturn(false);
        when(psDAO.isExistPhone(psInf.getPhone())).thenReturn(false);
        when(userDAO.createUserWithPersonalInformation(user, psInf)).thenReturn(true);

        User returnUser = new User();
        when(userDAO.getByLogin(user.getLogin())).thenReturn(returnUser);



        CreateUserCommand command = new CreateUserCommand();
        String actual = command.execute(req, resp);
        String expected = "profile.jsp";

        Assert.assertEquals(actual, expected);

    }

}