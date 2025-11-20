package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUsersTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS User (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users создана");
        } catch (SQLException e) {
            throw new SQLException("Ошибка при создании таблицы", e);
        }
    }

    @Override
    public void dropUsersTable(Connection connection) throws SQLException {
        String sql = "DROP TABLE IF EXISTS User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            throw new SQLException("Ошибка удаления таблицы", e);
        }
    }

    @Override
    public void saveUser(Connection connection, String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO User (name, lastName, age) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new SQLException("Ошибка при создании пользователя", e);
        }
    }

    @Override
    public void removeUserById(Connection connection, long id) throws SQLException {
        String sql = "DELETE FROM User WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id " + id + " удален");
        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении пользователей", e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable(Connection connection) throws SQLException {
        String sql = "TRUNCATE TABLE User";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users очищена");
        } catch (SQLException e) {
            throw new SQLException("Ошибка при очистке таблицы", e);
        }
    }
}
