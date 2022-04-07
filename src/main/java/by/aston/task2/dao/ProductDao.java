package by.aston.task2.dao;

import by.aston.task2.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductDao {
    public void createProduct(Product product, User user) {
        String name = product.getName();
        long userId = user.getId();
        final String sql = "INSERT INTO products (productName, userid) VALUES (?,?)";

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Product> findProductByName(String name) {
        final String sql = "SELECT * FROM products WHERE productname=?";
        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getString("productname"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
