/*
SQLyog Ultimate v9.02 
MySQL - 5.0.24-community-nt : Database - tutorial_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tutorial_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tutorial_db`;

/*Table structure for table `answersheet_table` */

DROP TABLE IF EXISTS `answersheet_table`;

CREATE TABLE `answersheet_table` (
  `id` int(20) NOT NULL auto_increment,
  `question_id` int(20) default NULL,
  `sub_id` int(30) default NULL,
  `answer` varchar(40) default NULL,
  `user_id` int(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `answersheet_table` */

insert  into `answersheet_table`(`id`,`question_id`,`sub_id`,`answer`,`user_id`) values (35,8,14,'option_a',8),(36,9,14,'option_a',8),(37,10,14,'option_a',8),(38,11,14,'option_a',8),(39,12,14,'option_a',8),(40,8,14,'option_a',8),(41,9,14,'option_a',8),(42,10,14,'option_a',8),(43,11,14,'option_a',8),(44,12,14,'option_a',8),(45,13,5,'option_a',8),(46,14,5,'option_a',8),(47,15,5,'option_b',8),(48,16,5,'option_c',8),(49,17,5,'option_a',8),(50,18,5,'option_c',8),(51,13,5,'option_a',14),(52,14,5,'option_a',14),(53,15,5,'option_a',14),(54,16,5,'option_a',14),(55,17,5,'option_a',14),(56,17,5,'option_a',14),(57,19,1,'option_a',14),(58,20,1,'option_a',14),(59,21,1,'option_a',14),(60,19,1,'option_a',14),(61,20,1,'option_a',14),(62,21,1,'option_a',14),(63,21,1,'option_a',14),(64,8,14,'option_d',14),(65,9,14,'option_d',14),(66,10,14,'option_d',14),(67,11,14,'option_d',14),(68,8,14,'option_a',14),(69,9,14,'option_a',14),(70,10,14,'option_a',14),(71,11,14,'option_a',14),(72,8,14,'option_a',14),(73,9,14,'option_a',14),(74,10,14,'option_a',14),(75,11,14,'option_a',14),(76,8,14,'option_a',14),(77,9,14,'option_a',14),(78,10,14,'option_a',14),(79,11,14,'option_a',14),(80,11,14,'option_a',14);

/*Table structure for table `college_table` */

DROP TABLE IF EXISTS `college_table`;

CREATE TABLE `college_table` (
  `clg_id` int(11) NOT NULL auto_increment,
  `clg_name` varchar(45) default NULL,
  `clg_uni` varchar(45) default NULL,
  `clg_address` varchar(45) default NULL,
  `clg_fee` varchar(45) default NULL,
  `clg_branch` varchar(45) default NULL,
  `clg_contact` varchar(45) default NULL,
  `clg_degree` varchar(45) default NULL,
  `clg_document` varchar(45) default NULL,
  `clg_course` varchar(45) default NULL,
  `clg_cutoff` varchar(45) default NULL,
  PRIMARY KEY  (`clg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `college_table` */

insert  into `college_table`(`clg_id`,`clg_name`,`clg_uni`,`clg_address`,`clg_fee`,`clg_branch`,`clg_contact`,`clg_degree`,`clg_document`,`clg_course`,`clg_cutoff`) values (1,'SIMS','RGPV','indore','500000','EC','0000000000','BE','total 5 ','someThing','12'),(2,'SIA','RGPV','indore','12345','CS','0000000000','BCA','7','Something','40'),(3,'GIST','RGPV','indore','300000','IT','0000000000','BSC','8','something','60'),(4,'NIST','NIST','indore','500000','EE','0000000000','BE','4','something','80');

/*Table structure for table `login_table` */

DROP TABLE IF EXISTS `login_table`;

CREATE TABLE `login_table` (
  `login_id` int(11) NOT NULL,
  `user_name` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `role` varchar(45) default NULL,
  `result_date` varchar(45) default NULL,
  PRIMARY KEY  (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `login_table` */

insert  into `login_table`(`login_id`,`user_name`,`password`,`role`,`result_date`) values (1,'admin','123','admin',NULL),(2,'rahul','321','operator',NULL),(3,'nirmal','123','student',NULL),(4,'zdds','12345678','operator',NULL),(5,'martand','123','student',NULL),(6,'av_1',NULL,'student',NULL),(7,'av1','123','student',NULL),(8,'gopaldutt39','123','student',NULL),(9,'gg','s33adNXNNO','student',NULL),(10,'op','I2X3NTuYiv','student',NULL),(11,'nm','vun5FTixDK','student',NULL),(12,'uh','TUSXnew8Rs','student',NULL),(13,'vb11','ZrBtCrVeIG','student',NULL),(14,'bnbn','123','student',NULL),(15,'oi','oJozdZpG8B','student',NULL);

/*Table structure for table `profile_table` */

DROP TABLE IF EXISTS `profile_table`;

CREATE TABLE `profile_table` (
  `profile_id` int(11) NOT NULL,
  `first_name` varchar(45) default NULL,
  `last_name` varchar(45) default NULL,
  `contact_no` varchar(45) default NULL,
  `address` varchar(45) default NULL,
  `email_id` varchar(45) default NULL,
  `pass_token` varchar(128) default NULL,
  `course` varchar(45) default NULL,
  `about_me` varchar(45) default NULL,
  PRIMARY KEY  (`profile_id`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`),
  UNIQUE KEY `contact_no_UNIQUE` (`contact_no`),
  UNIQUE KEY `token_id_UNIQUE` (`pass_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `profile_table` */

insert  into `profile_table`(`profile_id`,`first_name`,`last_name`,`contact_no`,`address`,`email_id`,`pass_token`,`course`,`about_me`) values (1,'admin','admin','000000','indore','admin','12','BE','dffdds'),(2,'rahul','sharma','4567034541','america','solankini.com','solankinirmal24@gmail.com,938383847,1499342364391',NULL,'rtr'),(3,'nirmal','solnki','9179784636','gwalior','sal24@gmail.com','ert',NULL,'hii this is nirmal request'),(4,'raszdl','sharma','45541','america','dssddfghj@gmail.com','sdasdads',NULL,'sdfgfd'),(5,'martand','mishra','000000000','indore','solmal24@gmail.com',NULL,'ec','hii'),(6,'aditya','vyas','0909090909','indore','av@mailinator.com',NULL,'java',NULL),(7,'aditya','vyas','1234567890','indore','av1@mailinator.com',NULL,'c++',NULL),(8,'Gopal','Dutt','123467890','378/9, Tilak Nagar','gopaldutt39@gmail.com',NULL,'BTECH',NULL),(9,'gh','ghg','454545422','hgjjhg ','gg@mailinator.com',NULL,NULL,NULL),(10,'op','op','65765754645','fh hfghf','op@mailinator.com',NULL,NULL,NULL),(11,'nm','nm','2434343434','jgjhg hg gh','nm@mailinator.com',NULL,NULL,NULL),(12,'uh','uh','5454343423','hfgh fh','uh@mailinator.com',NULL,NULL,NULL),(13,'v','b','9000009000','dfvgsdf  fdgfh','vivek11@mailinator.com',NULL,NULL,NULL),(14,'dd','d','234323424242','hgjhg','bnbn@mailinator.com',NULL,NULL,NULL),(15,'oi','oi','2323232323','zsdgsdg','oi@mailinator.com',NULL,NULL,NULL);

/*Table structure for table `question_table` */

DROP TABLE IF EXISTS `question_table`;

CREATE TABLE `question_table` (
  `question_id` int(20) NOT NULL auto_increment,
  `description` varchar(300) default NULL,
  `sub_id` int(20) default NULL,
  `option_a` varchar(100) default NULL,
  `option_b` varchar(100) default NULL,
  `option_c` varchar(100) default NULL,
  `option_d` varchar(100) default NULL,
  `answer` varchar(100) default NULL,
  PRIMARY KEY  (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `question_table` */

insert  into `question_table`(`question_id`,`description`,`sub_id`,`option_a`,`option_b`,`option_c`,`option_d`,`answer`) values (8,'qqqqqqqqqqqqqqq?',14,'a','b','c','d','option_a'),(9,'qqqqqqqqqqqqqqq?',14,'a','b','c','d','option_a'),(10,'qqqqqqqqqqqqqqq?',14,'a','b','c','d','option_a'),(11,'qqqqqqqqqqqqqqq?',14,'a','b','c','d','option_a'),(12,'qqqqqqqqqqqqqqq?',14,'a','b','c','d','option_a'),(13,'What is Anderoid ?',5,'a','b','c','d','option_a'),(14,'What is Anderoid ?',5,'a','b','c','d','option_a'),(15,'What is Anderoid ?',5,'a','b','c','d','option_a'),(16,'What is Anderoid ?',5,'a','b','c','d','option_a'),(17,'What is Anderoid ?',5,'a','b','c','d','option_a'),(18,'What is Anderoid ?',5,'a','b','c','d','option_a'),(19,'1',1,'1','2','3','4','option_a'),(20,'1',1,'1','2','3','4','option_a'),(21,'1',1,'1','2','3','4','option_a'),(22,'1',1,'1','2','3','4','option_a');

/*Table structure for table `result_table` */

DROP TABLE IF EXISTS `result_table`;

CREATE TABLE `result_table` (
  `result_id` bigint(10) NOT NULL auto_increment,
  `timestamp` bigint(50) default NULL,
  `total` int(20) default NULL,
  `status` int(5) default NULL,
  `sub_id` int(20) default NULL,
  `user_id` int(20) default NULL,
  `code` varchar(20) default NULL,
  `is_verified` tinyint(1) default NULL,
  `is_qualified` tinyint(1) default NULL,
  `percent` float default NULL,
  `attempt` int(20) default NULL,
  PRIMARY KEY  (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `result_table` */

insert  into `result_table`(`result_id`,`timestamp`,`total`,`status`,`sub_id`,`user_id`,`code`,`is_verified`,`is_qualified`,`percent`,`attempt`) values (9,1514141369771,5,NULL,14,8,NULL,NULL,1,100,5),(10,1514141396030,5,NULL,14,8,NULL,NULL,1,200,10),(11,1514141556288,6,NULL,5,8,NULL,NULL,0,0,0),(12,1514745485295,6,NULL,5,14,NULL,NULL,1,100,6),(13,1514745725146,4,NULL,1,14,NULL,NULL,0,0,3),(14,1514745757175,4,NULL,1,14,NULL,NULL,1,100,7),(18,1514748035439,5,NULL,14,14,NULL,NULL,1,100,9);

/*Table structure for table `subject_table` */

DROP TABLE IF EXISTS `subject_table`;

CREATE TABLE `subject_table` (
  `sub_id` bigint(20) NOT NULL auto_increment,
  `sub_name` varchar(100) NOT NULL,
  PRIMARY KEY  (`sub_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `subject_table` */

insert  into `subject_table`(`sub_id`,`sub_name`) values (1,'java'),(2,'php'),(3,'c'),(4,'c++'),(5,'android'),(6,'ios'),(7,'scala'),(8,'hadoop'),(9,'big data'),(10,'mongo db'),(11,'cobal'),(14,'sql'),(15,'html'),(16,'D'),(17,'objective c'),(18,'swift');

/*Table structure for table `tutorial_table` */

DROP TABLE IF EXISTS `tutorial_table`;

CREATE TABLE `tutorial_table` (
  `tutorial_id` int(20) NOT NULL auto_increment,
  `sub_id` int(20) default NULL,
  `heading` varchar(300) default NULL,
  `description` varchar(20000) default NULL,
  UNIQUE KEY `tutorial_id` (`tutorial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tutorial_table` */

insert  into `tutorial_table`(`tutorial_id`,`sub_id`,`heading`,`description`) values (1,5,'introduction','introduction'),(2,5,'',''),(3,1,'Introduction','Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.'),(4,1,'Overview','Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems\' Java platform (Java 1.0 [J2SE]).\r\n\r\nThe latest release of the Java Standard Edition is Java SE 8. With the advancement of Java and its widespread popularity, multiple configurations were built to suit various types of platforms. For example: J2EE for Enterprise Applications, J2ME for Mobile Applications.\r\n\r\nThe new J2 versions were renamed as Java SE, Java EE, and Java ME respectively. Java is guaranteed to be Write OnceJava programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems\' Java platform (Java 1.0 [J2SE]). The latest release of the Java Standard Edition is Java SE 8. With the advancement of Java and its widespread popularity, multiple configurations were built to suit various types of platforms. For example: J2EE for Enterprise Applications, J2ME for Mobile Applications. The new J2 versions were renamed as Java SE, Java EE, and Java ME respectively. Java is guaranteed to be Write Once, Run Anywhere.Run Anywhere.'),(5,1,'History of Java','Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems\' Java platform (Java 1.0 [J2SE]). The latest release of the Java Standard Edition is Java SE 8. With the advancement of Java and its widespread popularity, multiple configurations were built to suit various types of platforms. For example: J2EE for Enterprise Applications, J2ME for Mobile Applications. The new J2 versions were renamed as Java SE, Java EE, and Java ME respectively. Java is guaranteed to be Write Once, Run Anywhere.'),(6,1,'Tools You Will Need','For performing the examples discussed in this tutorial, you will need a Pentium 200-MHz computer with a minimum of 64 MB of RAM (128 MB of RAM recommended).\r\n\r\nYou will also need the following softwares ?\r\n\r\nLinux 7.1 or Windows xp/7/8 operating system\r\nJava JDK 8\r\nMicrosoft Notepad or any other text editor\r\nThis tutorial will provide the necessary skills to create GUI, networking, and weFor performing the examples discussed in this tutorial, you will need a Pentium 200-MHz computer with a minimum of 64 MB of RAM (128 MB of RAM recommended).\r\n\r\nYou will also need the following softwares ?\r\n\r\nLinux 7.1 or Windows xp/7/8 operating system\r\nJava JDK 8\r\nMicrosoft Notepad or any other text editor\r\nThis tutorial will provide the necessary skills to create GUI, networking, and web applications using Java.b applications using Java.');

/*Table structure for table `user_table` */

DROP TABLE IF EXISTS `user_table`;

CREATE TABLE `user_table` (
  `user_id` int(11) NOT NULL,
  `profile_id` int(11) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_table` */

insert  into `user_table`(`user_id`,`profile_id`) values (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
