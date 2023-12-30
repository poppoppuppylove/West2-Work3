package MyJdbc.OrderManagement.jdbc;

import java.sql.SQLException;
import java.util.Date;
@SuppressWarnings("all")
public class Test {

    public static void main(String[] args) throws SQLException {
        // 添加商品成功
        ProductDao.addProduct(8, "新商品2", 88);

        // 查询所有商品
        System.out.println("所有商品:");
        ProductDao.getAllProducts();

        // 添加商品失败
        ProductDao.addProduct(8, "新商品2", 88);

        // 按商品价格升序排序
        System.out.println("\n按商品价格升序排序:");
        SortDao.sortProductsByPriceAsc();

        // 创建订单
        OrderDao.createOrder(8, new Date(), 88);
        OrderDao.createOrder(2, new Date(), 6);
        OrderDao.createOrder(88, new Date(), 20);
        OrderDao.createOrder(3, new Date(), 20);

        // 查询所有订单
        System.out.println("\n所有订单:");
        OrderDao.getAllOrders();

        // 按订单时间降序排序
        System.out.println("\n按订单时间降序排序");
        SortDao.sortOrdersByDateDesc();

        // 按订单价格升序排序
        System.out.println("\n按订单价格升序排序:");
        SortDao.sortOrdersByPriceAsc();

        // 删除商品,若有订单还要删相关订单
        System.out.println("\n删除商品及相关订单:");
        ProductDao.deleteProductAndOrders(2);
        ProductDao.deleteProductAndOrders(8);
        ProductDao.getAllProducts();

        // 添加商品
        ProductDao.addProduct(2, "剪刀", 6);
        ProductDao.addProduct(5, "咖啡", 28);

        // 删除订单
        System.out.println("\n删除订单后还剩:");
        OrderDao.deleteOrder(8);
        OrderDao.getAllOrders();

        // 更新商品信息
        System.out.println("\n更新商品信息:");
        ProductDao.updateProduct(6, "管他什么", 999);
        ProductDao.getAllProducts();

        // 根据商品ID查询商品信息
        System.out.println("\n根据商品ID查询商品信息:");
        ProductDao.getProductById(4);
    }
}
