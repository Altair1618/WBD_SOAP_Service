DROP DATABASE IF EXISTS `wbd_soap`;
CREATE DATABASE `wbd_soap`;
USE `wbd_soap`;

DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(256) NOT NULL,
  `IP` varchar(16) NOT NULL,
  `endpoint` varchar(256) NOT NULL,
  `requested_at` timestamp NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`)
);
