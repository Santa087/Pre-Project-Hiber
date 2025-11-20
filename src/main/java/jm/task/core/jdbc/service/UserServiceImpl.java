package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoJDBCImpl(Util.getConnection());
    }

    @Override
    public void createUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            userDao.createUsersTable(connection);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при создании таблицы пользователей", e);
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            userDao.dropUsersTable(connection);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении таблицы пользователей", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            userDao.saveUser(connection, name, lastName, age);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при сохранении пользователя", e);
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            userDao.removeUserById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        try (Connection connection = Util.getConnection()) {
            return userDao.getAllUsers(connection);
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении списка пользователей", e);
        }
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            userDao.cleanUsersTable(connection);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Ошибка при очистке таблицы пользователей", e);
        }
    }
}
