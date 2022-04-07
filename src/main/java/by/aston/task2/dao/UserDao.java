package by.aston.task2.dao;

import by.aston.task2.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDao {

    public void createUser(User user) {
        String username = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();
        final String sql = "INSERT INTO users (username,login,password) VALUES (?,?,?)";

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> findUserByLog(String log) {
        final String sql = "SELECT * FROM users WHERE login=?";
        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, log);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("username"), log, resultSet.getString("password"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
