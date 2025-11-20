package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            UserServiceImpl userService = new UserServiceImpl();



            userService.createUsersTable();

            userService.saveUser("John", "Smith", (byte) 20);
            userService.saveUser("Alice", "Henderson", (byte) 25);
            userService.saveUser("Mark", "Brown", (byte) 31);
            userService.saveUser("Bob", "Davis", (byte) 38);

            List<User> users = userService.getAllUsers();
            System.out.println("Все пользователи:");
            for (User user : users) {
                System.out.println(user);
            }

            connection.commit();
            System.out.println("Изменения сохранены");

            userService.removeUserById(1);
            userService.cleanUsersTable();
            userService.dropUsersTable();

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Произошла ошибка: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Ошибка отката транзаккции" + rollbackEx.getMessage());
            }
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия соединения");
            }
        }
    }
}
