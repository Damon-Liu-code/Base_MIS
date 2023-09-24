/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.42-log : Database - db_mis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_mis` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `db_mis`;

/*Table structure for table `t_customer_service` */

DROP TABLE IF EXISTS `t_customer_service`;

CREATE TABLE `t_customer_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serveType` varchar(30) DEFAULT NULL COMMENT '服务类型',
  `overview` varchar(500) DEFAULT NULL COMMENT '服务内容',
  `customer` varchar(30) DEFAULT NULL COMMENT '客户',
  `state` varchar(20) DEFAULT NULL COMMENT '状态',
  `servicerequest` varchar(500) DEFAULT NULL COMMENT '服务状态',
  `createPeople` varchar(100) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `assigner` varchar(100) DEFAULT NULL COMMENT '分配人',
  `assignTime` datetime DEFAULT NULL COMMENT '分配时间',
  `serviceProce` varchar(500) DEFAULT NULL COMMENT '服务反馈',
  `serviceProcePeople` varchar(20) DEFAULT NULL COMMENT '服务反馈人',
  `serviceProceTime` datetime DEFAULT NULL COMMENT '服务反馈时间',
  `serviceProceResult` varchar(500) DEFAULT NULL COMMENT '服务反馈结果',
  `myd` varchar(50) DEFAULT NULL COMMENT '服务星级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_customer_service` */

insert  into `t_customer_service`(`id`,`serveType`,`overview`,`customer`,`state`,`servicerequest`,`createPeople`,`createTime`,`assigner`,`assignTime`,`serviceProce`,`serviceProcePeople`,`serviceProceTime`,`serviceProceResult`,`myd`) values (1,'咨询','咨询下Think pad采购价格','苏宁集团','已归档','厂家直销3500/台','Jack','2019-04-10 11:42:32','小红','2015-06-03 00:00:00','s','Jack','2015-06-04 00:00:00','OK','☆☆☆☆'),(2,'咨询','咨询价格','必点集团','已归档','厂家直销4500/台','Jack','2019-04-10 08:46:23',NULL,NULL,'sss','Jack','2015-06-04 00:00:00','OK','☆☆☆'),(3,'咨询','咨询分期付款方式','俺来也集团','已归档','定金30%+尾款70%','Jack','2019-04-10 18:59:53','小红','2015-06-03 00:00:00','sds','Jack','2015-06-04 00:00:00','OK','☆☆☆☆'),(6,'咨询','咨询定金尾款支付方式','苏宁集团','已归档','70%订单确认后一周内支付','Jack','2019-04-10 22:55:55','小红','2015-06-04 00:00:00','ds','Jack','2015-06-04 00:00:00','OK','☆☆☆'),(7,'咨询','咨询产品使用','苏宁集团','已归档','详见产品规格说明书','Jack','2019-04-10 03:55:48','小明','2015-06-04 00:00:00','ss','Jack','2015-06-04 00:00:00','OK','☆☆'),(8,'建议','建议客服人员耐心接待','新浪','已归档','好的，感谢','Jack','2019-04-10 00:00:00','小张','2015-06-04 00:00:00','111','Jack','2015-06-04 00:00:00','ok','☆☆☆☆☆'),(9,'投诉','投诉产品质量问题','新浪','已归档','包邮寄回，包修包换','Jack','2019-04-10 13:55:56','小明','2015-06-04 00:00:00','333','Jack','2015-06-04 00:00:00','OK','☆☆☆☆☆'),(10,'建议','建议增加新的功能','京东集团','已处理','好的，谢谢','Jack','2019-04-10 00:00:00','小明','2016-03-13 00:00:00','打回去重写报告','光智','2019-05-09 00:00:00','',''),(11,'建议','建议对产品进行维护','谷歌','已归档','好的，谢谢','Jack','2019-04-10 11:11:23','小明','2015-06-11 00:00:00','fds','Jack','2015-06-11 00:00:00','d','☆☆☆'),(12,'建议','建议能够送货到家','百度','已归档','好的，谢谢','Jack','2019-04-10 14:53:11','小红','2015-06-10 00:00:00','fda','Jack','2015-06-10 00:00:00','good','☆☆☆☆☆'),(13,'咨询','咨询pad价格','谷歌','已归档','厂家直销4000/台','Jack','2019-04-10 15:45:23','小红','2015-06-11 00:00:00','。。。\r\n1，2\r\n，3。。。','Jack','2015-06-11 00:00:00','OK','☆☆☆☆☆'),(14,'咨询','咨询售后问题','苏宁集团','已分配','包邮寄回，包修包换','Jack','2019-04-10 15:52:22','小明','2019-04-11 00:00:00',NULL,NULL,NULL,NULL,NULL),(15,'咨询','咨询think pad xx型号价格','苏宁集团','已分配','厂家直销4000/台','Jack','2019-04-10 00:00:00','小红','2019-05-09 00:00:00',NULL,NULL,NULL,NULL,NULL),(16,'投诉','投诉质量问题，要求更换','俺来也集团','已归档','包邮寄回，包修包换','Jack','2019-04-10 15:18:52','小明','2015-08-27 00:00:00','进行了....客户交流。。。\r\n','Jack','2015-08-31 00:00:00','OK','☆☆☆☆☆'),(17,'咨询','咨询产品售后及退换货','谷歌','新创建','包邮寄回，包修包换','Jack','2019-04-10 12:55:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'投诉','投诉虚假宣传','谷歌','新创建','投诉不实，予以驳回','Jack','2019-04-10 18:14:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `page` varchar(50) DEFAULT NULL,
  `parentid` int(11) DEFAULT '0',
  `show_in_menu` tinyint(1) DEFAULT '0',
  `iconCSS` varchar(30) DEFAULT NULL,
  `value` int(11) DEFAULT '0',
  `create_datetime` datetime DEFAULT NULL,
  `display` int(11) DEFAULT '0',
  `colsetauthority` varchar(1) DEFAULT NULL COMMENT '1:set; 0: not set;',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`name`,`page`,`parentid`,`show_in_menu`,`iconCSS`,`value`,`create_datetime`,`display`,`colsetauthority`) values (1,'用户模块',NULL,0,1,NULL,0,NULL,1,NULL),(2,'系统设置模块',NULL,0,1,'icon-application-view-tile',9999,NULL,1,NULL),(4,'菜单设置','/pages/menu_info.html',2,1,NULL,8000,NULL,1,NULL),(5,'权限设置','/pages/role_info.html',2,1,NULL,8000,NULL,1,NULL),(6,'用户信息管理','/pages/user_info.html',1,1,NULL,9999,NULL,1,NULL),(7,'修改密码','/pages/change_pwd.html',1,1,NULL,0,NULL,1,NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `roleNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`roleName`,`roleNumber`) values (1,'超级管理员',9999),(2,'系统管理员',8000),(4,'公司主管',6000),(5,'公司员工',3000),(6,'普通用户',500),(7,'高级用户',1000),(8,'公司经理',5000);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`trueName`,`email`,`phone`,`roleName`) values (1,'admin','123','Admin','826444535@qq.com','15705181299','超级管理员'),(4,'test003','123','小红','test003@126.com','15886888898','公司主管'),(5,'test004','123','小张','test004@126.com','15499498848','公司员工'),(6,'test005','123','小王','test005@126.com','15449484984','公司员工'),(7,'test006','123','小刘','test006@126.com','15358984849','普通用户'),(9,'test007','123','小周','test007@126.com','15849948844','公司经理'),(10,'test002','123','小刘','test002@126.com','19147961234','公司主管');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
