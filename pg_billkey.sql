-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.4.0 - MySQL Community Server - GPL
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

-- 테이블 pg.pg_approve 구조 내보내기
CREATE TABLE IF NOT EXISTS `pg_approve` (
  `appr_idx` int NOT NULL AUTO_INCREMENT,
  `pay_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '결제 코드',
  `user_ci` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '사용자 CI',
  `appr_price` bigint NOT NULL DEFAULT '0' COMMENT '승인금액',
  `appr_status` int NOT NULL DEFAULT (0) COMMENT '승인결과',
  `appr_datetime` datetime NOT NULL COMMENT '승인완료일시',
  `created_at` datetime NOT NULL COMMENT '승인요청일시',
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`appr_idx`) USING BTREE,
  UNIQUE KEY `FK2_pay_code` (`pay_code`),
  KEY `FK1_userci` (`user_ci`),
  CONSTRAINT `FK1_userci` FOREIGN KEY (`user_ci`) REFERENCES `pg_user_card_token` (`user_ci`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 pg.pg_approve:~1 rows (대략적) 내보내기
INSERT INTO `pg_approve` (`appr_idx`, `pay_code`, `user_ci`, `appr_price`, `appr_status`, `appr_datetime`, `created_at`, `updated_at`) VALUES
	(2, 'paycode2', 'userci1', 5000, 1, '2024-06-01 20:18:22', '2024-06-01 20:18:23', '2024-06-01 20:18:28');

-- 테이블 pg.pg_card_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `pg_card_info` (
  `card_idx` int NOT NULL AUTO_INCREMENT,
  `user_ci` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '사용자 ci',
  `card_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '결제카드정보 hash',
  `expired` varchar(6) DEFAULT NULL,
  `crc` char(3) DEFAULT NULL,
  `billing_status` tinyint DEFAULT NULL COMMENT '빌링키 상태값 ( 0: 정상 1: 취소 2: 중지)',
  `ref_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ref_Id',
  `created_at` datetime DEFAULT NULL COMMENT '등록일',
  `updated_at` datetime DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`card_idx`),
  UNIQUE KEY `ref_id_unique` (`ref_id`),
  KEY `user_ci_idx` (`user_ci`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 pg.pg_card_info:~6 rows (대략적) 내보내기
INSERT INTO `pg_card_info` (`card_idx`, `user_ci`, `card_num`, `expired`, `crc`, `billing_status`, `ref_id`, `created_at`, `updated_at`) VALUES
	(1, 'userci1', '1111111111111111', '1234', '123', 0, 'card_ref1', '2024-06-01 02:52:36', '2024-06-01 02:52:37'),
	(3, 'userci2', '1111222233334444', '0230', '233', 0, 'card_ref2', '2024-06-01 03:29:27', NULL),
	(4, 'userci3', '1111222233334444', '0230', '233', 0, 'card_ref3', '2024-06-01 03:44:50', NULL),
	(5, 'userci4', '1111222233334444', '0230', '233', 0, 'card_ref4', '2024-06-01 18:15:40', NULL),
	(6, 'userci5', '1111222233334444', '0230', '233', 0, 'card_ref5', '2024-06-01 18:21:30', NULL),
	(7, 'userci6', '1111222233334444', '0230', '233', 0, 'card_ref6', '2024-06-01 18:23:36', NULL);

-- 테이블 pg.pg_payment 구조 내보내기
CREATE TABLE IF NOT EXISTS `pg_payment` (
  `pay_idx` int NOT NULL AUTO_INCREMENT,
  `pay_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '결제코드',
  `pay_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '결제수단',
  `card_ref` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '빌키(결제 hash)',
  `user_ci` varchar(100) NOT NULL DEFAULT '0' COMMENT '결제요청자',
  `pay_price` bigint NOT NULL DEFAULT '0' COMMENT '결제금액',
  `pay_result` tinyint NOT NULL DEFAULT '0' COMMENT '결제결과',
  `created_at` datetime NOT NULL COMMENT '결제일시',
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`pay_idx`),
  UNIQUE KEY `UNIQUE_KEY` (`pay_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 pg.pg_payment:~1 rows (대략적) 내보내기
INSERT INTO `pg_payment` (`pay_idx`, `pay_code`, `pay_type`, `card_ref`, `user_ci`, `pay_price`, `pay_result`, `created_at`, `updated_at`) VALUES
	(1, 'paycode1', '1', '121212', '12121', 50000, 1, '2024-06-01 19:11:48', '2024-06-01 19:11:49'),
	(2, 'paycode2', '1', 'card_ref2', 'userci1', 5000, 1, '2024-06-01 21:13:52', NULL),
	(3, 'paycode3', '1', 'card_ref2', 'userci1', 1, 1, '2024-06-02 00:36:40', NULL);

-- 테이블 pg.pg_refund 구조 내보내기
CREATE TABLE IF NOT EXISTS `pg_refund` (
  `refund_idx` int NOT NULL AUTO_INCREMENT,
  `appr_idx` int DEFAULT '0' COMMENT '승인 idx',
  `refund_price` bigint DEFAULT NULL COMMENT '취소 금액',
  `refund_status` tinyint DEFAULT NULL COMMENT '취소 상태',
  `created_at` datetime DEFAULT NULL COMMENT '취소 요청일',
  `updated_at` datetime DEFAULT NULL,
  KEY `pk_refund` (`refund_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 pg.pg_refund:~0 rows (대략적) 내보내기

-- 테이블 pg.pg_user_card_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `pg_user_card_token` (
  `token_idx` int NOT NULL AUTO_INCREMENT,
  `user_ci` varchar(50) DEFAULT NULL COMMENT 'user ci',
  `created_at` datetime DEFAULT NULL COMMENT '등록일시',
  `updated_at` datetime DEFAULT NULL COMMENT '수정일시',
  PRIMARY KEY (`token_idx`),
  UNIQUE KEY `unique_key` (`user_ci`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 카드 토큰';

-- 테이블 데이터 pg.pg_user_card_token:~0 rows (대략적) 내보내기
INSERT INTO `pg_user_card_token` (`token_idx`, `user_ci`, `created_at`, `updated_at`) VALUES
	(1, 'userci1', '2024-06-01 21:17:38', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
