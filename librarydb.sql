-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 24, 2019 at 11:08 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

create database librarydb;
use librarydb;



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Book`
--

CREATE TABLE `Book` (
  `bookId` varchar(10) NOT NULL,
  `ISBN` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `edition` varchar(10) NOT NULL,
  `Subject` varchar(50) NOT NULL,
  `campusName` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Book`
--

INSERT INTO `Book` (`bookId`, `ISBN`, `title`, `author`, `edition`, `Subject`, `campusName`, `status`) VALUES
('101', '10382284', 'Introduction to Java Programming', 'Y. Daniel Liang', '10', 'Information Technology', 'Surrey', 0),
('102', '23344434373', 'Fundamentals of business in Canada', 'James', '10', 'Business', 'Langley', 1),
('103', '23344434373 ', 'Fundamentals of business in Canada', 'James', '10', 'Business', 'Surrey', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Campus`
--

CREATE TABLE `Campus` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Campus`
--

INSERT INTO `Campus` (`name`) VALUES
('Cloverdale'),
('Langley'),
('Richmond'),
('Surrey');

-- --------------------------------------------------------

--
-- Table structure for table `Issue`
--

CREATE TABLE `Issue` (
  `bookId` varchar(10) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `issueTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dueDate` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Issue`
--

INSERT INTO `Issue` (`bookId`, `userId`, `issueTime`, `dueDate`) VALUES
('101', '100362906', '2019-06-05 23:40:13', '2019-06-12');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `userId` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `lName` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `userType` varchar(10) NOT NULL,
  `campusName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`userId`, `username`, `password`, `fName`, `lName`, `DOB`, `userType`, `campusName`) VALUES
('01', 'mike', 'mike01', 'Mike', 'Ross', '1992-05-06', 'admin', 'Surrey'),
('100362906', 'janmeet', 'janmeet46', 'Janmeet', 'Grewal', '1999-07-19', 'student', 'Surrey');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Book`
--
ALTER TABLE `Book`
  ADD PRIMARY KEY (`bookId`),
  ADD KEY `campusName` (`campusName`);

--
-- Indexes for table `Campus`
--
ALTER TABLE `Campus`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `Issue`
--
ALTER TABLE `Issue`
  ADD PRIMARY KEY (`bookId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`userId`),
  ADD KEY `campusName` (`campusName`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Book`
--
ALTER TABLE `Book`
  ADD CONSTRAINT `Book_ibfk_1` FOREIGN KEY (`campusName`) REFERENCES `Campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Issue`
--
ALTER TABLE `Issue`
  ADD CONSTRAINT `Issue_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `Book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Issue_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `User`
--
ALTER TABLE `User`
  ADD CONSTRAINT `User_ibfk_1` FOREIGN KEY (`campusName`) REFERENCES `Campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
