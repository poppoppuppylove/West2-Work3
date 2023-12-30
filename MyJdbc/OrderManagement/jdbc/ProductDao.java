package MyJdbc.OrderManagement.jdbc;

import MyJdbc.OrderManagement.jdbc.Utils.JdbcUtils;

import java.sql.*;
@SuppressWarnings("all")
public class ProductDao {

    // 添加商品
    public static void addProduct(int productId, String productName, double productPrice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            // 设置自动提交为false以启动事务
            connection.setAutoCommit(false);

            // 数据验证：检查商品ID是否已存在
            if (isProductIdExists(connection, productId)) {
                System.out.println("商品ID " + productId + " 已存在。");
                // 回滚事务，因为商品ID已存在
                connection.rollback();
                return;
            }

            String sql = "INSERT INTO myproducts (product_id, product_name, product_price) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, productName);
            preparedStatement.setDouble(3, productPrice);
            preparedStatement.executeUpdate();

            // 提交事务，如果一切正常
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

    // 在事务中检查商品ID是否已存在
    private static boolean isProductIdExists(Connection connection, int productId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
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
                JdbcUtils.release(null, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 删除商品及相关订单
    public static void deleteProductAndOrders(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            // 设置自动提交为false以启动事务
            connection.setAutoCommit(false);

            // 判断是否存在相关订单
            if (hasOrdersForProduct(connection, productId)) {
                // 删除相关订单
                deleteOrdersByProductId(connection, productId);
            }

            // 删除商品
            String deleteProductSql = "DELETE FROM myproducts WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(deleteProductSql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

            // 提交事务，如果一切正常
            connection.commit();
            System.out.println("成功删除ID为 "+productId+" 的商品");
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

    // 删除相关订单
    private static void deleteOrdersByProductId(Connection connection, int productId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String deleteOrdersSql = "DELETE FROM yourorders WHERE product_id = ?";
        preparedStatement = connection.prepareStatement(deleteOrdersSql);
        preparedStatement.setInt(1, productId);
        preparedStatement.executeUpdate();

        // 释放资源
        try {
            JdbcUtils.release(null, preparedStatement, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 判断是否存在相关订单
    private static boolean hasOrdersForProduct(Connection connection, int productId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String countOrdersSql = "SELECT * FROM yourorders WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(countOrdersSql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int orderCount = resultSet.getInt(1);
                return orderCount > 0;
            }

            return false;
        } finally {
            try {
                JdbcUtils.release(null, preparedStatement, resultSet);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    // 修改商品信息
    public static void updateProduct(int productId, String newProductName, double newProductPrice) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            // 设置自动提交为false以启动事务
            connection.setAutoCommit(false);

            String sql = "UPDATE myproducts SET product_name = ?, product_price = ? WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newProductName);
            preparedStatement.setDouble(2, newProductPrice);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();

            // 提交事务，如果一切正常
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


    // 查询所有商品信息
    public static void getAllProducts() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT product_id, product_name, product_price FROM myproducts";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                System.out.println("商品ID: " + productId + ", 商品名称: " + productName + ", 商品价格: " + productPrice);
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

    // 根据商品ID查询商品信息
    public static void getProductById(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT product_name, product_price FROM myproducts WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                System.out.println("商品ID: " + productId + ", 商品名称: " + productName + ", 商品价格: " + productPrice);
            } else {
                System.out.println("未找到ID为 " + productId + " 的产品");
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
}
