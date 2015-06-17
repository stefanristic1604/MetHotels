CREATE DATABASE  IF NOT EXISTS `methotels` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `methotels`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: methotels
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnik` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `facebook_id` varchar(100) DEFAULT NULL,
  `korisnicko_ime` varchar(256) NOT NULL,
  `lozinka` varchar(256) NOT NULL,
  `email` varchar(1024) NOT NULL,
  `rola` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,NULL,'stefan.ristic.1604','202cb962ac59075b964b07152d234b70','stefan.ristic.1604@metropolitan.ac.rs','Admin'),(2,NULL,'admin','202cb962ac59075b964b07152d234b70','admin@methotels.com','Admin'),(3,NULL,'sluzbenik','202cb962ac59075b964b07152d234b70','sluzbenik@methotels.com','Sluzbenik'),(5,NULL,'rile','202cb962ac59075b964b07152d234b70','rile@gmail.com','Korisnik');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacija` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `soba_id` int(11) DEFAULT NULL,
  `ime` varchar(256) DEFAULT NULL,
  `prezime` varchar(368) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `broj_soba` int(11) DEFAULT NULL,
  `dan_prijave` date DEFAULT NULL,
  `dan_odjave` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (4,5,'Stefan','Ristic','stefilix933@gmail.com',3,'2015-04-04','2015-04-11'),(5,NULL,'Ivan','Ristic','ivan@gmail.com',5,'2015-05-06','2015-05-12'),(6,NULL,'Valentina','Ristic','valentina@gmail.com',3,'2015-05-12','2015-05-07'),(7,NULL,'Ana','Anic','ana@gmail.com',3,'2015-05-06','2015-05-21'),(8,NULL,'Pera','Peric','pera@gmail.com',1,'2015-05-23','2015-05-19'),(9,NULL,'Mika','Mikic','mika@gmail.com',2,'2015-05-18','2015-05-25'),(10,NULL,'Laza','Lazic','laza@gmail.com',3,'2015-05-11','2015-05-14'),(11,NULL,'Jovan','Jovanovic','jovan@gmail.com',4,'2015-05-06','2015-05-15'),(12,NULL,'Mila','Milic','mila@gmail.com',1,'2015-05-04','2015-05-05'),(13,NULL,'Ivan','Ivanovic','ivan@gmail.com',5,'2015-05-07','2015-05-21'),(14,NULL,'Nikola','Nikolic','nikola@gmail.com',2,'2015-05-14','2015-05-21'),(16,NULL,'Tina','Tinic','tina@gmail.com',2,'2015-05-23','2015-05-30');
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soba`
--

DROP TABLE IF EXISTS `soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soba` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(64) DEFAULT NULL,
  `sprat` varchar(64) DEFAULT NULL,
  `opis` varchar(2048) DEFAULT NULL,
  `refSlika` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soba`
--

LOCK TABLES `soba` WRITE;
/*!40000 ALTER TABLE `soba` DISABLE KEYS */;
INSERT INTO `soba` VALUES (7,'Deluxe','2','Opis','Slika'),(22,'Classic','3','Opis','Slika');
/*!40000 ALTER TABLE `soba` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-17 15:49:15
