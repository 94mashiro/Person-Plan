-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: May 24, 2016 at 12:23 PM
-- Server version: 5.5.42
-- PHP Version: 7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `personplan`
--

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(2) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `create_time` bigint(20) DEFAULT NULL,
  `done_time` bigint(20) DEFAULT NULL,
  `step_number` int(11) DEFAULT NULL,
  `done_number` int(11) DEFAULT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`id`, `user_id`, `name`, `create_time`, `done_time`, `step_number`, `done_number`, `status`) VALUES
(2, 2, 'test2', 1462930719848, 0, 4, 0, 0),
(3, 2, 'test23', 1462930719848, 0, 1, 0, 0),
(5, 2, 'test244', 1462930719848, 0, 2, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `step`
--

CREATE TABLE `step` (
  `id` int(11) unsigned NOT NULL,
  `plan_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `plan_start` bigint(20) DEFAULT NULL,
  `plan_done` bigint(20) DEFAULT NULL,
  `fin_start` bigint(20) DEFAULT NULL,
  `fin_done` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `step`
--

INSERT INTO `step` (`id`, `plan_id`, `name`, `plan_start`, `plan_done`, `fin_start`, `fin_done`, `status`) VALUES
(1, 2, 'test-step', 1462896000000, 1465574400000, 0, 0, 0),
(2, 2, '213123213', 1420041600000, 1451577600000, 0, 0, 0),
(3, 2, 'e212e1', 946656000000, 946656000000, 0, 0, 0),
(4, 2, '12123', 1462896000000, 1470931200000, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `id`, `password`) VALUES
('mashiro', 1, 'rootmashiro'),
('test', 2, '1234'),
('test2', 3, '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `step`
--
ALTER TABLE `step`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `id` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `step`
--
ALTER TABLE `step`
  MODIFY `id` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;