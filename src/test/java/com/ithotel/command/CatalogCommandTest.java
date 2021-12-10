package com.ithotel.command;


import com.ithotel.dao.DAOException;
import com.ithotel.dao.DataBaseConnection;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.Room;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class CatalogCommandTest {

    DataBaseConnection ds;
    MockedStatic<DataBaseConnection> dsStatic;
    RoomDAO roomDAO;
    Room room;
    Connection con;
    PreparedStatement psOne;
    ResultSet rsOne;
    PreparedStatement psTwo;
    ResultSet rsTwo;

    @Before
    public void setUp() throws SQLException, DAOException {

        room = new Room();
        room.setName("one");
        room.setId(1);
        room.setPrice(new BigDecimal(10));
        room.setStatusOfRoom(1);
        room.setNumbersOfBed(2);
        room.setClassOfRoom(1);

        ds = mock(DataBaseConnection.class);

        dsStatic = mockStatic(DataBaseConnection.class);
        dsStatic.when(()->DataBaseConnection.getInstance()).thenReturn(ds);

        roomDAO = new RoomDAO();
        con = mock(Connection.class);
        psOne = mock(PreparedStatement.class);
        rsOne = mock(ResultSet.class);
        psTwo = mock(PreparedStatement.class);
        rsTwo = mock(ResultSet.class);
    }

    @After
    public void tearDown() {
        dsStatic.close();
    }

    @Test
    public void executeTest() throws DAOException, CommandException, SQLException {

        HttpSession session = mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("page")).thenReturn("1");
        when(req.getParameter("sort")).thenReturn(null);
        when(req.getSession()).thenReturn(session);

        when(ds.getConnection()).thenReturn(con);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        //when(roomDAO.getAllWithLimit(1, 3)).thenReturn(rooms);
        //when(roomDAO.getCountRoom()).thenReturn(1);

        when(con.prepareStatement(QuerySQL.GET_ALL_ROOM_LIMIT)).thenReturn(psOne);
        when(psOne.executeQuery()).thenReturn(rsOne);
        when(rsOne.next()).thenReturn(true);
        when(rsOne.next()).thenReturn(false);
        when(rsOne.getInt("id")).thenReturn(room.getId());
        when(rsOne.getInt("numbers_bed")).thenReturn(room.getNumbersOfBed());
        when(rsOne.getInt("class_id")).thenReturn(1);
        when(rsOne.getInt("statuses_room_id")).thenReturn(1);
        when(rsOne.getBigDecimal("price")).thenReturn(new BigDecimal(10));
        when(rsOne.getString("name")).thenReturn("one");

        when(con.prepareStatement(QuerySQL.COUNT_OF_ROOM)).thenReturn(psTwo);
        when(psTwo.executeQuery()).thenReturn(rsTwo);
        when(rsTwo.next()).thenReturn(true);
        when(rsTwo.getInt(1)).thenReturn(1);

        CatalogCommand command = new CatalogCommand();

        String actual = command.execute(req, resp);
        String expected = "catalog.jsp";
        Assert.assertEquals(actual, expected);

    }
}