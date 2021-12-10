package com.ithotel.listener;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    final static Logger logger = Logger.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        logger.info("ContextListener#contextInitialized get Servlet context = " + ctx);

        // I18n ininialization

        // obtain file name with locales descriptions
        String localesFileName = ctx.getInitParameter("locales");

        // obtain real path on server
        String localesFileRealPath = ctx.getRealPath(localesFileName);

        // local descriptions
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            logger.error("ContextListener#contextInitialized get error IOException = " + e);
        }

        // save descriptions to servlet context
        ctx.setAttribute("locales", locales);

        logger.debug("locales ==> " + locales);
    }
}
