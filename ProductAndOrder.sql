/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.35 : Database - jdbcstudy1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jdbcstudy1` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `jdbcstudy1`;

/*Table structure for table `myproducts` */

DROP TABLE IF EXISTS `myproducts`;

CREATE TABLE `myproducts` (
  `product_id` int NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` double NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `myproducts` */

insert  into `myproducts`(`product_id`,`product_name`,`product_price`) values 
(1, 'twice演唱会门票', 2888),
(2, '剪刀', 6),
(3, '专辑', 100),
(4, '包治百病药', 9999),
(5, '咖啡', 28),
(6, '可乐', 3);

/*Table structure for table `yourorders` */

DROP TABLE IF EXISTS `yourorders`;

CREATE TABLE `yourorders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_price` double NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `yourorders_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `myproducts` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;

/*Data for the table `yourorders` */

insert  into `yourorders`(`order_id`,`product_id`,`order_date`,`order_price`) values 
(5,3,'1997-03-24 00:00:00',100),
(6,1,'1999-04-23 00:00:00',2888);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
