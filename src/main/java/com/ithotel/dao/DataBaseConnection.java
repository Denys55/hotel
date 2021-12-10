package com.ithotel.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


//use pattern Singleton create Data Source connection
public class DataBaseConnection {

    final static Logger logger = Logger.getLogger(DataBaseConnection.class);
    private static DataBaseConnection instance;

    public static synchronized DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
            logger.debug("create DataBaseConnection");
        }
        return instance;
    }

    //////////////////////////////////////

    private DataSource ds;

    private DataBaseConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
        } catch (NamingException ex) {
            logger.error(ex);
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
            logger.debug("get Connection");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new IllegalStateException("Cannot obtain a connection", ex);
        }
        return con;
    }
    /*
    final static Logger logger = Logger.getLogger(DataBaseConnection.class);
    private static MysqlDataSource dataSource;

    private DataBaseConnection() throws DAOException {
        logger.error("Try used private constructor DataBaseConnection ");
        throw new DAOException("You can create this class");
    }

    //Create connection to MySQL Data Base
    public static synchronized MysqlDataSource getDataSource() throws DAOException {

        if(dataSource == null) {

            Properties props = new Properties();

            String fileName = "src/main/resources/database.properties";

            try (FileInputStream fis = new FileInputStream(fileName)) {
                props.load(fis);
                logger.info("load properties for DataSource");
            } catch (IOException ex) {
                logger.error("IOException, can not read properties", ex);
                throw new DAOException("Can not get dataSource", ex);
            }

            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("mysql.url"));
            dataSource.setUser(props.getProperty("mysql.username"));
            dataSource.setPassword(props.getProperty("mysql.password"));
            logger.info("Data source created, configured and given away");

            return dataSource;
        }
        logger.info("Data source given away");
        return dataSource;
    }

     */
}
