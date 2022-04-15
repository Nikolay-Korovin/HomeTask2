package java.by.aston.task2.dao;

import java.by.aston.task2.entity.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class ProductDao {
    public void save(Product product) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    public void update(Product product) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(product);
        tx1.commit();
        session.close();
    }

    public void delete(Product product){
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    public Product findByID(long id){
        return HibernateSessionFactoryUtil.getSession().get(Product.class,id);
    }

    public List<Product> findByName(String productname){
        Query query = HibernateSessionFactoryUtil.getSession().createQuery(String.format("FROM Product P WHERE P.productname = '%s'" , productname));
        return (List<Product>) query.list();
    }

    public List<Product> findAll(){
        return (List<Product>) HibernateSessionFactoryUtil.getSession().createQuery("From Product").list();
    }
}
