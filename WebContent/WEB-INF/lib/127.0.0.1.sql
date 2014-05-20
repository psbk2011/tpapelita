-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 19 Mei 2014 pada 21.14
-- Versi Server: 5.5.27
-- Versi PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `master_tpapelita`
--
CREATE DATABASE `master_tpapelita` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `master_tpapelita`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `administrator`
--

CREATE TABLE IF NOT EXISTS `administrator` (
  `admin_id` int(10) unsigned NOT NULL,
  `admin_name` varchar(45) DEFAULT NULL,
  `admin_email` varchar(45) DEFAULT NULL,
  `admin_phone` varchar(14) DEFAULT NULL,
  `admin_job` tinyint(1) DEFAULT NULL,
  `admin_username` varchar(18) DEFAULT NULL,
  `admin_pass` text,
  `admin_last_pass` text,
  `admin_status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `administrator`
--

INSERT INTO `administrator` (`admin_id`, `admin_name`, `admin_email`, `admin_phone`, `admin_job`, `admin_username`, `admin_pass`, `admin_last_pass`, `admin_status`) VALUES
(0, 'admin', 'mail@mail.com', '085711696261', 0, 'admin', 'admin', 'admin', 0),
(1, 'Subhan F.', '', '085711696261', 1, NULL, '085711696261', '085711696261', 0),
(2, 'Subhan F', '', '0921', 1, 'suguro', '0921', '0921', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `event_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `event_date_create` datetime DEFAULT NULL,
  `event_title` varchar(45) DEFAULT NULL,
  `event_time` datetime DEFAULT NULL,
  `event_text` text,
  `admin_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `investment`
--

CREATE TABLE IF NOT EXISTS `investment` (
  `inves_id` int(10) unsigned NOT NULL,
  `inves_date` datetime DEFAULT NULL,
  `inves_type` int(1) DEFAULT NULL,
  `inves_status` int(1) DEFAULT NULL,
  `inves_transfer` int(11) DEFAULT NULL,
  `inves_sender_name` varchar(45) DEFAULT NULL,
  `inves_bank_name` varchar(45) DEFAULT NULL,
  `inves_account_no` varchar(18) DEFAULT NULL,
  `investor_id` int(10) unsigned DEFAULT NULL,
  `admin_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`inves_id`),
  KEY `investor_id` (`investor_id`),
  KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `investment`
--

INSERT INTO `investment` (`inves_id`, `inves_date`, `inves_type`, `inves_status`, `inves_transfer`, `inves_sender_name`, `inves_bank_name`, `inves_account_no`, `investor_id`, `admin_id`) VALUES
(2014520001, '2014-05-20 02:03:03', NULL, 3, 100000, NULL, 'BNI', '11123023489900', 4, NULL),
(2014520003, '2014-05-20 02:03:47', NULL, 3, 100000, NULL, 'BCA', '125161366364', 1, NULL),
(2014520004, '2014-05-20 02:05:26', NULL, 3, 20000, NULL, 'NO BANK', '', 1, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `investor`
--

CREATE TABLE IF NOT EXISTS `investor` (
  `investor_id` int(10) unsigned NOT NULL,
  `investor_name` varchar(45) DEFAULT NULL,
  `investor_email` varchar(45) DEFAULT NULL,
  `investor_phone` varchar(14) DEFAULT NULL,
  `investor_status` tinyint(1) DEFAULT NULL,
  `investor_registration` datetime DEFAULT NULL,
  `investor_pass` text,
  `investor_last_pass` text,
  PRIMARY KEY (`investor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `investor`
--

INSERT INTO `investor` (`investor_id`, `investor_name`, `investor_email`, `investor_phone`, `investor_status`, `investor_registration`, `investor_pass`, `investor_last_pass`) VALUES
(1, 'Jaka', 'Jaka@gmail.com', '081234567235', 0, '2014-04-29 19:20:08', '081234567235', '081234567235'),
(2, 'Subhan Dukun', 'subhan@gmail.com', '09851975', 0, '2014-04-29 19:21:19', '09851975', '09851975'),
(3, 'Koala', '', '6575757', 0, '2014-05-12 21:06:16', '6575757', '6575757'),
(4, 'Subhan G', '', '085711696261', 0, '2014-05-19 23:38:26', '085711696261', '085711696261');

-- --------------------------------------------------------

--
-- Struktur dari tabel `outcome`
--

CREATE TABLE IF NOT EXISTS `outcome` (
  `outcome_id` int(10) unsigned NOT NULL,
  `outcome_date` datetime DEFAULT NULL,
  `admin_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`outcome_id`),
  KEY `outcome_id` (`outcome_id`),
  KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `outcome`
--

INSERT INTO `outcome` (`outcome_id`, `outcome_date`, `admin_id`) VALUES
(1, '2014-05-13 10:29:39', 0),
(2, '2014-05-14 10:36:23', 0),
(3, '2014-05-19 17:27:24', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `outcome_details`
--

CREATE TABLE IF NOT EXISTS `outcome_details` (
  `details_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `details_unit_name` varchar(50) DEFAULT NULL,
  `details_unit_price` int(11) DEFAULT NULL,
  `details_unit_qty` int(11) DEFAULT NULL,
  `details_info` text,
  `outcome_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`details_id`),
  KEY `outcome_id` (`outcome_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `report_publish`
--

CREATE TABLE IF NOT EXISTS `report_publish` (
  `report_id` int(10) unsigned NOT NULL,
  `report_date_create` datetime DEFAULT NULL,
  `report_date` date DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `support_center`
--

CREATE TABLE IF NOT EXISTS `support_center` (
  `support_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `support_date` datetime DEFAULT NULL,
  `support_name` varchar(35) DEFAULT NULL,
  `support_subject` varchar(20) DEFAULT NULL,
  `support_email` varchar(50) DEFAULT NULL,
  `support_message` text,
  `support_show` tinyint(1) DEFAULT NULL,
  `support_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`support_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data untuk tabel `support_center`
--

INSERT INTO `support_center` (`support_id`, `support_date`, `support_name`, `support_subject`, `support_email`, `support_message`, `support_show`, `support_status`) VALUES
(1, '2014-05-18 14:51:19', 'ss', 'sss', 'ss', 'ss', 0, 0);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `administrator` (`admin_id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `investment`
--
ALTER TABLE `investment`
  ADD CONSTRAINT `investment_ibfk_8` FOREIGN KEY (`investor_id`) REFERENCES `investor` (`investor_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `investment_ibfk_9` FOREIGN KEY (`admin_id`) REFERENCES `administrator` (`admin_id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `outcome`
--
ALTER TABLE `outcome`
  ADD CONSTRAINT `outcome_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `administrator` (`admin_id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `outcome_details`
--
ALTER TABLE `outcome_details`
  ADD CONSTRAINT `outcome_details_ibfk_2` FOREIGN KEY (`outcome_id`) REFERENCES `outcome` (`outcome_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
