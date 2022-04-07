package by.aston.task2.service;

import by.aston.task2.dao.UserDao;
import by.aston.task2.entity.User;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void createUser(User user) {
        if (userDao.findUserByLog(user.getLogin()).isEmpty()) {
            userDao.createUser(user);
        }
    }

    public Optional<User> findUserByLog(String log) {
        return userDao.findUserByLog(log);
    }
}
