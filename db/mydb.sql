-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.3.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- mydb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `mydb`;

-- 테이블 mydb.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `board_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(30) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FK_board_user` (`user_id`),
  CONSTRAINT `FK_board_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 mydb.board:~6 rows (대략적) 내보내기
INSERT IGNORE INTO `board` (`board_id`, `user_id`, `title`, `content`) VALUES
	(2, 2, 'title 2', 'content 2'),
	(10, 3, 'title 10', 'content 10'),
	(12, 3, 'new title', 'new content'),
	(14, 3, 'title 14', 'content 14'),
	(16, 4, 'title 15', 'content 15'),
	(17, 6, '안녕하세요.', '반갑습니다.');

-- 테이블 mydb.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 mydb.role:~2 rows (대략적) 내보내기
INSERT IGNORE INTO `role` (`role_id`, `name`) VALUES
	(2, 'ROLE_USER'),
	(3, 'ROLE_ADMIN');

-- 테이블 mydb.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `enabled` bit(1) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 mydb.user:~5 rows (대략적) 내보내기
INSERT IGNORE INTO `user` (`enabled`, `user_id`, `password`, `username`) VALUES
	(b'1', 2, '$2a$10$vBfGCucxKN2M.pEQ7YeX8uVtTMqhnqrH5kdDUlWpuhfh2GR8NV1EW', 'user2'),
	(b'1', 3, '$2a$10$O19AM.1yUv5Ruzhj6upy7.2PA1MspwbK65POZba20JDRuXM2UT132', 'user3'),
	(b'1', 4, '$2a$10$ncOD9/XXarj4jxTSOPjOaOV/VZ6AgP4uUQRw6K4y54UBimDIeYoxO', 'user4'),
	(b'1', 5, '$2a$10$3gIiuwNqOwHQz.pUgiw0SeA9Gv8K73Ik2SM.2c/O5Aw4XNkzujfSe', 'user5'),
	(b'1', 6, '$2a$10$Dd/y23BthpHzAwLE.k9VyuJeG4JkyWPALbSxA7nnQJi/vFVNIcmf2', 'user6');

-- 테이블 mydb.user_role 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 mydb.user_role:~5 rows (대략적) 내보내기
INSERT IGNORE INTO `user_role` (`role_id`, `user_id`, `user_role_id`) VALUES
	(2, 2, 2),
	(3, 3, 3),
	(2, 4, 4),
	(2, 5, 5),
	(2, 6, 6);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
