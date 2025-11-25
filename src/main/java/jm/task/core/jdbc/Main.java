package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Connection connection = null;
        UserService userService = new UserServiceImpl();
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

        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}

