package by.aston.task2.service;

import by.aston.task2.dao.UserDao;
import by.aston.task2.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void createUser(User user) throws SQLException {
        if (userDao.findUserByLog(user.getLogin()).isEmpty()) {
            userDao.createUser(user);
        }
    }

    public Optional<User> findUserByLog(String log) throws SQLException {
        return userDao.findUserByLog(log);
    }
}
