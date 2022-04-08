package by.aston.task2.service;

import by.aston.task2.dao.ProductDao;
import by.aston.task2.entity.Product;
import by.aston.task2.entity.User;

import java.util.Map;
import java.util.Optional;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public void createProduct(Product product, User user) {
        if (productDao.findProductByName(product.getProductName()).isEmpty()) {
            productDao.createProduct(product, user);
        }
    }

    public Optional<Product> findProductByName(String name) {
        return productDao.findProductByName(name);
    }

    public boolean deleteProduct(Product product, User user) {
        return productDao.deleteProduct(product, user);
    }

    public Map<Integer, String> getAllProducts(User user){
        return productDao.getAllProducts(user);
    }
}
