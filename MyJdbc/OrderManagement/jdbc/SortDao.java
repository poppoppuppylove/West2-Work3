package MyJdbc.OrderManagement.jdbc;

import MyJdbc.OrderManagement.jdbc.Utils.JdbcUtils;

import java.sql.*;

public class SortDao {

    // 按商品价格升序排序
    public static void sortProductsByPriceAsc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM myproducts ORDER BY product_price ASC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                System.out.println("Product ID: " + productId + ", Product Name: " + productName + ", Product Price: " + productPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 按商品价格降序排序
    public static void sortProductsByPriceDesc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM myproducts ORDER BY product_price DESC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                System.out.println("Product ID: " + productId + ", Product Name: " + productName + ", Product Price: " + productPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 按订单价格升序排序
    public static void sortOrdersByPriceAsc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM yourorders ORDER BY order_price ASC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Order Date: " + orderDate + ", Order Price: " + orderPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 按订单价格降序排序
    public static void sortOrdersByPriceDesc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM yourorders ORDER BY order_price DESC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Order Date: " + orderDate + ", Order Price: " + orderPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 按订单时间升序排序
    public static void sortOrdersByDateAsc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM yourorders ORDER BY order_date ASC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Order Date: " + orderDate + ", Order Price: " + orderPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 按订单时间降序排序
    public static void sortOrdersByDateDesc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM yourorders ORDER BY order_date DESC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Order Date: " + orderDate + ", Order Price: " + orderPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
