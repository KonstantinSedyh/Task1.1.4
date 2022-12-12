package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table was created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users VALUES (NULL, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User " + id + " was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastName, age FROM Users";
        List<User> userList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            System.out.println(userList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table clean");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
