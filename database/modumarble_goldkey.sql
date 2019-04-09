CREATE DATABASE  IF NOT EXISTS `modumarble` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `modumarble`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: modumarble
-- ------------------------------------------------------
-- Server version	5.5.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `goldkey`
--

DROP TABLE IF EXISTS `goldkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goldkey` (
  `goldkey_no` int(11) NOT NULL,
  `goldkey_name` varchar(45) DEFAULT NULL,
  `goldkey_explain` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`goldkey_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goldkey`
--

LOCK TABLES `goldkey` WRITE;
/*!40000 ALTER TABLE `goldkey` DISABLE KEYS */;
INSERT INTO `goldkey` VALUES (1,'사회복지 대금','생활이 어려워 10만원을 지급해드립니다.'),(2,'여행 지원금','여행 지원금 10만원을 지급해드립니다.'),(3,'생일 축하','생일 선물로 30만원을 지급해드립니다.'),(4,'자동차 대회 1등','1등 상금으로 100만원을 지급해드립니다.'),(5,'프로젝트 1등','1등 상금으로 100만원을 지급해드립니다.'),(6,'회식비 지원','회식비로 50만원을 기부하셔야합니다.'),(7,'복권 당첨','1등 당첨금으로 500만원을 지급해드립니다.'),(8,'과속 운전','벌금으로 50만원을 내셔야합니다.'),(9,'음주 운전','벌금으로 100만원을 내셔야합니다.'),(10,'쓰레기 무단 투기','벌금으로 10만원을 내셔야합니다.'),(11,'무단 횡단','벌금으로 30만원을 내셔야합니다.'),(12,'면허증 미 소지','벌금으로 100만원을 내셔야합니다.'),(13,'서울올림픽개최','서울로 이동하세요. 서울 통행료를 내야합니다.'),(14,'우주여행 티켓','우주 선착장으로 이동하세요.'),(15,'무인도 여행','무인도로 이동하세요.'),(16,'앞으로 3칸 이동','앞으로 3칸 이동하세요.'),(17,'앞으로 2칸 이동','앞으로 2칸 이동하세요.'),(18,'사회복지금 지원','사회복지금으로 50만원을 내셔야합니다.'),(19,'뒤로 2칸 이동','뒤로 2칸 이동하세요.'),(20,'뒤로 3칸 이동','뒤로 3칸 이동하세요.');
/*!40000 ALTER TABLE `goldkey` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-09  8:59:07
