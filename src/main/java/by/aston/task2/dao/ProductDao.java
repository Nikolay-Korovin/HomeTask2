package by.aston.task2.dao;

import by.aston.task2.entity.*;

import java.sql.*;
import java.util.*;

public class ProductDao {
    public void createProduct(Product product, User user) {
        String productName = product.getProductName();
        long userId = user.getId();
        final String sql = "INSERT INTO products (productname) VALUES (?)";
        final String sql2 = "SELECT id FROM products WHERE productname = (?)";
        final String sql3 = "INSERT INTO orders (userid, productid) VALUES (?,?)";

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement statement2 = connection.prepareStatement(sql2);
             PreparedStatement statement3 = connection.prepareStatement(sql3)) {
            long productId = 0;

            statement.setString(1, productName);
            statement.executeUpdate();

            statement2.setString(1, productName);
            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                productId = resultSet.getLong("id");
            }

            statement3.setLong(1, userId);
            statement3.setLong(2, productId);
            statement3.executeUpdate();
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

    public boolean deleteProduct(Product product, User user) {
        String productName = product.getProductName();
        long userId = user.getId();
        final String sql0 = "SELECT id FROM products WHERE productname = (?)";
        final String sql1 = "SELECT userid FROM orders WHERE productid = (?)";
        final String sql2 = "DELETE FROM products WHERE productName=?";
        final String sql3 = "DELETE FROM orders WHERE productid = (?)";


        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement0 = connection.prepareStatement(sql0);
             PreparedStatement userIdFormOrdersWhereProductId = connection.prepareStatement(sql1);
             PreparedStatement statement2 = connection.prepareStatement(sql2);
             PreparedStatement statement3 = connection.prepareStatement(sql3)) {
            long productIdInProducts = 0;
            long userIdInOrders = 0;

            statement0.setString(1, productName);
            ResultSet resultSet1 = statement0.executeQuery();
            if (resultSet1.next()) {
                productIdInProducts = resultSet1.getLong("id");
            }

            userIdFormOrdersWhereProductId.setLong(1, productIdInProducts);
            ResultSet resultSet2 = userIdFormOrdersWhereProductId.executeQuery();
            if (resultSet2.next()) {
                userIdInOrders = resultSet2.getInt("userid");
                if (userId == userIdInOrders) {
                    statement3.setLong(1, productIdInProducts);
                    statement3.executeUpdate();

                    statement2.setString(1, productName);
                    statement2.executeUpdate();
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Map<Integer,String> getAllProducts(User user) {
        long userID = user.getId();
        Map<Integer,String> products = new HashMap<>();
        final String sql = "SELECT products.productname, orders.productId FROM orders JOIN products ON orders.productId = products.id WHERE userId = (?)";

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, userID);
            ResultSet resultSet1 = statement.executeQuery();
            while (resultSet1.next()) {
                products.put(resultSet1.getInt("productid"),new Product(resultSet1.getString("productname")).getProductName());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }
}
