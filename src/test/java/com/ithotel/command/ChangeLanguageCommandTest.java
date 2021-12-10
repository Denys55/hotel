package com.ithotel.command;

import com.ithotel.dao.DAOException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;


public class ChangeLanguageCommandTest {

    @Test
    public void executeTest() throws DAOException, CommandException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getQueryString()).thenReturn("index.jsp");

        ChangeLanguageCommand changeLanguageCommand = new ChangeLanguageCommand();

        String actual = changeLanguageCommand.execute(req, resp);
        String expected = "changeLocale.jsp";

        Assert.assertEquals(actual, expected);

    }

}