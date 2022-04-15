package java.by.aston.task2.dao;

import java.by.aston.task2.entity.Product;
import java.by.aston.task2.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static Session getSession() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);

            } catch (HibernateException e) {

                e.printStackTrace();
            }
        }
        return sessionFactory.openSession();
    }



}