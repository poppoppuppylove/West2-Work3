# 三轮考核学习心得

## 一、学习内容感悟

本轮学习主要掌握了mysql的使用，学习了jdbc的使用


## 二、任务的完成情况
只完成了基本任务

### 添加**事务管理**
#### 代码实现

1. 开启事务 `conn.setAutoCommit(false)`
2. 一组业务执行完毕，提交事务
3. 可以在 catch 语句中显示的定义回滚语句，但默认失败就会回滚
4. finally中恢复自动提交为true并释放资源

### 解决**SQL注入问题**
PrepareStatement可以防止SQL注入，并且效率更高

### 如果想要删除已经存在在订单中的商品，你要怎么处理？
在ProductDao中，删除商品的实现可以选择先判断商品是否现有订单，若没有的话则不执行删除订单的代码，而有的话则需要进行删除订单的代码，判断结束，然后再删除商品

### 避免使用SELECT *
尽量避免了，选择自己指定所需的列

## 三、对以后的展望
其实掌握的很不扎实，会补起来的。
希望第四轮可以稳当地学习吧
