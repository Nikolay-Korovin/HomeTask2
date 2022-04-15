package java.by.aston.task2.dao;

import java.by.aston.task2.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public User findByID(Long id) {
        return HibernateSessionFactoryUtil.getSession().get(User.class, id);
    }

    public List<User> findBylogin(String login) {
        Query query = HibernateSessionFactoryUtil.getSession().createQuery(String.format("FROM User U WHERE U.login = '%s'" , login));
        return (List<User>) query.list();
    }

    public List<User> findAll() {
        return (List<User>) HibernateSessionFactoryUtil.getSession().createQuery("From User").list();
    }

}
