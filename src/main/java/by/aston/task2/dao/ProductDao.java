package by.aston.task2.dao;

import by.aston.task2.constraints.Constraints;
import by.aston.task2.entity.*;

import java.sql.*;
import java.util.*;

public class ProductDao {
    public void createProduct(Product product, User user) throws SQLException {
        if (product != null && user != null) {
            String productName = product.getProductName();
            long userId = user.getId();
            long productId = 0;

            try (Connection connection = ConnectionPoolManager.getConnection();
                 PreparedStatement insertProduct = connection.prepareStatement(Constraints.INSERT_PRODUCT);
                 PreparedStatement selectProductIdByProductName = connection.prepareStatement(Constraints.SELECT_PRODUCTID_BY_PRODUCTNAME);
                 PreparedStatement insertUserIdAndProductIdIntoOrders = connection.prepareStatement(Constraints.INSERT_USERID_AND_PRODUCTID_INTO_ORDERS)) {

                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                insertProduct.setString(1, productName);
                insertProduct.executeUpdate();

                selectProductIdByProductName.setString(1, productName);
                ResultSet productIds = selectProductIdByProductName.executeQuery();
                if (productIds.next()) {
                    productId = productIds.getLong("id");
                }

                insertUserIdAndProductIdIntoOrders.setLong(1, userId);
                insertUserIdAndProductIdIntoOrders.setLong(2, productId);
                insertUserIdAndProductIdIntoOrders.executeUpdate();

                connection.commit();
            }
        }
    }

    public Optional<Product> findProductByName(String name) throws SQLException {

        try (Connection connection = ConnectionPoolManager.getConnection();
             PreparedStatement findProductByProductName = connection.prepareStatement(Constraints.SELECT_FROM_PRODUCTS_BY_PRODUCTNAME)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            findProductByProductName.setString(1, name);
            ResultSet resultSet = findProductByProductName.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(resultSet.getString("productname"));
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Product product, User user) throws SQLException {
        if (product != null && user != null) {
            String productName = product.getProductName();
            long userId = user.getId();

            try (Connection connection = ConnectionPoolManager.getConnection();
                 PreparedStatement productIdByProductName = connection.prepareStatement(Constraints.SELECT_PRODUCTID_FROM_PRODUCTS_BY_PRODUCTNAME);
                 PreparedStatement userIdFormOrdersWhereProductId = connection.prepareStatement(Constraints.SELECT_USERID_FROM_ORDERS_BY_PRODUCTID);
                 PreparedStatement deleteOrderByProductId = connection.prepareStatement(Constraints.DELETE_FROM_ORDERS_BY_PRODUCTID);
                 PreparedStatement deleteProductByProductName = connection.prepareStatement(Constraints.DELETE_FROM_PRODUCTS_BY_PRODUCTNAME)) {
                long productIdInProducts = 0;
                long userIdInOrders = 0;

                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                productIdByProductName.setString(1, productName);
                ResultSet productId = productIdByProductName.executeQuery();
                if (productId.next()) {
                    productIdInProducts = productId.getLong("id");
                }

                userIdFormOrdersWhereProductId.setLong(1, productIdInProducts);
                ResultSet userIds = userIdFormOrdersWhereProductId.executeQuery();
                if (userIds.next()) {
                    userIdInOrders = userIds.getInt("userid");
                    if (userId == userIdInOrders) {
                        deleteOrderByProductId.setLong(1, productIdInProducts);
                        deleteOrderByProductId.executeUpdate();

                        deleteProductByProductName.setString(1, productName);
                        deleteProductByProductName.executeUpdate();
                        connection.commit();
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }

    public Map<Integer, String> getAllProducts(User user) throws SQLException {
        if (user != null) {
            long userID = user.getId();
            Map<Integer, String> products = new HashMap<>();

            try (Connection connection = ConnectionPoolManager.getConnection();
                 PreparedStatement getAllProducts = connection.prepareStatement(Constraints.GET_ALLPRODUCTS)) {
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                getAllProducts.setLong(1, userID);
                ResultSet resultSet1 = getAllProducts.executeQuery();
                while (resultSet1.next()) {
                    products.put(resultSet1.getInt("productid"), new Product(resultSet1.getString("productname")).getProductName());
                }

            }
            return products;
        }else{
            return null;
        }
    }
}
