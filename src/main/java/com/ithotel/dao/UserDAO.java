package com.ithotel.dao;

import com.ithotel.entity.PersonalInformation;
import com.ithotel.entity.User;
import com.ithotel.util.DAOUtils;
import com.ithotel.util.QuerySQL;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

//class get query to Data Base table User
public class UserDAO implements DAO<User, Integer> {

    private final DataBaseConnection ds;
    final static Logger logger = Logger.getLogger(UserDAO.class);

    //create class and get Data Source if is not null
    public UserDAO() throws DAOException {
        this.ds = DataBaseConnection.getInstance();
        logger.info("Created UserDao and given Data Source");
    }

    //insert User
    @Override
    public boolean insert(User model) throws DAOException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("insertUser - created Connection");
            statement = con.prepareStatement(QuerySQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            logger.info("insertUser - created statement");
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            int result = statement.executeUpdate();
            logger.info("insertUser - executeUpdate");
            if (result > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    model.setId(rs.getInt(1));
                    logger.info("insertUser - set Id user and method return true");
                    return true;
                } else {
                    logger.debug("insertUser - return false");
                    return false;
                }
            }

        } catch (SQLException e) {
            logger.error("insertUser - catch SQLException", e);
            throw new DAOException("Can not insert User. ", e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(statement);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return false;
    }

    //create User with Personal Information
    public boolean createUserWithPersonalInformation(User model, PersonalInformation personalInformation) throws DAOException {
        Connection con = null;
        PreparedStatement psForuser = null;
        PreparedStatement psForPS = null;
        ResultSet rsForUser = null;
        try {
            con = ds.getConnection();
            logger.info("createUserWithPersonalInformation - created Connection");
            con.setAutoCommit(false);

            psForuser = con.prepareStatement(QuerySQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            logger.info("createUserWithPersonalInformation - created statement");
            psForuser.setString(1, model.getLogin());
            psForuser.setString(2, model.getPassword());
            int result = psForuser.executeUpdate();
            logger.info("createUserWithPersonalInformation - executeUpdate");
            if (result > 0) {
                rsForUser = psForuser.getGeneratedKeys();
                if (rsForUser.next()) {
                    model.setId(rsForUser.getInt(1));
                    logger.info("createUserWithPersonalInformation - set Id user " + model.getId());
                } else {
                    logger.debug("createUserWithPersonalInformation - can not set Id user");
                }
            }

            psForPS = con.prepareStatement(QuerySQL.INSERT_PERSONAL_INFORMATION_BY_ID_USER);
            logger.info("createUserWithPersonalInformation " + psForPS);
            psForPS.setString(1, personalInformation.getFirstName());
            psForPS.setString(2, personalInformation.getLastName());
            psForPS.setString(3, personalInformation.getEmail());
            psForPS.setString(4, personalInformation.getPhone());
            psForPS.setInt(5, model.getId());

            psForPS.executeUpdate();
            logger.info("createUserWithPersonalInformation - executeUpdate");

            con.commit();
            return true;
        } catch (SQLException e) {
            DAOUtils.rollback(con);
            logger.info("SQlExeption is = " + e);
            logger.error("createUserWithPersonalInformation - catch SQLException", e);
            throw new DAOException("Can not insert User. ", e);
        } finally {
            DAOUtils.setAutoCommit(con, true);
            DAOUtils.close(rsForUser);
            DAOUtils.close(psForuser);
            DAOUtils.close(psForPS);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
    }

    //get User by Id
    @Override
    public User getById(Integer id) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("getById - created Connection");
            ps = con.prepareStatement(QuerySQL.GET_USER_BY_ID);
            logger.info("getById - created statement");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            logger.info("getById - executeQuery");
            user = mapUser(rs);

        } catch (SQLException e) {
            logger.error("can not get user by " + id, e);
            throw new DAOException("can not get User by " + id, e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return user;
    }

    public User getByLogin(String login) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("getByLogin - created Connection");
            ps = con.prepareStatement(QuerySQL.GET_USER_BY_LOGIN);
            logger.info("getByLogin - created statement");
            ps.setString(1, login);
            rs = ps.executeQuery();
            logger.info("getById - executeQuery");
            user = mapUser(rs);

        } catch (SQLException e) {
            logger.error("can not get user by " + login, e);
            throw new DAOException("can not get User by " + login, e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return user;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            logger.info("set id");
            user.setLogin(rs.getString("login"));
            logger.info("set login");
            user.setPassword(rs.getString("password"));
            logger.info("set password");
            user.setRole(rs.getInt("role_id"));
            logger.info("set role");
            user.setBalance(rs.getBigDecimal("balance"));
            logger.info("set balance");
        }
        return user;
    }

    public PersonalInformation getPersonalInformationByUserId(int id) throws DAOException {
        PersonalInformation personalInformation = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("getPersonalInformationByUserId - created Connection");
            ps = con.prepareStatement(QuerySQL.GET_PERSONAL_INFORMATION_BY_USER_ID);
            logger.info("getPersonalInformationByUserId - created statement");

            ps.setInt(1, id);
            rs = ps.executeQuery();
            logger.info("getPersonalInformationByUserId - executeQuery");

            personalInformation = mapPersonalInformation(rs);

            if (personalInformation == null) {
                throw new DAOException("Personal Information by if " + id + "  is null");
            }

        } catch (SQLException e) {
            logger.error("can not get user by " + id, e);
            throw new DAOException("can not get User by " + id, e);
        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return personalInformation;
    }

    private PersonalInformation mapPersonalInformation(ResultSet rs) throws SQLException {
        PersonalInformation personalInformation = new PersonalInformation();
        if (rs.next()) {
            personalInformation.setFirstName(rs.getString("firstname"));
            logger.info("set firstname");

            personalInformation.setLastName(rs.getString("lastname"));
            logger.info("set lastname");

            personalInformation.setEmail(rs.getString("email"));
            logger.info("set email");

            personalInformation.setPhone(rs.getString("phone"));
            logger.info("set phone");
        }
        return personalInformation;
    }

    //update user by id
    public boolean updateUserById(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            logger.info("updateUserById - created Connection");

            ps = con.prepareStatement(QuerySQL.UPDATE_USER_BY_ID);
            logger.info("get prepareStatement" + ps);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                logger.info("update user");
                return true;
            } else {
                logger.error("can not update user");
            }
        } catch (SQLException e) {
            throw new DAOException("can not update user");
        } finally {
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return false;
    }

    //update user by id
    public boolean updatePersonalInformationByUserId(PersonalInformation perInfo, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
            logger.info("updatePersonalInformationByUserId - created Connection");

            ps = con.prepareStatement(QuerySQL.UPDATE_PERSONAL_INFORMATION_BY_USER_ID);
            logger.info("get prepareStatement" + ps);

            ps.setString(1, perInfo.getFirstName());
            ps.setString(2, perInfo.getLastName());
            ps.setString(3, perInfo.getPhone());
            ps.setString(4, perInfo.getEmail());
            ps.setInt(5, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                logger.info("update personal information user with id = " + id);
                return true;
            } else {
                logger.error("can not update user with id = " + id);
            }
        } catch (SQLException e) {
            throw new DAOException("can not update user with id = " + id);
        } finally {
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return false;
    }

    //update user balance by user id
    public boolean updateBalanceByUserId(BigDecimal balance, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            logger.info("updateBalanceByUserId - created Connection");

            ps = con.prepareStatement(QuerySQL.UPDATE_USER_BALANCE_BY_ID);
            ps.setBigDecimal(1, balance);
            ps.setInt(2, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                logger.info("update user balance with user id = " + id);
                return true;
            } else {
                logger.error("can not user balance with user id = " + id);
            }

        } catch (SQLException e) {
            throw new DAOException("can not user balance with user id = " + id);
        } finally {
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return false;
    }

    public PersonalInformation getPersonalInformationByOrderId(int orderId) throws DAOException {
        PersonalInformation personalInformation = new PersonalInformation();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            logger.info("getPersonalInformationByOrderId get connection" + con);

            ps = con.prepareStatement(QuerySQL.GET_PERSONAL_INFORMATION_BY_ORDER_ID);
            ps.setInt(1, orderId);
            logger.info("getPersonalInformationByOrderId get prepared statement" + con);

            rs = ps.executeQuery();
            logger.info("getPersonalInformationByOrderId get result set" + rs);

            while (rs.next()) {
                personalInformation.setFirstName(rs.getString("firstname"));
                personalInformation.setLastName(rs.getString("lastname"));
                personalInformation.setPhone(rs.getString("phone"));
                personalInformation.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            logger.warn("Can not get personal information by order id= " + orderId);
            throw new DAOException("Can not get personal information by order id= " + orderId);

        } finally {
            DAOUtils.close(rs);
            DAOUtils.close(ps);
            DAOUtils.close(con);
            logger.debug("Closed Result Set, PrepareStatement and Connection");
        }
        return personalInformation;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean delete(User model) {
        return false;
    }

    @Override
    public boolean update(User model) {
        return false;
    }


}
