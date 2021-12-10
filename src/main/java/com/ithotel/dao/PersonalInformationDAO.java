package com.ithotel.dao;

import com.ithotel.entity.PersonalInformation;
import com.ithotel.util.DAOUtils;
import com.ithotel.util.QuerySQL;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonalInformationDAO implements DAO<PersonalInformation, Integer>{

    private final DataBaseConnection ds;
    final static Logger logger = Logger.getLogger(PersonalInformationDAO.class);

    //create class and get Data Source if is not null
    public PersonalInformationDAO() throws DAOException {
        this.ds = DataBaseConnection.getInstance();
        logger.info("Created PersonalInformationDAO and given Data Source");
    }

    //method check email is exist
    public boolean isExistEmail(String email) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        PersonalInformation personalInformation = new PersonalInformation();

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if(rs.next()){
                personalInformation.setEmail(rs.getString("email"));
            }

            if(personalInformation.getEmail()!= null){
                //if exist
                return true;
            } else {
                //if not exist
                return false;
            }

        } catch (SQLException e){
            logger.error("Can not do isExistEmail, SQLException = " + e.getMessage());
            throw new DAOException(e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }

    //method check email is exist
    public boolean isExistPhone(String phone) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        PersonalInformation personalInformation = new PersonalInformation();

        try {
            con = ds.getConnection();
            ps = con.prepareStatement(QuerySQL.CHECK_PERSONAL_INFORMATION_BY_PHONE);
            ps.setString(1, phone);
            rs = ps.executeQuery();

            if(rs.next()){
                personalInformation.setPhone(rs.getString("phone"));
            }

            if(personalInformation.getPhone()!= null){
                //if exist
                return true;
            } else {
                //if not exist
                return false;
            }

        } catch (SQLException e){
            logger.error("Can not do isExistPhone, SQLException = " + e.getMessage());
            throw new DAOException(e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }


    @Override
    public boolean insert(PersonalInformation model) throws DAOException {
        return false;
    }

    @Override
    public PersonalInformation getById(Integer integer) throws DAOException {
        return null;
    }

    @Override
    public List<PersonalInformation> getAll() throws DAOException {
        return null;
    }

    @Override
    public boolean delete(PersonalInformation model) throws DAOException {
        return false;
    }

    @Override
    public boolean update(PersonalInformation model) throws DAOException {
        return false;
    }
}
