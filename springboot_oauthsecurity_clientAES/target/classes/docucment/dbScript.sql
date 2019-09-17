CREATE SCHEMA jdbc_tokenstore;

use jdbc_tokenstore;


CREATE TABLE `sb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sb_user` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Full_Name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `Role_Id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `client_secret` varchar(255) NOT NULL,
  `web_server_redirect_uri` varchar(2048) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `resource_ids` varchar(1024) DEFAULT NULL,
  `authorized_grant_types` varchar(1024) DEFAULT NULL,
  `authorities` varchar(1024) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `sb_role` VALUES (1,'Standard User - no admin rights','STANDARD_USER'),(2,'Admin User - Has permission to perform admin tasks','ADMIN_USER');

INSERT INTO `sb_user` VALUES (1,'ak','$2a$10$mU8VnBk17B1Hecq9MouaBulwHPa03LIaOCZFBbwrUe8KHOpXBkImO','ak',1);
