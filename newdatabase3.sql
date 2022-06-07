-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2022 at 08:26 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newdatabase3`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer_tbl`
--

CREATE TABLE `customer_tbl` (
  `id` int(11) NOT NULL,
  `born_at` date DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_tbl`
--

INSERT INTO `customer_tbl` (`id`, `born_at`, `first_name`, `last_name`) VALUES
(1, '2000-02-18', 'hind', 'ahmad'),
(2, '2000-09-09', 'toqa', 'zuhd'),
(3, '1998-02-03', 'sela', 'mohamad'),
(4, '1999-05-03', 'angham', 'zbaidah');

-- --------------------------------------------------------

--
-- Table structure for table `order_tbl`
--

CREATE TABLE `order_tbl` (
  `id` int(11) NOT NULL,
  `ordered_at` datetime NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_tbl`
--

INSERT INTO `order_tbl` (`id`, `ordered_at`, `customer_id`) VALUES
(1, '2022-06-07 14:22:23', 1),
(2, '2022-06-07 14:22:23', 3);

-- --------------------------------------------------------

--
-- Table structure for table `product_order_tbl`
--

CREATE TABLE `product_order_tbl` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `vat` double NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_order_tbl`
--

INSERT INTO `product_order_tbl` (`id`, `price`, `quantity`, `vat`, `order_id`, `product_id`) VALUES
(1, 6000, 2, 41, 2, 1),
(2, 2500, 1, 15.4, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product_tbl`
--

CREATE TABLE `product_tbl` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `reference` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `stock_able` bit(1) NOT NULL,
  `vat` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_tbl`
--

INSERT INTO `product_tbl` (`id`, `name`, `price`, `reference`, `slug`, `stock_able`, `vat`) VALUES
(1, 'laptop', 3000, 'uu', 'zz', b'1', 20.5),
(2, 'android device', 2500, 'oo', 'pp', b'0', 15.4),
(3, 'iphone', 4500, 'uuk', 'll', b'1', 22.6),
(4, 'apple watch', 1700, 'rr', 'rr', b'1', 10),
(5, 'airbods', 250, 'kk', 'll', b'1', 22.6);

-- --------------------------------------------------------

--
-- Table structure for table `stock_tbl`
--

CREATE TABLE `stock_tbl` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `update_at` datetime NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock_tbl`
--

INSERT INTO `stock_tbl` (`id`, `quantity`, `update_at`, `product_id`) VALUES
(2, 20, '2022-06-07 19:13:25', 5),
(3, 20, '2022-06-07 19:13:25', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_tbl`
--

CREATE TABLE `users_tbl` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_tbl`
--

INSERT INTO `users_tbl` (`id`, `email`, `name`, `password`, `username`) VALUES
(1, 'aseel.zbai@gmail.com', 'aseel', '123', 'aseel15'),
(3, NULL, 'toqa', '$2a$10$2rgf1sTS9YKNLtco0TH5MObmzaeCZiEb16NeOrXuTXIniSkm9Tst6', 'tt2'),
(5, 'ahmad@gmail.com', 'ahmad', '$2a$10$T1Gr3.5WTUe1JG7dUhs.juRPw2q5xkudZDZ.XXz3ahsxxo2lKXHhC', 'aa1'),
(6, 'sela@gmail.com', 'aseelNew', '$2a$10$I9M/DfzJcYqTFpQdbIzJ/eNNbkGdcs6FTswIa4Nsn7aGLVxrmc1Wm', 'aseelNew'),
(8, 'ss@gmail.com', 'rana', '$2a$10$YMoaTe1CQhSf/p1ydvEBLuoi3J5zxQGtokom.fgrsMi.C/Z4uuAGi', 'rana1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_tbl`
--
ALTER TABLE `customer_tbl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf8ao9i69k5d663lelp2cmwb2o` (`customer_id`);

--
-- Indexes for table `product_order_tbl`
--
ALTER TABLE `product_order_tbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3yxov82ypoj10wdb6u94iyfip` (`order_id`),
  ADD KEY `FK4ee4p9f8i1ydxmmuym2022d8k` (`product_id`);

--
-- Indexes for table `product_tbl`
--
ALTER TABLE `product_tbl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_tbl`
--
ALTER TABLE `stock_tbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9mddii9tpk1g7a7p944uuvmy1` (`product_id`);

--
-- Indexes for table `users_tbl`
--
ALTER TABLE `users_tbl`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK7p2qdaqmxojhma31lopxr6gqx` (`username`),
  ADD UNIQUE KEY `UK8usegh22yymqae5jjt4pdbd3k` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_tbl`
--
ALTER TABLE `customer_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order_tbl`
--
ALTER TABLE `order_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_order_tbl`
--
ALTER TABLE `product_order_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_tbl`
--
ALTER TABLE `product_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `stock_tbl`
--
ALTER TABLE `stock_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users_tbl`
--
ALTER TABLE `users_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD CONSTRAINT `FKf8ao9i69k5d663lelp2cmwb2o` FOREIGN KEY (`customer_id`) REFERENCES `customer_tbl` (`id`);

--
-- Constraints for table `product_order_tbl`
--
ALTER TABLE `product_order_tbl`
  ADD CONSTRAINT `FK3yxov82ypoj10wdb6u94iyfip` FOREIGN KEY (`order_id`) REFERENCES `order_tbl` (`id`),
  ADD CONSTRAINT `FK4ee4p9f8i1ydxmmuym2022d8k` FOREIGN KEY (`product_id`) REFERENCES `product_tbl` (`id`);

--
-- Constraints for table `stock_tbl`
--
ALTER TABLE `stock_tbl`
  ADD CONSTRAINT `FK9mddii9tpk1g7a7p944uuvmy1` FOREIGN KEY (`product_id`) REFERENCES `product_tbl` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
