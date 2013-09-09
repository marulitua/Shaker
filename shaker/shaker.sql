-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 09, 2013 at 11:53 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `shaker`
--

-- --------------------------------------------------------

--
-- Table structure for table `records`
--

CREATE TABLE IF NOT EXISTS `records` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delta_x` float NOT NULL,
  `delta_y` float NOT NULL,
  `delta_Z` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=202 ;

--
-- Dumping data for table `records`
--

INSERT INTO `records` (`id`, `device_id`, `timestamp`, `delta_x`, `delta_y`, `delta_Z`) VALUES
(190, 'b735abbff055c6e5', '2013-09-09 09:40:20', 0, 2.03028, 5.59286),
(191, 'b735abbff055c6e5', '2013-09-09 09:40:20', 4.32872, 4.32872, 6.74207),
(192, 'b735abbff055c6e5', '2013-09-09 09:40:24', 0, 0, 5.59286),
(193, 'b735abbff055c6e5', '2013-09-09 09:40:24', 0, 2.22182, 8.88728),
(194, 'b735abbff055c6e5', '2013-09-09 09:40:25', 0, 0, 6.359),
(195, 'b735abbff055c6e5', '2013-09-09 09:40:27', 0, 0, 6.28239),
(196, 'b735abbff055c6e5', '2013-09-09 09:40:52', 0, 0, 9.99819),
(197, 'b735abbff055c6e5', '2013-09-09 09:40:53', 0, 0, 12.4498),
(198, 'b735abbff055c6e5', '2013-09-09 09:40:53', 0, 0, 6.97192),
(199, 'b735abbff055c6e5', '2013-09-09 09:40:53', 0, 0, 5.63116),
(200, 'b735abbff055c6e5', '2013-09-09 09:40:53', 0, 4.59687, 15.093),
(201, 'b735abbff055c6e5', '2013-09-09 09:40:53', 0, 3.29442, 12.0668);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
