package by.aston.task2.dao;

import by.aston.task2.constraints.Constraints;
import by.aston.task2.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDao {

    public void createUser(User user) throws SQLException {
        String username = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement createUser = connection.prepareStatement(Constraints.CREATE_USER)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            createUser.setString(1, username);
            createUser.setString(2, login);
            createUser.setString(3, password);
            createUser.executeUpdate();
        }
    }

    public Optional<User> findUserByLog(String log) throws SQLException {
        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement findUserByLog = connection.prepareStatement(Constraints.SELECT_USER_BY_LOGIN)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            findUserByLog.setString(1, log);
            ResultSet resultSet = findUserByLog.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("username"), log, resultSet.getString("password"));
                return Optional.of(user);
            }

        }
        return Optional.empty();
    }
}
