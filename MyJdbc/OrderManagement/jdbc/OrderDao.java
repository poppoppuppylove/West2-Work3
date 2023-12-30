package MyJdbc.OrderManagement.jdbc;

import MyJdbc.OrderManagement.jdbc.Utils.JdbcUtils;

import java.sql.*;
import java.util.Date;
@SuppressWarnings("all")
public class OrderDao {

    // 创建订单
    public static void createOrder(int productId, Date orderDate, double orderPrice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 数据验证：检查商品是否存在
            if (!isProductExists(productId)) {
                System.out.println("ID为 " + productId + " 的商品不存在。");
                return;
            }

            connection = JdbcUtils.getConnection();
            // 将自动提交设置为false以启动事务
            connection.setAutoCommit(false);

            String sql = "INSERT INTO yourorders (product_id, order_date, order_price) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.setTimestamp(2, new Timestamp(orderDate.getTime()));
            preparedStatement.setDouble(3, orderPrice);
            preparedStatement.executeUpdate();

            // 如果一切正常，则提交事务
            connection.commit();
        } catch (SQLException e) {
            // 在异常情况下回滚事务
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交为true并释放资源
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                JdbcUtils.release(connection, preparedStatement, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 删除订单
    public static void deleteOrder(int productId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM yourorders WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                JdbcUtils.release(connection, preparedStatement, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 修改订单信息
    public static void updateOrder(int orderId, int productId, Date newOrderDate, double newOrderPrice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 数据验证：检查商品是否存在
            if (!isProductExists(productId)) {
                System.out.println("ID为 " + productId + " 的商品不存在。");
                return;
            }

            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE yourorders SET product_id = ?, order_date = ?, order_price = ? WHERE order_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.setTimestamp(2, new Timestamp(newOrderDate.getTime()));
            preparedStatement.setDouble(3, newOrderPrice);
            preparedStatement.setInt(4, orderId);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                JdbcUtils.release(connection, preparedStatement, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 查询所有订单信息
    public static void getAllOrders() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT order_id, product_id, order_date, order_price FROM yourorders";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("订单ID: " + orderId + ", 商品ID: " + productId + ", 下单日期: " + orderDate + ", 订单价格: " + orderPrice);
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

    // 根据订单ID查询订单信息
    public static void getOrderById(int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT product_id, order_date, order_price FROM yourorders WHERE order_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                double orderPrice = resultSet.getDouble("order_price");
                System.out.println("订单ID: " + orderId + ", 商品ID: " + productId + ", 下单日期: " + orderDate + ", 订单价格: " + orderPrice);
            } else {
                System.out.println("未找到ID为 " + orderId + " 的订单。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.release(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 数据验证：检查商品是否存在
    private static boolean isProductExists(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT product_id FROM myproducts WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                JdbcUtils.release(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
