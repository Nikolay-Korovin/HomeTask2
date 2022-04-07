package by.aston.task2.service;

import by.aston.task2.dao.UserDao;
import by.aston.task2.entity.User;

public class RegistrationService {
    private final UserDao userDao = new UserDao();
    public void createUser(User user){
        if(userDao.findUserByLog(user.getLogin()).isEmpty()){
            userDao.createUser(user);
        }
    }
}
