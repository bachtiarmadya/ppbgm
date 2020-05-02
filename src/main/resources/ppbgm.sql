-- USER LEVEL
CREATE TABLE `userlevel` (
  `levelid` varchar(50) NOT NULL,
  `levelcode` varchar(30) NOT NULL,
  `levelname` varchar(30) NOT NULL,
  `description` text,
  `isactive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`levelid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- USER ADDRESS
CREATE TABLE `useraddress` (
  `addressid` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `zone` varchar(30) NOT NULL,
  `geolocation` varchar(255) NOT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- USER MEMBER
CREATE TABLE `usermember` (
  `memberid` varchar(50) NOT NULL,
  `membercode` varchar(20) NOT NULL,
  `membername` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `avatar` varchar(250) NOT NULL,
  `levelid` varchar(50) NOT NULL,
  `addressid` varchar(50) DEFAULT NULL,
  `createdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`memberid`),
  KEY `levelid` (`levelid`),
  KEY `addressid` (`addressid`),
  CONSTRAINT `addressid` FOREIGN KEY (`addressid`) REFERENCES `useraddress` (`addressid`),
  CONSTRAINT `levelid` FOREIGN KEY (`levelid`) REFERENCES `userlevel` (`levelid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- MANAGED USER (MAIN USER TABLES)
CREATE TABLE `manageduser` (
  `userid` varchar(50) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `memberid` varchar(50) NOT NULL,
  `isactive` tinyint NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `memberid` FOREIGN KEY (`memberid`) REFERENCES `usermember` (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- USER RELATIONSHIP
CREATE TABLE `userrelations` (
  `relationid` varchar(50) NOT NULL,
  `firstid` text NOT NULL,
  `secondid` varchar(30) NOT NULL,
  PRIMARY KEY (`relationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- OTP LOG
CREATE TABLE `otplog` (
  `logid` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `token` varchar(30) NOT NULL,
  `createdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1
