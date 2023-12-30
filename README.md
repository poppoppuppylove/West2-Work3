# 三轮考核学习心得

## 一、学习内容感悟

本轮学习主要掌握了mysql的使用，学习了jdbc的使用
再也不敢速成了，学得好累好崩溃

## 二、任务的完成情况

###使用MySQL语言进行表的创建
-- 创建 myproducts 表
CREATE TABLE myproducts (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price DOUBLE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- 插入数据到 myproducts 表
INSERT INTO myproducts (product_id, product_name, product_price) 
VALUES 
    (1, 'twice演唱会门票', 2888),
    (2, '剪刀', 6),
    (3, '专辑', 100),
    (4, '包治百病药', 9999),
    (5, '咖啡', 28),
    (6, '可乐', 3);

-- 创建 yourorders 表
CREATE TABLE yourorders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    order_date DATETIME DEFAULT NULL,
    order_price DOUBLE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES myproducts(product_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- 插入数据到 yourorders 表
INSERT INTO yourorders (product_id, order_date, order_price) 
VALUES 
    (3, '1997-03-24 00:00:00', 100),
    (1, '1999-04-23 00:00:00', 2888);

###编写JDBC工具类处理数据库连接√
并且包含异常处理和资源释放

###执行增删查改操作√

###添加**事务管理**
#### 代码实现

1. 开启事务 `conn.setAutoCommit(false)`
2. 一组业务执行完毕，提交事务
3. 可以在 catch 语句中显示的定义回滚语句，但默认失败就会回滚
4. finally中恢复自动提交为true并释放资源

###解决**SQL注入问题**
PrepareStatement可以防止SQL注入，并且效率更高

###如果想要删除已经存在在订单中的商品，你要怎么处理？
在ProductDao中，删除商品的实现可以选择先判断商品是否现有订单，若没有的话则不执行删除订单的代码，而有的话则需要进行删除订单的代码，判断结束，然后再删除商品

###避免使用SELECT *
尽量避免了，选择自己指定所需的列

## 三、对以后的展望
虽然看得很快，但其实我觉得自己掌握的并不扎实，会补起来的。
然后希望第四轮可以稳当地学习完毕吧