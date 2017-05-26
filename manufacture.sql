-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 06, 2016 at 04:20 
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `manufacture`
--

-- --------------------------------------------------------

--
-- Table structure for table `component`
--

CREATE TABLE IF NOT EXISTS `component` (
  `COMPONENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPONENT_NAME` varchar(50) NOT NULL,
  `PART_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT '0',
  `IMG_URL` text,
  PRIMARY KEY (`COMPONENT_ID`),
  KEY `PART_ID` (`PART_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `component`
--

INSERT INTO `component` (`COMPONENT_ID`, `COMPONENT_NAME`, `PART_ID`, `QUANTITY`, `IMG_URL`) VALUES
(1, 'Motherboard', 1, 5, 'image/component/Virtua Tennis 4.png'),
(5, 'RAM !!', 1, 5, 'image/component/Lost Planet.png'),
(6, 'Processor', 1, 10, 'image/component/Digimon All Star Rumble.png'),
(7, 'Velg', 5, 11, 'image/component/Digimon All Star Rumble.png');

-- --------------------------------------------------------

--
-- Table structure for table `cost_production`
--

CREATE TABLE IF NOT EXISTS `cost_production` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCTION_YEAR` varchar(4) NOT NULL,
  `AMOUNT` int(11) NOT NULL,
  `USED_AMOUNT` int(11) DEFAULT '0',
  PRIMARY KEY (`PRODUCT_ID`,`PRODUCTION_YEAR`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `cost_production`
--

INSERT INTO `cost_production` (`PRODUCT_ID`, `PRODUCTION_YEAR`, `AMOUNT`, `USED_AMOUNT`) VALUES
(1, '2015', 500000, 100100),
(1, '2016', 2500000, 1500000),
(4, '2015', 5000000, 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `order_component`
--

CREATE TABLE IF NOT EXISTS `order_component` (
  `ORDER_COMPONENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPONENT_ID` int(11) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `REQUIRED_DATE` date DEFAULT NULL,
  `SCHEDULED_BUY_DATE` date DEFAULT '1945-01-01',
  `SCHEDULED_BUY_USERNAME` varchar(50) DEFAULT NULL,
  `STATUS` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `COST` double NOT NULL,
  PRIMARY KEY (`ORDER_COMPONENT_ID`),
  KEY `COMPONENT_ID` (`COMPONENT_ID`),
  KEY `USERNAME` (`USERNAME`),
  KEY `SCHEDULED_BUY_USERNAME` (`SCHEDULED_BUY_USERNAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `order_component`
--

INSERT INTO `order_component` (`ORDER_COMPONENT_ID`, `COMPONENT_ID`, `USERNAME`, `ORDER_DATE`, `REQUIRED_DATE`, `SCHEDULED_BUY_DATE`, `SCHEDULED_BUY_USERNAME`, `STATUS`, `QUANTITY`, `COST`) VALUES
(1, 1, 'mfahry', '2015-05-01', '2015-05-10', '2015-05-15', 'mfahry', 1, 10, 200000),
(3, 6, 'mfahry', '2015-05-02', '2012-12-12', '2015-05-07', 'akipmaulana', 1, 5, 100),
(5, 5, 'mfahry', '2015-05-02', '2012-12-11', '1945-01-01', 'mfahry', 0, 2, 0),
(6, 5, 'mfahry', '2015-05-02', '2013-02-05', '1945-01-01', 'mfahry', 0, 13, 0),
(7, 1, 'mfahry', '2015-05-05', '2012-12-11', '1945-01-01', 'mfahry', 0, 1, 0),
(8, 1, 'mfahry', '2015-05-05', '2012-12-11', '1945-01-01', 'jundi', 1, 1, 10000),
(9, 6, 'mfahry', '2015-05-06', '2015-05-12', '1945-01-01', 'mfahry', 0, 5, 0),
(10, 7, 'mfahry', '2015-05-11', '2015-12-05', '2015-12-06', 'akipmaulana', 1, 5, 2000000);

--
-- Triggers `order_component`
--
DROP TRIGGER IF EXISTS `AFT_UPDATE_ORDER_COMPONENTE`;
DELIMITER //
CREATE TRIGGER `AFT_UPDATE_ORDER_COMPONENTE` AFTER UPDATE ON `order_component`
 FOR EACH ROW BEGIN
	DECLARE vproductid INT;
	IF OLD.STATUS = 0 AND NEW.STATUS = 1 THEN
		UPDATE component SET QUANTITY = QUANTITY + NEW.QUANTITY WHERE COMPONENT_ID = NEW.COMPONENT_ID;

		SELECT PRODUCT_ID INTO vproductid FROM COMPONENT 
		JOIN PART USING(PART_ID)
		JOIN PRODUCT USING(PRODUCT_ID)
		WHERE COMPONENT_ID = NEW.COMPONENT_ID LIMIT 1;

		UPDATE COST_PRODUCTION SET USED_AMOUNT = USED_AMOUNT + NEW.COST WHERE PRODUCT_ID = vproductid AND YEAR(CURDATE());
	END IF;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `BEF_INSERT_ORDER`;
DELIMITER //
CREATE TRIGGER `BEF_INSERT_ORDER` BEFORE INSERT ON `order_component`
 FOR EACH ROW BEGIN
	SET NEW.ORDER_DATE = CURDATE();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `part`
--

CREATE TABLE IF NOT EXISTS `part` (
  `PART_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PART_NAME` varchar(50) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT '0',
  `IMG_URL` text,
  PRIMARY KEY (`PART_ID`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `part`
--

INSERT INTO `part` (`PART_ID`, `PART_NAME`, `PRODUCT_ID`, `QUANTITY`, `IMG_URL`) VALUES
(1, 'CPU', 1, 5, NULL),
(2, 'Keyboard Good II', 1, 5, 'image/part/Kinect Sport.png'),
(3, 'Layar Samsung', 1, 5, 'image/part/Dragon Age Inquistion.png'),
(4, 'Mouse', 1, 5, 'image/part/Lost Planet.png'),
(5, 'Roda', 4, 3, 'image/part/Virtua Tennis 4.png');

--
-- Triggers `part`
--
DROP TRIGGER IF EXISTS `AFT_UPDATE_PART`;
DELIMITER //
CREATE TRIGGER `AFT_UPDATE_PART` AFTER UPDATE ON `part`
 FOR EACH ROW BEGIN
	IF OLD.QUANTITY < NEW.QUANTITY THEN
		UPDATE component SET QUANTITY = QUANTITY - 1 WHERE PART_ID = NEW.PART_ID;
	END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(50) NOT NULL,
  `QUANTITY` int(11) DEFAULT '0',
  `IMG_URL` text,
  PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PRODUCT_ID`, `PRODUCT_NAME`, `QUANTITY`, `IMG_URL`) VALUES
(1, 'Komputer II', 5, 'image/product/Digimon All Star Rumble.png'),
(3, 'Sepeda II', 3, 'image/product/Virtua Tennis 4.png'),
(4, 'Mobil', 2, 'image/product/Shadow of Mordor.png');

--
-- Triggers `product`
--
DROP TRIGGER IF EXISTS `AFT_UPDATE_PRODUCT`;
DELIMITER //
CREATE TRIGGER `AFT_UPDATE_PRODUCT` AFTER UPDATE ON `product`
 FOR EACH ROW BEGIN
	IF OLD.QUANTITY < NEW.QUANTITY THEN
		UPDATE PART SET QUANTITY = QUANTITY - 1 WHERE PRODUCT_ID = NEW.PRODUCT_ID;
	END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` text NOT NULL,
  `EMPLOYEE_ID` varchar(20) NOT NULL,
  `EMPLOYEE_NAME` varchar(100) NOT NULL,
  `STATUS` enum('0','1') DEFAULT '1',
  `USER_GROUP` enum('FINANCE','PRODUCTION','LOGISTIC','SUPERADMIN') DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USERNAME`, `PASSWORD`, `EMPLOYEE_ID`, `EMPLOYEE_NAME`, `STATUS`, `USER_GROUP`) VALUES
('akipmaulana', 'akipmaulana', 'EMP-3', 'Akip Maulana', '1', 'LOGISTIC'),
('jundi', 'jundi', 'EMP-2', 'Abdurrahman Jundullah', '1', 'FINANCE'),
('mfahry', 'mfahry', 'EMP-1', 'Muhammad Fahry', '1', 'PRODUCTION');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `component`
--
ALTER TABLE `component`
  ADD CONSTRAINT `component_ibfk_1` FOREIGN KEY (`PART_ID`) REFERENCES `part` (`PART_ID`);

--
-- Constraints for table `cost_production`
--
ALTER TABLE `cost_production`
  ADD CONSTRAINT `cost_production_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`);

--
-- Constraints for table `order_component`
--
ALTER TABLE `order_component`
  ADD CONSTRAINT `order_component_ibfk_1` FOREIGN KEY (`COMPONENT_ID`) REFERENCES `component` (`COMPONENT_ID`),
  ADD CONSTRAINT `order_component_ibfk_2` FOREIGN KEY (`USERNAME`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `order_component_ibfk_3` FOREIGN KEY (`SCHEDULED_BUY_USERNAME`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `part`
--
ALTER TABLE `part`
  ADD CONSTRAINT `part_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
