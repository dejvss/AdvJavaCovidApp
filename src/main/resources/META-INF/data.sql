CREATE DATABASE IF NOT EXISTS contact_tracing;
USE contact_tracing;

DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE `tbl_address` (
  `addressCode` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_line_one` varchar(255) NOT NULL,
  `address_line_two` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  `postal_code` varchar(255) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `userEntity_userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressCode`),
  KEY `FK9o5bwu02rkwbkpb8v0y1kwvld` (`userEntity_userId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

INSERT INTO `tbl_address` (`addressCode`,`address_line_one`,`address_line_two`,`city`,`country_name`,`postal_code`,`street_name`,`userEntity_userId`) VALUES
 (1,'Komuna','Komuna e Parisit','Tirana','Albania','1250','STREETNAME','LeOiEWGnOKBbM7M5HQyR');

DROP TABLE IF EXISTS `tbl_quarantine_info`;
CREATE TABLE `tbl_quarantine_info` (
  `quarId` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_date` datetime DEFAULT NULL,
  `sickness_status` varchar(255) NOT NULL,
  `userAddress_addressCode` bigint(20) DEFAULT NULL,
  `userEntity_userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`quarId`),
  KEY `FKivgwg7g133401ufnbi1sf7taa` (`userAddress_addressCode`),
  KEY `FKibo1emel7y3n9c266o452rufg` (`userEntity_userId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

INSERT INTO `tbl_quarantine_info` (`quarId`,`from_date`,`sickness_status`,`userAddress_addressCode`,`userEntity_userId`) VALUES
 (1,'2020-06-15 00:00:00','infected_since',1,'LeOiEWGnOKBbM7M5HQyR');

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `userId` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `date_executed` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `sex` varchar(255) NOT NULL,
  `user_full_name` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_status` varchar(10) NOT NULL,
  `roleEntity_roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UK6jr81l5qqpxjp72fgi23ubqc9` (`user_name`),
  KEY `FK26xxhy3fql87t28ekwnmmikaf` (`roleEntity_roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `tbl_user` (`userId`,`age`,`date_executed`,`email`,`password`,`phone_number`,`sex`,`user_full_name`,`user_name`,`user_status`,`roleEntity_roleId`) VALUES
 ('LeOiEWGnOKBbM7M5HQyR',30,'2020-06-15 09:48:46','test@gmail.com','1000:42bbbe8882ec3aa56161d49e0355b030e3b3c9db903b1aa8:815c882691fd5b666f0a577ee09603e4bf9573aed3e0186c','+8801817126345','Male','Test User','test-test','Active',1);

DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_executed` datetime NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_status` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UKk0ijfnxd35ym2mj3fniha0spw` (`role_name`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

INSERT INTO `tbl_user_role` (`roleId`,`date_executed`,`role_name`,`role_status`) VALUES
 (1,'2020-06-15 00:00:00','USER','Active');

DROP TABLE IF EXISTS `tbl_user_session`;
CREATE TABLE `tbl_user_session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `logoff_time` datetime DEFAULT NULL,
  `logon_time` datetime DEFAULT NULL,
  `userEntity_userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `FKtmccguo9f2aual0mcjhm5a8a0` (`userEntity_userId`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT INTO `tbl_user_session` (`session_id`,`logoff_time`,`logon_time`,`userEntity_userId`) VALUES
 (1,'2020-06-15 09:49:17','2020-06-15 09:48:50','LeOiEWGnOKBbM7M5HQyR'),
 (2,NULL,'2020-06-15 09:49:20','LeOiEWGnOKBbM7M5HQyR');
