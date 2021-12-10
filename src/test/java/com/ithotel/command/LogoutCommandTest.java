package com.ithotel.command;


import com.ithotel.dao.DAOException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutCommandTest {

    @Test
    public void executeTest() throws DAOException, CommandException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        LogoutCommand command = new LogoutCommand();

        String actual = command.execute(req, resp);
        String expected = "index.jsp";

        Assert.assertEquals(actual, expected);

    }

}