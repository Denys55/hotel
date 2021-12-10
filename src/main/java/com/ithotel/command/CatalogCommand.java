package com.ithotel.command;

import com.ithotel.dao.DAOException;
import com.ithotel.dao.RoomDAO;
import com.ithotel.entity.Room;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CatalogCommand implements Command {

    final static Logger logger = Logger.getLogger(CatalogCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        String sort = req.getParameter("sort");
        logger.info("get " + sort);
        RoomDAO roomDAO = new RoomDAO();
        if (sort == null){
            logger.info("sort in if");
            return executeWithOutSort(req, roomDAO);
        } else {
            logger.info("sort in else");
            return executeWithSort(req, roomDAO, sort);
        }
    }

    private String executeWithOutSort(HttpServletRequest req, RoomDAO roomDAO) throws DAOException, CommandException {
        String page = req.getParameter("page");
        logger.info("get " + page);
        int i = Integer.parseInt(page);
        List<Room> listRooms = roomDAO.getAllWithLimit(i, 3);

        int countPage = roomDAO.getCountRoom()/3;
        logger.info("countPage =  " + countPage);
        if (listRooms == null) {
            logger.error("list Room = null");
            throw new CommandException("Error we can get rooms");
        } else {
            req.getSession().setAttribute("currentRooms", listRooms);
            req.setAttribute("page", page);
            req.setAttribute("countPage", countPage);
            return "catalog.jsp";
        }
    }

    private String executeWithSort(HttpServletRequest req, RoomDAO roomDAO, String sort) throws DAOException, CommandException {
        String page = req.getParameter("page");
        logger.info("get " + page);
        int i = Integer.parseInt(page);
        List<Room> listRooms = roomDAO.getAllWithLimitAndOrderBy(i, 3, sort);

        int countPage = roomDAO.getCountRoom()/3;
        logger.info("countPage =  " + countPage);
        if (listRooms == null) {
            logger.error("list Room = null");
            throw new CommandException("Error we can get rooms");
        } else {
            logger.info(listRooms);
            req.getSession().setAttribute("currentRooms", listRooms);
            req.setAttribute("page", page);
            req.setAttribute("countPage", countPage);
            req.setAttribute("sort", sort);
            return "catalog.jsp";
        }
    }
}
