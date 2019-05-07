-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: student
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter` (
  `ChaNo` int(11) NOT NULL AUTO_INCREMENT,
  `ChaName` varchar(500) NOT NULL,
  `ChaNoLast` int(11) DEFAULT NULL,
  PRIMARY KEY (`ChaNo`),
  UNIQUE KEY `ChaName_UNIQUE` (`ChaName`),
  KEY `FK_Chpater_Chapter_idx` (`ChaNoLast`),
  KEY `FK_Chpater_thisChapter` (`ChaNoLast`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classfeedbackcache`
--

DROP TABLE IF EXISTS `classfeedbackcache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classfeedbackcache` (
  `SNo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `QAnswer` varchar(500) DEFAULT NULL,
  `hash` int(11) DEFAULT NULL,
  PRIMARY KEY (`SNo`,`QNo`),
  KEY `FK_Feedbackcache_Question_idx` (`QNo`),
  CONSTRAINT `FK_Feedbackcache_Question` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Feedbackcache_Student` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `ClaNo` varchar(50) NOT NULL,
  `ClaName` varchar(50) NOT NULL,
  `CTNo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ClaNo`),
  KEY `CTNo` (`CTNo`),
  CONSTRAINT `CTNo` FOREIGN KEY (`CTNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `count_exam`
--

DROP TABLE IF EXISTS `count_exam`;
/*!50001 DROP VIEW IF EXISTS `count_exam`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `count_exam` AS SELECT 
 1 AS `ENo`,
 1 AS `cou`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `count_question`
--

DROP TABLE IF EXISTS `count_question`;
/*!50001 DROP VIEW IF EXISTS `count_question`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `count_question` AS SELECT 
 1 AS `trainNo`,
 1 AS `cou`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `TNo` varchar(50) NOT NULL,
  `CNo` varchar(50) NOT NULL,
  `CouName` varchar(50) NOT NULL,
  PRIMARY KEY (`CNo`),
  KEY `TNo` (`TNo`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `DocNo` int(11) NOT NULL AUTO_INCREMENT,
  `DocName` varchar(500) NOT NULL,
  `DocTime` datetime NOT NULL,
  `TNo` varchar(50) NOT NULL,
  `DocPath` varchar(255) NOT NULL,
  `DocType` varchar(255) NOT NULL,
  `ChaNo` int(50) DEFAULT NULL,
  PRIMARY KEY (`DocNo`),
  KEY `TNo` (`TNo`),
  KEY `ChaNo` (`ChaNo`),
  CONSTRAINT `document_ibfk_1` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `document_ibfk_2` FOREIGN KEY (`ChaNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `ENo` varchar(50) NOT NULL,
  `EName` varchar(500) NOT NULL,
  `TNo` varchar(50) NOT NULL,
  `ClaNo` varchar(50) NOT NULL,
  `ETime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `EBeginTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `EEndTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `EDuration` int(11) NOT NULL DEFAULT '120',
  `EMark` int(11) NOT NULL DEFAULT '100',
  PRIMARY KEY (`ENo`),
  KEY `ENo` (`ENo`),
  KEY `TNO` (`TNo`),
  CONSTRAINT `TNO` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam_q`
--

DROP TABLE IF EXISTS `exam_q`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_q` (
  `ENo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `QMark` double NOT NULL,
  PRIMARY KEY (`ENo`,`QNo`),
  KEY `QNo_exam` (`QNo`),
  CONSTRAINT `ENo_exam` FOREIGN KEY (`ENo`) REFERENCES `exam` (`ENo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `QNo_exam` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examresult`
--

DROP TABLE IF EXISTS `examresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examresult` (
  `SNo` varchar(50) NOT NULL,
  `ENo` varchar(50) NOT NULL,
  `ERMark` int(11) NOT NULL,
  `SETime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SNo`,`ENo`),
  KEY `ENo` (`ENo`),
  CONSTRAINT `examresult_ibfk_1` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examresult_ibfk_2` FOREIGN KEY (`ENo`) REFERENCES `exam` (`ENo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examsheet`
--

DROP TABLE IF EXISTS `examsheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examsheet` (
  `ENo` varchar(50) NOT NULL,
  `SNo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `QAnswer` varchar(500) NOT NULL,
  `TSIsRight` int(11) NOT NULL,
  PRIMARY KEY (`ENo`,`SNo`,`QNo`),
  KEY `examsheet_ibfk_2` (`SNo`),
  KEY `QNo` (`QNo`),
  CONSTRAINT `examsheet_ibfk_1` FOREIGN KEY (`ENo`) REFERENCES `exam` (`ENo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examsheet_ibfk_2` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examsheet_ibfk_3` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examsheet_cache`
--

DROP TABLE IF EXISTS `examsheet_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examsheet_cache` (
  `ENo` varchar(50) NOT NULL,
  `SNo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `QAnswer` varchar(500) NOT NULL,
  `TSIsRight` int(11) NOT NULL,
  PRIMARY KEY (`ENo`,`SNo`,`QNo`),
  KEY `SNo` (`SNo`),
  KEY `QNo` (`QNo`),
  CONSTRAINT `examsheet_cache_ibfk_1` FOREIGN KEY (`ENo`) REFERENCES `exam` (`ENo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examsheet_cache_ibfk_2` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examsheet_cache_ibfk_3` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examtime`
--

DROP TABLE IF EXISTS `examtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examtime` (
  `SNo` varchar(50) NOT NULL,
  `ENo` varchar(50) NOT NULL,
  `ETime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SNo`,`ENo`),
  CONSTRAINT `examtime_ibfk_1` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `FeedNo` int(11) NOT NULL AUTO_INCREMENT,
  `FeedTitle` varchar(255) NOT NULL,
  `FeedContent` varchar(1000) NOT NULL,
  `FeedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SNo` varchar(50) NOT NULL,
  PRIMARY KEY (`FeedNo`),
  KEY `SNo` (`SNo`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge`
--

DROP TABLE IF EXISTS `knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `knowledge` varchar(50) DEFAULT NULL,
  `preview_veryHigh` int(11) DEFAULT '0',
  `preview_High` int(11) DEFAULT '0',
  `preview_Low` int(11) DEFAULT '0',
  `preview_veryLow` int(11) DEFAULT '0',
  `class_veryHigh` int(11) DEFAULT '0',
  `class_High` int(11) DEFAULT '0',
  `class_Low` int(11) DEFAULT '0',
  `class_veryLow` int(11) DEFAULT '0',
  `chpNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `knowledge_UNIQUE` (`knowledge`),
  KEY `FK_Knowledge_chapter_idx` (`chpNo`),
  CONSTRAINT `FK_Knowledge_chapter` FOREIGN KEY (`chpNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ojacproblem`
--

DROP TABLE IF EXISTS `ojacproblem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ojacproblem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SNo` varchar(50) DEFAULT NULL,
  `ojid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_OJ` (`SNo`,`ojid`) USING BTREE,
  KEY `FK_OJACProblem_ojproblem_idx` (`ojid`),
  CONSTRAINT `FK_OJACProblem_Student` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_OJACProblem_ojproblem` FOREIGN KEY (`ojid`) REFERENCES `ojproblem` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ojproblem`
--

DROP TABLE IF EXISTS `ojproblem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ojproblem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `ChaNo` int(11) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `knowledgeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Ojproblem_chapter_idx` (`ChaNo`),
  KEY `FK_Ojproblem_knowledge_idx` (`knowledgeId`),
  CONSTRAINT `FK_Ojproblem_chapter` FOREIGN KEY (`ChaNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Ojproblem_knowledge` FOREIGN KEY (`knowledgeId`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option` (
  `ONo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `OContent` varchar(500) NOT NULL,
  `OIsAnswer` int(11) DEFAULT NULL,
  PRIMARY KEY (`ONo`,`QNo`),
  KEY `question_option` (`QNo`),
  CONSTRAINT `question_option` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `preview_knowledge`
--

DROP TABLE IF EXISTS `preview_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preview_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TNo` varchar(50) DEFAULT NULL,
  `ClaNo` varchar(50) DEFAULT NULL,
  `tpid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Preview_Teacher_idx` (`TNo`),
  KEY `FK_Preview_Classroom_idx` (`ClaNo`),
  KEY `FK_Preview_TPreview_idx` (`tpid`),
  KEY `FK_Preview_Knowledge_idx` (`rid`),
  CONSTRAINT `FK_Preview_Classroom` FOREIGN KEY (`ClaNo`) REFERENCES `classroom` (`ClaNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Preview_Knowledge` FOREIGN KEY (`rid`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Preview_TPreview` FOREIGN KEY (`tpid`) REFERENCES `tpreview` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Preview_Teacher` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `QNo` int(11) NOT NULL AUTO_INCREMENT,
  `QTitle` varchar(500) NOT NULL,
  `QType` int(11) NOT NULL,
  `ChaNo` int(11) NOT NULL,
  `QDifficulty` int(11) NOT NULL,
  `QRange` varchar(50) DEFAULT NULL,
  `QAnswer` varchar(500) DEFAULT NULL,
  `QKnowledge` int(11) DEFAULT NULL,
  PRIMARY KEY (`QNo`),
  KEY `ChaNo_question_chapter` (`ChaNo`),
  KEY `FK_Question_knowledge_idx` (`QKnowledge`),
  CONSTRAINT `ChaNo_question_chapter` FOREIGN KEY (`ChaNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Question_knowledge` FOREIGN KEY (`QKnowledge`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spreview`
--

DROP TABLE IF EXISTS `spreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spreview` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SNo` varchar(50) DEFAULT NULL,
  `tpdid` int(11) DEFAULT NULL,
  `sstate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SPreview_Detail_idx` (`tpdid`),
  KEY `FK_SPreview_Student` (`SNo`),
  CONSTRAINT `FK_SPreview_Detail` FOREIGN KEY (`tpdid`) REFERENCES `tpreview_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SPreview_Student` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=851 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `SNo` varchar(50) NOT NULL,
  `SPwd` varchar(50) NOT NULL,
  `SName` varchar(50) NOT NULL,
  `SSex` varchar(50) NOT NULL,
  `SCollege` varchar(50) NOT NULL,
  `SMajor` varchar(50) NOT NULL,
  `ClaNo` varchar(50) NOT NULL,
  PRIMARY KEY (`SNo`),
  KEY `ClaNo` (`ClaNo`),
  CONSTRAINT `ClaNo` FOREIGN KEY (`ClaNo`) REFERENCES `classroom` (`ClaNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_knowledge`
--

DROP TABLE IF EXISTS `student_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SNo` varchar(50) DEFAULT NULL,
  `kid` int(11) DEFAULT NULL,
  `preview_veryHigh` int(11) DEFAULT '0',
  `preview_High` int(11) DEFAULT '0',
  `preview_Low` int(11) DEFAULT '0',
  `preview_veryLow` int(11) DEFAULT '0',
  `class_veryHigh` int(11) DEFAULT '0',
  `class_High` int(11) DEFAULT '0',
  `class_Low` int(11) DEFAULT '0',
  `class_veryLow` int(11) DEFAULT '0',
  `tpid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SKnowledge_Student_idx` (`SNo`),
  KEY `FK_SKnowledge_Knowledge_idx` (`kid`),
  CONSTRAINT `FK_SKnowledge_Knowledge` FOREIGN KEY (`kid`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SKnowledge_Student` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `TNo` varchar(50) NOT NULL,
  `TPwd` varchar(50) NOT NULL,
  `Tname` varchar(50) NOT NULL,
  `TSex` varchar(10) NOT NULL,
  `TSchool` varchar(50) NOT NULL,
  `TCollege` varchar(50) NOT NULL,
  `TMajor` varchar(50) NOT NULL,
  `TAbout` varchar(500) NOT NULL,
  PRIMARY KEY (`TNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testsheet`
--

DROP TABLE IF EXISTS `testsheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testsheet` (
  `SNo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `TrainNo` int(11) NOT NULL,
  `QAnswer` varchar(500) NOT NULL,
  `TSIsRight` int(11) NOT NULL,
  PRIMARY KEY (`SNo`,`QNo`,`TrainNo`),
  KEY `qno_testsheet` (`QNo`),
  KEY `trainno_testsheet` (`TrainNo`),
  CONSTRAINT `qno_testsheet` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sno_testsheet` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainno_testsheet` FOREIGN KEY (`TrainNo`) REFERENCES `training` (`TrainNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testsheet_cache`
--

DROP TABLE IF EXISTS `testsheet_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testsheet_cache` (
  `SNo` varchar(50) NOT NULL,
  `QNo` int(11) NOT NULL,
  `TrainNo` int(11) NOT NULL,
  `QAnswer` varchar(500) NOT NULL,
  `TSIsRight` int(11) NOT NULL,
  PRIMARY KEY (`SNo`,`QNo`,`TrainNo`),
  KEY `qno_testsheet` (`QNo`),
  KEY `trainno_testsheet` (`TrainNo`),
  CONSTRAINT `testsheet_cache_ibfk_1` FOREIGN KEY (`TrainNo`) REFERENCES `training` (`TrainNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testsheet_cache_ibfk_2` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testsheet_cache_ibfk_3` FOREIGN KEY (`SNo`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tpreview`
--

DROP TABLE IF EXISTS `tpreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tpreview` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TNo` varchar(50) DEFAULT NULL,
  `ClaNo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TPreview_Teacher_idx` (`TNo`),
  KEY `FK_TPreview_Classroom_idx` (`ClaNo`),
  CONSTRAINT `FK_TPreview_Classroom` FOREIGN KEY (`ClaNo`) REFERENCES `classroom` (`ClaNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_TPreview_Teacher` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tpreview_detail`
--

DROP TABLE IF EXISTS `tpreview_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tpreview_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tpid` int(11) DEFAULT NULL,
  `trequire` text,
  `require_type` int(11) DEFAULT NULL,
  `require_id` int(11) DEFAULT NULL,
  `isonline` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TPDetail_TPreview_idx` (`tpid`),
  CONSTRAINT `FK_TPDetail_TPreview` FOREIGN KEY (`tpid`) REFERENCES `tpreview` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `train_q`
--

DROP TABLE IF EXISTS `train_q`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train_q` (
  `TrainNo` int(11) NOT NULL,
  `QNo` int(11) NOT NULL,
  PRIMARY KEY (`TrainNo`,`QNo`),
  KEY `QNo_question` (`QNo`),
  CONSTRAINT `QNo_question` FOREIGN KEY (`QNo`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TrainNo_training` FOREIGN KEY (`TrainNo`) REFERENCES `training` (`TrainNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training` (
  `TrainNo` int(11) NOT NULL AUTO_INCREMENT,
  `TrainName` varchar(255) NOT NULL,
  `TrainType` int(11) NOT NULL,
  `TrainTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `TrainEndTime` timestamp NULL DEFAULT NULL,
  `ChaNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`TrainNo`),
  KEY `ChaNo_training_chapter` (`ChaNo`),
  CONSTRAINT `ChaNo_training_chapter` FOREIGN KEY (`ChaNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `VNo` int(11) NOT NULL AUTO_INCREMENT,
  `VName` varchar(500) NOT NULL,
  `VIntroduce` varchar(500) DEFAULT NULL,
  `VType` int(11) NOT NULL,
  `ChaNo` int(50) NOT NULL,
  `VRange` varchar(50) DEFAULT NULL,
  `VTime` datetime NOT NULL,
  `TNo` varchar(50) NOT NULL,
  `VCount` int(11) NOT NULL,
  `VAddress` varchar(255) NOT NULL,
  `VImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VNo`),
  KEY `ChaNo` (`ChaNo`),
  KEY `TNo` (`TNo`),
  CONSTRAINT `video_ibfk_1` FOREIGN KEY (`ChaNo`) REFERENCES `chapter` (`ChaNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `video_ibfk_2` FOREIGN KEY (`TNo`) REFERENCES `teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `videofinish`
--

DROP TABLE IF EXISTS `videofinish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videofinish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(50) DEFAULT NULL,
  `vno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Videofinish_Student_idx` (`sno`),
  KEY `FK_Videofinish_Video_idx` (`vno`),
  CONSTRAINT `FK_Videofinish_Student` FOREIGN KEY (`sno`) REFERENCES `student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Videofinish_Video` FOREIGN KEY (`vno`) REFERENCES `video` (`VNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wrongquestion`
--

DROP TABLE IF EXISTS `wrongquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wrongquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Wrongquestion_question_idx` (`qid`),
  CONSTRAINT `FK_Wrongquestion_question` FOREIGN KEY (`qid`) REFERENCES `question` (`QNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'student'
--

--
-- Dumping routines for database 'student'
--

--
-- Final view structure for view `count_exam`
--

/*!50001 DROP VIEW IF EXISTS `count_exam`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `count_exam` AS select `exam_q`.`ENo` AS `ENo`,count(0) AS `cou` from `exam_q` group by `exam_q`.`ENo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `count_question`
--

/*!50001 DROP VIEW IF EXISTS `count_question`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `count_question` AS select `train_q`.`TrainNo` AS `trainNo`,count(0) AS `cou` from `train_q` group by `train_q`.`TrainNo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-07 12:44:09
