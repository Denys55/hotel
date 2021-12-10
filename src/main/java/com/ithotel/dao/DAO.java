package com.ithotel.dao;

import java.util.List;

public interface DAO<Entity, Id> {

    boolean insert(Entity model) throws DAOException;
    Entity getById(Id id) throws DAOException;
    List<Entity> getAll()throws DAOException;
    boolean delete(Entity model) throws DAOException;
    boolean update(Entity model) throws DAOException;

}
