# 1. 数据库

## 1.2 什么是数据库

数据库（Database，简称DB）是长期存放在计算机内、有组织、可共享的大量数据的集合，可以看作是一个数据的仓库。其作用是保存并能安全管理数据，包括数据的增加、删除、修改和查询等操作，以减少数据冗余。

## 1.3 数据库分类

### 1.3.1 关系型数据库（SQL）

关系型数据库通过表和表之间、行和列之间的关系进行数据的存储。常见的关系型数据库包括MySQL、Oracle、SQL Server、SQLite、DB2等。它们适用于以表格形式存储数据，如学员信息表、考勤表等。

### 1.3.2 非关系型数据库（NoSQL）

非关系型数据库通常指数据以对象的形式存储在数据库中，对象之间的关系通过每个对象自身的属性来决定。常见的非关系型数据库包括Redis、MongoDB等。它们适用于以对象形式存储和检索数据。

### 1.3.3 DBMS（数据库管理系统）

数据库管理系统（DBMS）是数据库管理软件，用于科学组织和存储数据，以高效地获取和维护数据。MySQL是一个典型的数据库管理系统。

## 1.4 MySQL简介

### 1.4.1 概念

MySQL是一种流行的开源、免费的关系型数据库管理系统。

### 1.4.2 历史

MySQL由瑞典MySQL AB公司开发，目前属于Oracle旗下产品。

### 1.4.3 特点

- 免费、开源数据库
- 小巧、功能齐全
- 使用便捷
- 可运行于Windows或Linux操作系统
- 适用于中小型甚至大型网站应用

MySQL具有广泛的应用领域，并为开发人员提供了强大而灵活的数据库管理工具。

## 1.6 SQLyog

SQLyog是一款可手动操作和管理MySQL数据库的软件工具。

### 特点

- 简洁
- 易用
- 图形化

### 使用SQLyog管理工具完成的操作

1. 连接本地MySQL数据库
2. 新建School数据库

### 问题与发现

在使用过程中，发现MySQL在5.5.3之后增加了utf8mb4的编码，用于兼容四字节的unicode，支持任意文字，包括emoji编码。因此，可以在实际使用中给需要支持emoji的库或表设置utf8mb4。例如，评论需要支持emoji可以采用utf8mb4。

另外，发现从MySQL 8.0.17版本开始，TINYINT、SMALLINT、MEDIUMINT、INT以及BIGINT类型的显示宽度将失效。因此，在使用这些字段类型时，需要注意显示宽度可能无效。

在历史记录中，可以查看相对应的数据库操作语句。

##1.7 命令行连接
mysql -u root -p123456 --连接数据库
alter user root@localhost identified by '123456'; --修改用户密码
flush privileges; --刷新权限

------------------------------------
--所有的语句都用;结尾
show databases;--查看所有的数据库
use school; --use 数据库名 切换数据库

show tables;--查看所有的表
desc student;--查看名为student表信息

create database westos; --创建一个名为westos的数据库 
exit;--退出链接

-- 单行注释
/*
多行注释
*/

**数据库xxx语言 **

DDL 定义

DML 操作

DQL 查询

DCL 控制
#2.操作数据库
##2.1 操作数据库

###1.创建数据库

CREATE DATABASE IF NOT EXISTS westos

###2.删除数据库

DROP DATABASE IF EXISTS westos

###3.使用数据库

USE `school` --加单引号会变成字段 特殊的字符一眼加这个符号

###4.查看数据库

SHOW DATABASES --查看所有的数据库

##2.2 数据库的列（数据）类型
###数值

tinyint 十分小的数据 1个字节
smallint 较小的数据 2个字节
mediumint 中等大小的数据 3个字节
int 标准的整数 4个字节 常用的
bigint 较大的数据 8个字节
float 浮点数 4个字节
double 浮点数 8个字节
decimal 字符串形式的浮点数 金融计算的时候 一般用decimal

###字符串

char 字符串固定大小 0~255
varchar 可变字符串大小 0~65535 对应String
tinytest 微型文本 2^8-1
test 文本串 2^16 -1 保存大文本

###时间日期

data YYYY-MM-DD，日期格式

time HH:mm:ss
datetime YYYY-MM-DD HH:mm:ss 最常用的时间格式
timestamp 时间戳 1970.1.1到现在的毫秒数 较为常用
year 年份标识

###null

没有值，未知
注意，不要使用NULL进行运算，结果为NULL

##2.3、数据库的字段属性（重点）
###Unsigned：

无符号的整数
声明了该列不能为负数

###zerofill

0填充的
不足的位数，使用0来填充， int（3）， 5— 005

###自增：

通常理解为自增，自动在上一条记录的基础上+1（默认）
通常用来设计唯一的逐渐~ index，必须是证书类型
可以自定义设计主键自增的起始值和步长

###非空null not null

假设设置为not null，如果不给它赋值，就会报错！
null 如果不填写值，默认就是null！

###默认：

设置默认的值
sex，默认值为男，如果不指定该列的值，则会有默认的值！

==编辑到这里开始准备期末，mysql的学习转向速成==
# 分页查询
从第二条开始查，查三条：
```sql
SELECT id, name, url, alexa FROM websites LIMIT 2, 3;
```
# distinct去重
查询不重复的国家：
```sql
SELECT DISTINCT country FROM websites;
```
# WHERE语句
运算符> < >= <= <> != = is null is not null like in

在sql语句中 null值和任何东西比较 都为false，包括null
使用运算符进行条件查询：
```sql
SELECT * FROM websites WHERE sal > 0 AND sal < 2000;
```
# NULL值
与NULL值进行比较的查询：
```sql
SELECT * FROM websites WHERE sal IS NOT NULL;
```
# 逻辑条件：AND, OR
使用AND和OR进行复杂条件查询：
```sql
SELECT * FROM websites WHERE sal <= 0 AND sal >= 2000;
SELECT * FROM websites WHERE sal BETWEEN 2000 AND 0; /* 效果同上 */
SELECT * FROM websites WHERE sal < 5 OR sal IS NULL;
```
# 排序：ORDER BY
对查询结果进行排序：
```sql
SELECT * FROM websites ORDER BY sal ASC, alexa DESC; /* 先按sal升序，再按alexa降序 */
```
# LIKE通配符
使用LIKE进行模糊查询：
```sql
SELECT * FROM websites WHERE name LIKE '%O%';
SELECT * FROM websites WHERE name LIKE '_O%'; /* 匹配一个字符 */
```
# IN匹配多个条件
使用IN匹配多个条件：
```sql
SELECT * FROM websites WHERE country IN ('USA', '鸟国', 'CN');
```
# 别名
为表指定别名：
```sql
SELECT tt.name '网站名字' FROM websites tt; /* tt是表的别名 */
```
# 分组查询：GROUP BY
对结果进行分组并计算平均值：
```sql
SELECT AVG(sal) '平均值', country FROM websites GROUP BY country;
```
## HAVING子句
使用HAVING子句进行分组后的条件筛选：
```sql
SELECT AVG(sal) '平均值', country FROM websites GROUP BY country HAVING AVG(sal) > 1200;
```
-- 不能用where，只能用having;

# 子查询
将查询结果作为表使用

# 连接查询
## 过时写法
```sql
SELECT name, a.count, a.date FROM websites w, access_log a WHERE w.id = a.site_id;
```
**注释：** 这种写法可能会产生笛卡尔积，即每个网站都会与访问日志中的每条记录进行匹配，不加where的话会随意乱连。
## 左外连接
```sql
SELECT * FROM websites w LEFT OUTER JOIN access_log a ON w.id = a.site_id;
```
**注释：** 左外连接会返回左表（websites）的所有记录，即使右表（access_log）中没有匹配的记录。
## 全连接（使用UNION）
```sql
SELECT * FROM websites w RIGHT JOIN access_log a ON w.id = a.site_id
UNION
SELECT * FROM websites w LEFT OUTER JOIN access_log a ON w.id = a.site_id;
```
**注释：** 使用UNION实现全连接，但这种写法不常用，因为它会返回左外连接和右外连接的所有记录，可能会导致重复的行。
# IFNULL函数
使用IFNULL函数处理NULL值：
```sql
SELECT name, IFNULL(count, 0), IFNULL(a.date, '无日期') FROM websites w LEFT OUTER JOIN access_log a ON w.id = a.site_id;
```
==回到狂神mysql==
# 9.2 三大范式
在数据库设计中，规范化是一种重要的概念，它旨在减少数据冗余和改善数据完整性。不规范的数据设计可能会导致多种问题，包括信息重复、更新异常、插入异常、无法正确表示信息以及删除异常，这些都可能导致有效信息的丢失。
## 第一范式 (1st NF)
第一范式的目标是确保每列的原子性，即每列都包含不可再分的最小数据单元。如果每列都是这样的单元，则表满足第一范式。
## 第二范式 (2nd NF)
第二范式建立在第一范式的基础上。它要求表中的所有非主键列都必须完全依赖于主键，而不是仅依赖于主键的一部分。如果一个表满足第二范式，那么它也必然满足第一范式。
## 第三范式 (3rd NF)
如果一个关系满足第二范式，并且除了主键以外的其他列都不传递依赖于主键列，则该关系满足第三范式。第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。
## 规范化与性能的关系
在规范化数据库的设计中，通常建议关联查询的表不得超过三张表。然而，为了满足某些商业目标，数据库性能有时比规范化更重要。在数据规范化的同时，要综合考虑数据库的性能。例如，通过在给定的表中添加额外的字段，可以减少搜索信息所需的时间；通过在给定的表中插入计算列，可以方便查询。
请注意，规范化的过程可能会增加查询的复杂性和性能成本，因此在实际应用中，需要在规范化和性能之间找到平衡点。

#JDBC
# 10.3第一个JDBC程序

```java

package com.kuang.lesson01;

import java.sql.*;// 我的第一个JDBC程序

public class JdbcFirstDemo {

    public static void main(String[] args) throws 

ClassNotFoundException, SQLException {

        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");  // 固定写法，加载驱动
        //2.用户信息和 url
        // useUnicode=true&characterEncoding=utf8&useSSL=true
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username ="root";
        String password = "123456";
        //3.连接成功，数据库对象  Connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //4.执行 SQL 的对象
        Statement statement = connection.createStatement();
        //5.执行 SQL 的对象去执打 SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birth=" + resultSet.getObject("birthday"));
            System.out.println("==================================================");
        }
        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

# 10.4 statement对象
Jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。
- `executeUpdate` 方法用于向数据库发送增、删、改的SQL语句，执行完后，将会返回一个整数（即增删改语句导致了数据库几行数据发生了变化）。
- `executeQuery` 方法用于向数据库发送查询语句，返回代表查询结果的 `ResultSet` 对象。
## CRUD操作 - Create
使用 `executeUpdate(String sql)` 方法完成数据添加操作，示例操作：
```java
Statement statement = connection.createStatement();
String sql = "insert into user(...) values(...)";
int num = statement.executeUpdate(sql);
if(num>0){
    System.out.println("插入成功");
}
```
## CRUD操作 - Delete
使用 `executeUpdate(String sql)` 方法完成数据删除操作，示例操作：
```java
Statement statement = connection.createStatement();
String sql = "delete from user where id =1";
int num = statement.executeUpdate(sql);
if(num>0){
    System.out.println("删除成功");
}
```
## CRUD操作 - Read
使用 `executeUpdate(String sql)` 方法完成数据查询操作，示例操作：
```java
Statement statement = connection.createStatement();
String sql = "select * from  user where id =1";
ResultSet rs= statement.executeQuery(sql);
if(rs.next()){
    System.out.println("");
}
```
## 代码实现
1. 提取工具类
```java
package com.kuang.lesson02.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            // 1.驱动只用加载一次
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 2.获取连接
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }
    // 3.释放资源
    public static void release(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
```
2. 编写增删改的方法，`executeUpdate`
```java
package com.kuang.lesson02.utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.kuang.lesson02.utils.JdbcUtils.*;
public class TestInnsert {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 获取连接
            st = conn.createStatement(); // 获取SQL执行对象
            String sql = "INSERT INTO users(id,`NAME`,`PASSWORD`,`email`,`birthday`)" +
                    "VALUES(5,'sanjin','123456','233223@qq.com','2020-01-01')";
            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```
3. 查询 `executeQuery`
```java
package com.kuang.lesson02.utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static com.kuang.lesson02.utils.JdbcUtils.*;
public class TestInnsert {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 获取连接
            st = conn.createStatement(); // 获取SQL执行对象
            String sql = "select * from users";
            rs = st.executeQuery(sql); // 查询完毕返回结果集
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
       }
    }
}
```

# SQL 注入问题
SQL 存在漏洞，会被攻击导致数据泄露。SQL 会被拼接 `or`。
```java
package com.thb.study2;
import com.thb.study2.Utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.thb.study2.Utils.JdbcUtils.getConnection;
public class SQL {
    public static void main(String[] args) {
        // SQL 注入
        login("' or '1=1", "' or '1=1");
    }
    public static void login(String name, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 获取连接
            st = conn.createStatement(); // 获取 SQL 执行对象
            String sql = "select * from users where `NAME`='" + name + "' AND `PASSWORD`='" + password + "'" ;
            rs = st.executeQuery(sql); // 查询完毕返回结果集
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
                System.out.println("==========================================");
            }
            JdbcUtils.release(conn, st, rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```
## 10.5 PreparedStatement 对象
`PreparedStatement` 可以防止 SQL 注入，效率更高。
### 更新
```java
package com.thb.study2;
import com.thb.study2.Utils.JdbcUtils;
import java.sql.*;
import java.util.Date;
public class TestInsert02 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO users(`id`,`NAME`,`PASSWORD`,`email`,`birthday`) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 6);
            ps.setString(2, "无言");
            ps.setString(3, "999");
            ps.setString(4, "th@hhh.com");
            ps.setDate(5, new Date(new java.util.Date().getTime()));
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功o(*////▽////*)q");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
    }
}
```
### 查询
```java
package com.thb.study2;
import com.thb.study2.Utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.thb.study2.Utils.JdbcUtils.getConnection;
public class TestQuary03 {
    public static void main(String[] args) {
        login("苏苏", "222");
    }
    public static void login(String name, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 获取连接
            String sql = "SELECT * FROM users WHERE `NAME`=? AND `PASSWORD`=?";
            st = conn.prepareStatement(sql); // 获取 SQL 执行对象
            st.setString(1, name);
            st.setString(2, password);
            rs = st.executeQuery(); // 查询完毕返回结果集
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
                System.out.println("==========================================");
            }
            JdbcUtils.release(conn, st, rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```
##防止SQL注入
```
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.*;

public class SQL注入 {

    public static void main(String[] args) {
        //正常登录
        login("lisi","123456");
        login("'' or 1=1","'' or 1=1");
    }

    // 登录业务
    public static void login(String username, String password){

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();  // 获收数据库连接
            // Preparedstatement 的 SQL注入的本质,把传递进米的参数当做字符
            //假设其中在在转义字符,比如说·会被直接转义
            String sql = "select * from users where `NAME`=? and `PASSWORD`=?";

            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);

            rs = st.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }

    }

}
```
# 10.7 事务
要么都成功，要么都失败

## ACID原则

- 原子性：要么全部完成，要么都不完成
- 一致性：总数不变
- 隔离性：多个进程互不干扰
- 持久性：一旦提交不可逆，持久化到数据库了

## 隔离性的问题

- 脏读：事务读取了另一个没有提交的事务
- 不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变
- 虚读(幻读)：在一个事务内，读取到了别人插入的数据，导致前后读出来结果不一致

## 代码实现

1. 开启事务 `conn.setAutoCommit(false)`
2. 一组业务执行完毕，提交事务
3. 可以在 catch 语句中显示的定义回滚语句，但默认失败就会回滚

# 10.8 数据库连接池

数据库连接 — 执行完毕 — 释放

连接 — 释放十分浪费系统资源

池化技术：准备一些预先的资源，过来就连接预先准备好的

------- 开门 – 业务员：等待 - 服务 - 关门

最小连接数：10

最大连接数：15

等待超时：100ms

## 编写连接池，实现一个接口 Data Source

## 开源数据源实现

- DBCP
- C3PO
- Druid：阿里巴巴

## 结论

无论使用什么数据源，本质还是一样的， Data Source接口不会变，方法就不会变
