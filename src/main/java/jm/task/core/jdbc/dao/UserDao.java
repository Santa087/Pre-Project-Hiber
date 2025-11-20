package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable(Connection connection) throws SQLException;

    void dropUsersTable(Connection connection) throws SQLException;

    void saveUser(Connection connection, String name, String lastName, byte age) throws SQLException;

    void removeUserById(Connection connection, long id) throws SQLException;

    List<User> getAllUsers(Connection connection) throws SQLException;

    void cleanUsersTable(Connection connection) throws SQLException;
}
