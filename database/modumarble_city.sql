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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `city_No` int(11) NOT NULL,
  `city_Name` varchar(45) NOT NULL,
  `city_GPrice` int(11) NOT NULL,
  `city_HPrice` int(11) NOT NULL,
  `city_LPrice` int(11) NOT NULL,
  `city_GX` int(11) NOT NULL,
  `city_GY` int(11) NOT NULL,
  `city_HX` int(11) NOT NULL,
  `city_HY` int(11) NOT NULL,
  `city_LX` int(11) NOT NULL,
  `city_LY` int(11) NOT NULL,
  `city_GB` int(11) NOT NULL DEFAULT '0',
  `city_HB` int(11) NOT NULL DEFAULT '0',
  `city_LB` int(11) NOT NULL DEFAULT '0',
  `city_Owner` varchar(45) DEFAULT '소유주 없음',
  `city_ImgPath` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`city_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (0,'출발',0,0,0,775,775,0,0,0,0,0,0,0,'소유주없음','null\r'),(1,'타이페이',50000,100000,150000,685,780,680,745,705,745,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/taipei'),(2,'황금열쇠1',0,0,0,615,780,0,0,0,0,0,0,0,'소유주없음','null\r'),(3,'베이징',80000,130000,160000,545,780,540,745,570,745,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/beijing'),(4,'마닐라',80000,100000,120000,480,780,470,745,500,745,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/manila'),(5,'제주도',500000,500000,500000,405,780,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/jeju'),(6,'싱가폴',100000,120000,160000,335,780,335,745,360,745,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/singapore'),(7,'황금열쇠2',0,0,0,279,780,0,0,0,0,0,0,0,'소유주없음','null\r'),(8,'카이로',100000,130000,150000,200,780,195,745,220,745,1,0,0,'player1','C:/Users/odae/workspace/ModuMable/img/cairo'),(9,'이스탄불',100000,150000,180000,133,780,125,745,153,745,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/istanbul'),(10,'무인도',0,0,0,35,775,0,0,0,0,0,0,0,'소유주없음','null\r'),(11,'아테네',140000,280000,450000,15,680,100,670,100,705,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/athens'),(12,'황금열쇠3',0,0,0,15,615,0,0,0,0,0,0,0,'소유주없음','null\r'),(13,'코펜하겐',160000,220000,280000,15,545,100,535,100,565,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/copenhagen'),(14,'스톡홀름',160000,200000,270000,15,475,100,470,100,500,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/stockholm'),(15,'콩코드여객기',300000,300000,300000,15,405,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/koncode'),(16,'퀘백',180000,230000,250000,15,340,100,335,100,365,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/quebec'),(17,'황금열쇠4',0,0,0,15,270,0,0,0,0,0,0,0,'소유주없음','null\r'),(18,'베를린',180000,230000,280000,15,205,100,195,100,1250,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/berlin'),(19,'오타와',200000,250000,300000,15,135,100,130,100,160,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/ottawa'),(20,'사회복지기금출금',0,0,0,35,35,0,0,0,0,0,0,0,'소유주없음','null\r'),(21,'부에노스아이레스',220000,300000,360000,133,20,125,100,155,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/buenosaires'),(22,'황금열쇠5',0,0,0,200,20,0,0,0,0,0,0,0,'소유주없음','null\r'),(23,'상파울로',240000,330000,380000,279,20,260,100,295,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/saopaulo'),(24,'시드니',240000,350000,440000,335,20,335,100,360,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/sydney'),(25,'부산',700000,700000,700000,405,20,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/pusan'),(26,'하와이',260000,360000,500000,480,20,470,100,500,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/hawaii'),(27,'리스본',260000,340000,380000,545,20,140,100,570,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/lisbon'),(28,'퀸엘리자베스',500000,500000,500000,615,20,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/queen'),(29,'마드리드',280000,380000,540000,685,20,675,100,705,100,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/madrid'),(30,'우주정거장',0,0,0,775,35,0,0,0,0,0,0,0,'소유주없음','null\r'),(31,'도쿄',300000,400000,670000,785,135,745,130,745,160,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/tokyo'),(32,'아둔의창',650000,650000,650000,785,205,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/adun'),(33,'파리',320000,400000,690000,785,270,745,265,745,290,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/paris'),(34,'로마',320000,400000,700000,785,340,745,330,745,360,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/roma'),(35,'황금열쇠6',0,0,0,785,405,0,0,0,0,0,0,0,'소유주없음','null\r'),(36,'런던',350000,430000,830000,785,475,745,470,745,500,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/london'),(37,'뉴욕',350000,440000,850000,785,545,745,535,745,565,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/newyork'),(38,'사회복지기금입금',0,0,0,785,615,0,0,0,0,0,0,0,'소유주없음','null\r'),(39,'서울',1200000,1200000,1200000,785,680,0,0,0,0,0,0,0,'소유주없음','C:/Users/odae/workspace/ModuMable/img/seoul');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
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
