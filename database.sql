/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `android`;
CREATE DATABASE IF NOT EXISTS `android` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `android`;

DROP TABLE IF EXISTS `class_info`;
CREATE TABLE IF NOT EXISTS `class_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `classes` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `class_info` DISABLE KEYS */;
INSERT INTO `class_info` (`id`, `classes`, `grade`, `speciality`) VALUES
	(1, '3班', '19', '计算机科学');
/*!40000 ALTER TABLE `class_info` ENABLE KEYS */;

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

DROP TABLE IF EXISTS `hostel`;
CREATE TABLE IF NOT EXISTS `hostel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `last_modify` datetime(6) DEFAULT NULL,
  `remain` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` (`id`, `count`, `created_date`, `grade`, `last_modify`, `remain`) VALUES
	(1, 2, '2021-09-30 10:58:10.000000', 80, '2021-09-30 14:51:04.541000', NULL),
	(2, 0, '2021-09-30 12:21:49.000000', 12, '2021-09-30 15:05:29.548000', NULL),
	(3, 4, '2021-09-30 12:29:02.000000', 23, '2021-09-30 14:51:31.917000', NULL),
	(6, 0, '2021-09-30 14:52:04.324000', 0, NULL, NULL);
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;

DROP TABLE IF EXISTS `late_return_table`;
CREATE TABLE IF NOT EXISTS `late_return_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `hostel` bigint(20) DEFAULT NULL,
  `last_modify` datetime(6) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `late_return_table` DISABLE KEYS */;
INSERT INTO `late_return_table` (`id`, `created_date`, `hostel`, `last_modify`, `student_id`) VALUES
	(1, '2021-09-30 12:32:05.403000', 2, '2021-09-30 12:32:11.863000', 1);
/*!40000 ALTER TABLE `late_return_table` ENABLE KEYS */;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `admin` int(11) DEFAULT NULL,
  `class_info` bigint(20) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `hostel` bigint(20) DEFAULT NULL,
  `last_modify` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_number` (`student_number`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `student_number`, `username`, `password`, `admin`, `class_info`, `created_date`, `hostel`, `last_modify`, `status`) VALUES
	(1, '201821324354', 'admin', 'rPsC0ak19PEWo1A9q+zj', 1, 1, '2021-09-30 11:12:12.720000', 1, NULL, '已入住'),
	(2, '3123', '张三1232', '6exCA3cT/1RNWaXUNtNg', 0, 1, '2021-09-30 11:12:38.328000', 2, '2021-09-30 14:47:29.169000', '已入住'),
	(3, '123', '测试123', 'lAaRB3062e7GGn5EQf4A', 0, 1, '2021-09-30 11:12:53.931000', 2, '2021-09-30 13:53:47.638000', '已入住'),
	(4, '123321', '测试改宿舍', 'iDQAexV9quYgnPOrU3QH', 0, 1, '2021-09-30 11:13:23.875000', 2, '2021-09-30 13:52:58.373000', '已入住'),
	(6, '201710233214', '张三', 'cIVfqj81KNQd5L5icQGn', 0, 1, '2021-09-30 14:25:35.832000', 2, NULL, '已入住'),
	(7, '201783214356', 'dsaf', 'rPsC0ak19PEWo1A9q+zj', 0, 1, '2021-09-30 14:44:52.705000', 2, NULL, '已入住');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

DROP TABLE IF EXISTS `visit_table`;
CREATE TABLE IF NOT EXISTS `visit_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accessed_hostel` bigint(20) DEFAULT NULL,
  `accessed_student` bigint(20) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modify` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

/*!40000 ALTER TABLE `visit_table` DISABLE KEYS */;
INSERT INTO `visit_table` (`id`, `accessed_hostel`, `accessed_student`, `created_date`, `last_modify`, `phone`) VALUES
	(1, 2, 1, '2021-09-30 12:32:57.276000', '2021-09-30 14:54:50.659000', '11111111111');
/*!40000 ALTER TABLE `visit_table` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
