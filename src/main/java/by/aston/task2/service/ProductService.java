package by.aston.task2.service;

import by.aston.task2.dao.ProductDao;
import by.aston.task2.entity.Product;
import by.aston.task2.entity.User;

import java.util.Optional;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public void createItem(Product product, User user) {
        if (productDao.findProductByName(product.getName()).isEmpty()) {
            productDao.createProduct(product, user);
        }
    }

    public Optional<Product> findProductByName(String name) {
        return productDao.findProductByName(name);
    }
}
