package by.aston.task2.service;

import by.aston.task2.dao.ProductDao;
import by.aston.task2.entity.Product;
import by.aston.task2.entity.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public void createProduct(Product product, User user) throws SQLException {
        if (productDao.findProductByName(product.getProductName()).isEmpty()) {
            productDao.createProduct(product, user);
        }
    }

    public Optional<Product> findProductByName(String name) throws SQLException {
        return productDao.findProductByName(name);
    }

    public boolean deleteProduct(Product product, User user) throws SQLException {
        return productDao.deleteProduct(product, user);
    }

    public Map<Integer, String> getAllProducts(User user) throws SQLException {
        return productDao.getAllProducts(user);
    }
}
