package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("alex", "petrov", (byte) 20);
        userDao.saveUser("tony", "sidorov", (byte) 22);
        userDao.saveUser("ivan", "ivaniv", (byte) 23);
        userDao.saveUser("sasha", "tanya", (byte) 34);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
