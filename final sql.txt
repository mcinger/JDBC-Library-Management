drop database if exists task5;

create database task5;

use task5;

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `Title_id` varchar(13) NOT NULL,
  `Author_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#Title_id is 13 number
#Title_id=Isbn
# 

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES ('0000000000001','dong');
INSERT INTO `author` VALUES ('0000000000002','kenb');
INSERT INTO `author` VALUES ('0000000000003','mary');
INSERT INTO `author` VALUES ('0000000000004','tom');
INSERT INTO `author` VALUES ('0000000000005','peter');
INSERT INTO `author` VALUES ('0000000000006','tracy');
INSERT INTO `author` VALUES ('0000000000007','lily');
INSERT INTO `author` VALUES ('0000000000008','hary');
INSERT INTO `author` VALUES ('0000000000009','john');
INSERT INTO `author` VALUES ('0000000000010','megan');

/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table borrow
#

DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `Card_id` varchar(6) NOT NULL,
  `Book_id` varchar(16) NOT NULL,
  `Borrow_date` date DEFAULT NULL,
  `Borrow_time` int(11) DEFAULT NULL,
  `Borrow_times` int(11) DEFAULT NULL,
  `Title_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Card_id`,`Book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table borrow
#

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES ('000001','1234567812345678','2011-12-01',30,2,'french');
INSERT INTO `borrow` VALUES ('000002','8765432187654321','2012-09-11',30,1,'math');
INSERT INTO `borrow` VALUES ('000003','1234123412341234','2010-09-11',30,1,'chinese');
INSERT INTO `borrow` VALUES ('000004','1212121212121212','2013-08-15',30,1,'biology');
INSERT INTO `borrow` VALUES ('000005','1234567887654321','2011-06-19',30,1,'geography');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `Card_id` varchar(6) NOT NULL,
  `Title_name` varchar(100) NOT NULL,
  `Comment_content` varchar(1000) NOT NULL,
  `Comment_date` date DEFAULT NULL,
  PRIMARY KEY (`Card_id`,`Title_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table comment
#

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('000001','french','good example','2012-09-13');
INSERT INTO `comment` VALUES ('000003','chinese','good book','2011-09-13');
INSERT INTO `comment` VALUES ('000005','geography','no example','2012-06-13');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `Card_id` varchar(6) NOT NULL,
  `Book_id` varchar(16) NOT NULL,
  `Borrow_date` date NOT NULL,
  `Return_date` date NOT NULL,
  PRIMARY KEY (`Card_id`,`Book_id`,`Borrow_date`,`Return_date`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table history
#

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('000001','1234567812345678','2011-12-01','2012-01-30');
INSERT INTO `history` VALUES ('000002','8765432187654321','2012-09-11','2012-11-17');
INSERT INTO `history` VALUES ('000003','1234123412341234','2010-09-11','2010-12-17');
INSERT INTO `history` VALUES ('000004','1212121212121212','2013-08-15','2014-01-10');
INSERT INTO `history` VALUES ('000005','1234567887654321','2011-06-19','2011-06-29');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table keyword
#

DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `Title_id` varchar(13) NOT NULL,
  `Key_word` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES ('0000000000001','france');
INSERT INTO `keyword` VALUES ('0000000000002','calculate');
INSERT INTO `keyword` VALUES ('0000000000003','dragon');
INSERT INTO `keyword` VALUES ('0000000000004','darwin');
INSERT INTO `keyword` VALUES ('0000000000005','contient');
INSERT INTO `keyword` VALUES ('0000000000006','story');
INSERT INTO `keyword` VALUES ('0000000000007','einstein');
INSERT INTO `keyword` VALUES ('0000000000008','jordan');
INSERT INTO `keyword` VALUES ('0000000000009','messi');
INSERT INTO `keyword` VALUES ('0000000000010','starwar');
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table manager
#

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `Manager_id` varchar(8) NOT NULL,
  `Manager_pwd` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`Manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table manager
#

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('admin','admin');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;


#
# Source for table message
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `Card_id` varchar(6) NOT NULL,
  `message` varchar(100) NOT NULL,
  PRIMARY KEY (`Card_id`,`message`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Dumping data for table message
#

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('000001','Qianzhou');
INSERT INTO `message` VALUES ('000002','Professor');
INSERT INTO `message` VALUES ('000003','president');
INSERT INTO `message` VALUES ('000004','lily');
INSERT INTO `message` VALUES ('000005','zhang');
INSERT INTO `message` VALUES ('000006','shen');
INSERT INTO `message` VALUES ('000007','zhao');
INSERT INTO `message` VALUES ('000008','wei');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;


#
# Source for table pay
#

DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `Card_id` varchar(6) NOT NULL,
  `Book_id` varchar(16) NOT NULL,
  `Title_name` varchar(100) DEFAULT NULL,
  `R_reason` varchar(1000) DEFAULT NULL,
  `Pay_price` varchar(10) DEFAULT NULL,
  `Borrow_date` date DEFAULT NULL,
  `Pay_date` date DEFAULT NULL,
  PRIMARY KEY (`Card_id`,`Book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES ('000001','1234567812345678','french','pass due','20','2011-12-01','2012-02-30');
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `Card_id` varchar(6) NOT NULL,
  `Title_isbn` varchar(13) NOT NULL,
  `Title_name` varchar(100) DEFAULT NULL,
  `Title_press` varchar(100) DEFAULT NULL,
  `R_reason` varchar(1000) DEFAULT NULL,
  `Title_author` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Card_id`,`Title_isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `recommend` WRITE;
INSERT INTO `recommend` VALUES ('000003','0000000000003','chinese','beijing','useful with example easy to understand','mary');
UNLOCK TABLES;

DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `Isbn` varchar(13) NOT NULL,
  `Title_name` varchar(100) DEFAULT NULL,
  `Title_press` varchar(100) DEFAULT NULL,
  `Title_date` date DEFAULT NULL,
  `Title_price` varchar(10) DEFAULT NULL,
  `Title_booknumber` int(11) DEFAULT NULL,
  `Title_currentnumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`Isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES ('0000000000001','french','neu','2001-01-01','12',6,3);
INSERT INTO `title` VALUES ('0000000000002','math','boston','2010-09-01','10',6,5);
INSERT INTO `title` VALUES ('0000000000003','chinese','beijing','1998-09-09','30',10,2);
INSERT INTO `title` VALUES ('0000000000004','biology','nature','1998-09-09','30',3,1);
INSERT INTO `title` VALUES ('0000000000005','geography','nature','1998-09-09','30',8,3);
INSERT INTO `title` VALUES ('0000000000006','novel','anderson','1998-09-09','30',2,2);
INSERT INTO `title` VALUES ('0000000000007','science','discovery','1998-09-09','30',10,5);
INSERT INTO `title` VALUES ('0000000000008','basketball','sports','1998-09-09','30',16,2);
INSERT INTO `title` VALUES ('0000000000009','soccer','sports','1998-09-09','30',18,4);
INSERT INTO `title` VALUES ('0000000000010','movie','entertainment','1998-09-09','30',3,4);
UNLOCK TABLES;

#
# Source for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Card_id` varchar(6) NOT NULL,
  `User_name` varchar(100) DEFAULT NULL,
  `User_id` varchar(18) DEFAULT NULL,
  `Card_date` date DEFAULT NULL,
  `Card_time` int(11) DEFAULT NULL,
  `User_phone` varchar(12) DEFAULT NULL,
  `User_password` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`Card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('000001','wang','110107199206191516','2012-09-06',365,'18765823931','wang');
INSERT INTO `user` VALUES ('000002','johnny','110107199206191516','2012-09-06',365,'18765823931','johnny');
INSERT INTO `user` VALUES ('000003','trump','110107199206191516','2012-09-06',365,'18765823931','trump');
INSERT INTO `user` VALUES ('000004','li','370126199107177126','2012-09-10',365,'18765823936','li');
INSERT INTO `user` VALUES ('000005','zhang','370120299988999988','2012-09-11',365,'18765923930','zhang');
INSERT INTO `user` VALUES ('000006','shen','370123759959586455','2011-10-10',365,'18755345345','shen');
INSERT INTO `user` VALUES ('000007','zhao','370912774579735978','2010-01-01',365,'18755345346','zhao');
INSERT INTO `user` VALUES ('000008','wei','333334566767665556','2009-11-11',365,'18869956766','wei');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

 