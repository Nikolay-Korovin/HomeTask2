package java.by.aston.task2.service;

import java.by.aston.task2.dao.UserDao;
import java.by.aston.task2.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void createUser(User user) throws SQLException {
        if (userDao.findBylogin(user.getLogin()) != null) {
            userDao.save(user);
        }
    }

    public List<User> findUserByLog(String log) throws SQLException {
        return userDao.findBylogin(log);
    }

    public User findById(String id){
        return userDao.findByID(Long.parseLong(id));
    }
}
