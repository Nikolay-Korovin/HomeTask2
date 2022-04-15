package java.by.aston.task2.service;

import java.by.aston.task2.dao.ProductDao;
import java.by.aston.task2.entity.Product;
import java.by.aston.task2.entity.User;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public void createProduct(Product product, User user) throws SQLException {
        if (productDao.findByName(product.getProductName()) == null) {
            productDao.save(product);
        }
    }

    public List<Product> findProductByName(String name) throws SQLException {
        return productDao.findByName(name);
    }

    public void deleteProduct(Product product) throws SQLException {
        productDao.delete(product);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDao.findAll();
    }
}
