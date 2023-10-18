/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.27-MariaDB : Database - utakmice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`utakmice` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `utakmice`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `adminID` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(255) DEFAULT NULL,
  `sifra` varchar(255) DEFAULT NULL,
  `imeAdministratora` varchar(255) DEFAULT NULL,
  `prezimeAdministratora` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`adminID`,`korisnickoIme`,`sifra`,`imeAdministratora`,`prezimeAdministratora`) values 
(1,'mihajlo','mihajlo','Mihajlo','Stojadinov');

/*Table structure for table `igrac` */

DROP TABLE IF EXISTS `igrac`;

CREATE TABLE `igrac` (
  `igracID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `pozicija` varchar(255) DEFAULT NULL,
  `brojDresa` int(11) DEFAULT NULL,
  `klubID` int(11) DEFAULT NULL,
  PRIMARY KEY (`igracID`),
  KEY `fk_igrac_klub` (`klubID`),
  CONSTRAINT `fk_igrac_klub` FOREIGN KEY (`klubID`) REFERENCES `klub` (`klubID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `igrac` */

insert  into `igrac`(`igracID`,`ime`,`prezime`,`pozicija`,`brojDresa`,`klubID`) values 
(3,'Lionel','Messi','Pozicija 3',12,3),
(4,'Mohamed','Salah','Pozicija 4',23,4),
(5,'Cavi','Iniesta','Pozicija 5',45,5),
(6,'Kristijano','Ronaldo','Pozicija 6',67,6),
(7,'Aleksandar','Mitrovic','Pozicija 7',64,6),
(8,'Nikola','Zigic','Pozicija 8',89,7),
(9,'Dejvid','Bekam','Pozicija 9',90,8),
(10,'Diego','Maradona','Pozicija 10',72,9);

/*Table structure for table `klub` */

DROP TABLE IF EXISTS `klub`;

CREATE TABLE `klub` (
  `klubID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  `grad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`klubID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `klub` */

insert  into `klub`(`klubID`,`naziv`,`grad`) values 
(3,'Barcelona','Barcelona'),
(4,'Real Madrid','Madrid'),
(5,'Liverpool','Liverpool'),
(6,'Mancester United','Mancester'),
(7,'Arsenal','London'),
(8,'Roma','Rim'),
(9,'Napoli','Napulj');

/*Table structure for table `rasporedutakmica` */

DROP TABLE IF EXISTS `rasporedutakmica`;

CREATE TABLE `rasporedutakmica` (
  `rasporedID` int(11) NOT NULL AUTO_INCREMENT,
  `sezona` varchar(255) DEFAULT NULL,
  `adminID` int(11) DEFAULT NULL,
  PRIMARY KEY (`rasporedID`),
  KEY `fk_rasporedutakmica_administrator` (`adminID`),
  CONSTRAINT `fk_rasporedutakmice_administrator` FOREIGN KEY (`adminID`) REFERENCES `administrator` (`adminID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `rasporedutakmica` */

insert  into `rasporedutakmica`(`rasporedID`,`sezona`,`adminID`) values 
(1,'Sezona 1',1),
(2,'Sezona 2',1);

/*Table structure for table `statistikakluba` */

DROP TABLE IF EXISTS `statistikakluba`;

CREATE TABLE `statistikakluba` (
  `statistikaKlubaID` int(11) NOT NULL AUTO_INCREMENT,
  `klubID` int(11) NOT NULL,
  `brojBodova` int(11) DEFAULT NULL,
  `brojDatihGolova` int(11) DEFAULT NULL,
  `brojPrimljenihGolova` int(11) DEFAULT NULL,
  PRIMARY KEY (`statistikaKlubaID`,`klubID`),
  KEY `fk_statistikakluba_klub` (`klubID`),
  CONSTRAINT `fk_statistikakluba_klub` FOREIGN KEY (`klubID`) REFERENCES `klub` (`klubID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `statistikakluba` */

insert  into `statistikakluba`(`statistikaKlubaID`,`klubID`,`brojBodova`,`brojDatihGolova`,`brojPrimljenihGolova`) values 
(8,3,2,2,0),
(9,3,3,3,1),
(10,4,1,2,3),
(11,5,3,2,2),
(12,6,1,2,3),
(13,7,3,2,0),
(14,8,3,2,1),
(15,9,2,1,1),
(16,9,3,3,3);

/*Table structure for table `utakmica` */

DROP TABLE IF EXISTS `utakmica`;

CREATE TABLE `utakmica` (
  `utakmicaID` int(11) NOT NULL AUTO_INCREMENT,
  `rezultat` varchar(255) DEFAULT NULL,
  `datum` datetime DEFAULT NULL,
  `stadion` varchar(255) DEFAULT NULL,
  `kolo` int(11) DEFAULT NULL,
  `rasporedID` int(11) DEFAULT NULL,
  `domacinID` int(11) DEFAULT NULL,
  `gostID` int(11) DEFAULT NULL,
  PRIMARY KEY (`utakmicaID`),
  KEY `fk_utakmica_rasporedutakmica` (`rasporedID`),
  KEY `fk_utakmica_klub_gost` (`gostID`),
  KEY `fk_utakmica_klub_domacin` (`domacinID`),
  CONSTRAINT `fk_utakmica_klub_domacin` FOREIGN KEY (`domacinID`) REFERENCES `klub` (`klubID`) ON UPDATE CASCADE,
  CONSTRAINT `fk_utakmica_klub_gost` FOREIGN KEY (`gostID`) REFERENCES `klub` (`klubID`) ON UPDATE CASCADE,
  CONSTRAINT `fk_utakmica_rasporedutakmica` FOREIGN KEY (`rasporedID`) REFERENCES `rasporedutakmica` (`rasporedID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `utakmica` */

insert  into `utakmica`(`utakmicaID`,`rezultat`,`datum`,`stadion`,`kolo`,`rasporedID`,`domacinID`,`gostID`) values 
(1,'1:0','2023-09-12 14:45:00','Stadion 1',2,1,3,4),
(2,'2:2','2023-05-14 14:00:00','Stadion 3',2,1,5,6),
(3,'1:0','2022-01-12 14:44:00','Stadion 1',1,2,3,5),
(4,'2:0','2022-04-14 16:00:00','Stadion 2',2,2,3,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
