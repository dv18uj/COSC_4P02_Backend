-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 01, 2023 at 01:50 AM
-- Server version: 8.0.32-0ubuntu0.22.04.2
-- PHP Version: 8.1.2-1ubuntu2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `panotour`
--

-- --------------------------------------------------------

--
-- Table structure for table `artifact`
--

CREATE TABLE `artifact` (
  `oid` int NOT NULL,
  `sid` int NOT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `people` varchar(1000) DEFAULT NULL,
  `subjects` varchar(1000) DEFAULT NULL,
  `dimensions` varchar(1000) DEFAULT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `photographer` varchar(1000) DEFAULT NULL,
  `studio` varchar(1000) DEFAULT NULL,
  `number_images` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `artifact`
--

INSERT INTO `artifact` (`oid`, `sid`, `url`, `name`, `description`, `date`, `people`, `subjects`, `dimensions`, `title`, `photographer`, `studio`, `number_images`) VALUES
(1, 1, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_1.jpg', 'Niagara High School', 'This property was originally part of the Military\r\nReserve. In 1823, James Crooks exchanged his\r\nproperty on the site of Fort Mississauga for the\r\nland on which the High School now sits. He later\r\ntransferred this land to the Niagara Board of\r\nTrustees for common and grammar schools.\r\nThis building was built in 1875 and was the first\r\nhigh school in Town. It operated as such from\r\n1875 to 1947. The Society has been in possession\r\nof the building since 1949. It was built as a singlestorey rectangular brick building with a medium\r\npitch gable roof. The features of the building are\r\nderived from Gothic and Italianate Vernacular\r\nstyles.\r\nThe wood trim, brackets and bargeboard on\r\nthe building are machine made and are mostly\r\noriginal.', NULL, 'test', 'Architecture', 'test', 'test', 'test', 'test', 1),
(2, 1, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_2.jpg', 'Double-hung Windows', 'The windows are\r\ndouble-hung divided\r\nsashes, six panes per\r\nsash (left). The sills\r\nof the windows and\r\ndoors are made\r\nfrom cut limestone.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(3, 1, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_3.jpg', 'Mansard Roof and Belfry', 'The ridge of the roof is\r\ninterrupted by the addition\r\nof a mansard roof, the top\r\nof which forms the base of\r\nthe belfry.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(4, 1, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_4.jpg', 'Eavestrough', 'Decorative Italianate style brackets\r\nin the eaves , faced with a plain\r\nhorizontal cornice.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(5, 1, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_5.jpg', 'Brickwork', 'The brick pattern on the front facade\r\nis called stretcher bond with flush\r\njoints.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(6, 3, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_6.jpg', 'Hipped Roof', 'The roof style of Memorial Hall is called a hipped roof.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(7, 3, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_7.jpg', 'Limestone plinth and sills', 'The plinth of the walls and the sills of the openings are\r\nmade of cut limestone, obtained from the John Rogers Dry\r\nGoods Store on Queen Street, which was demolished c1900.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(8, 3, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_8.jpg', 'Single-hung Windows', 'All the front windows on the second\r\nfloor have single-hung divided\r\nsashes, which means they have\r\nsmaller upper sashes with four panes\r\neach, and larger lower sashes with a\r\nsingle pane.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(9, 3, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_9.jpg', 'Corbelled Arch', 'The columns on the porch are\r\nspanned by a corbelled arch.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(10, 2, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_10.jpg', 'Buff brick', 'Buff brick, with brick “Darte” at the quarters of the circle surround the side gable. Only four buildings in Niagara-on-theLake have the use\r\nof buff brick for decoration around openings.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(11, 2, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_11.jpg', 'Link Building', 'This building was constructed in\r\n1971 – 73 to join Memorial Hall with\r\nthe High School Building.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(12, 2, 'https://cosc.brocku.ca/~dv18uj/pano/NOTL_12.jpg', 'Front Windows', 'Eight of the front windows were\r\nobtained from the Parish of St. Mark’s Church, which were removed\r\nfrom the Parish hall in 1965.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hotspot`
--

CREATE TABLE `hotspot` (
  `hid` int NOT NULL,
  `oid` int NOT NULL,
  `pid` int NOT NULL,
  `px` int DEFAULT NULL,
  `py` int DEFAULT NULL,
  `pz` int DEFAULT NULL,
  `rx` int DEFAULT NULL,
  `ry` int DEFAULT NULL,
  `rz` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hotspot`
--

INSERT INTO `hotspot` (`hid`, `oid`, `pid`, `px`, `py`, `pz`, `rx`, `ry`, `rz`) VALUES
(1, 1, 1, 2, 1, -15, 0, 0, 0),
(2, 2, 1, -4, 3, -12, 0, 0, 0),
(3, 3, 1, 0, 15, -15, 1, 0, 0),
(4, 4, 1, 10, 4, -15, 0, -1, 0),
(5, 5, 4, 2, 0, -14, 0, 0, 0),
(6, 6, 3, 1, 8, -8, 0, 0, 0),
(7, 7, 6, 4, 3, -11, 0, 0, 0),
(8, 8, 3, 5, 10, 5, 0, 0, 0),
(9, 9, 3, 2, 2, 3, 0, 0, 0),
(10, 10, 6, -2, 5, -13, 0, 0, 0),
(11, 11, 2, 1, 1, -10, 0, 0, 0),
(12, 12, 2, -5, 1, -10, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `lid` int NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`lid`, `name`, `image`) VALUES
(1, 'Niagara High School', 'https://cosc.brocku.ca/~dv18uj/pano/BA.png'),
(2, 'The Link Building', 'https://cosc.brocku.ca/~dv18uj/pano/BB.png'),
(3, 'Memorial Hall', 'https://cosc.brocku.ca/~dv18uj/pano/BC.png');

-- --------------------------------------------------------

--
-- Table structure for table `panoview`
--

CREATE TABLE `panoview` (
  `pid` int NOT NULL,
  `sid` int NOT NULL,
  `image` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `panoview`
--

INSERT INTO `panoview` (`pid`, `sid`, `image`) VALUES
(1, 1, 'https://cosc.brocku.ca/~dv18uj/pano/1.jpg'),
(2, 2, 'https://cosc.brocku.ca/~dv18uj/pano/2.jpg'),
(3, 3, 'https://cosc.brocku.ca/~dv18uj/pano/3.jpg'),
(4, 1, 'https://cosc.brocku.ca/~dv18uj/pano/4.jpg'),
(5, 1, 'https://cosc.brocku.ca/~dv18uj/pano/5.jpg'),
(6, 3, 'https://cosc.brocku.ca/~dv18uj/pano/6.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `sid` int NOT NULL,
  `lid` int NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`sid`, `lid`, `name`, `description`) VALUES
(1, 1, 'Main Entrance', 'This property was originally part of the Military\r\nReserve. In 1823, James Crooks exchanged his\r\nproperty on the site of Fort Mississauga for the\r\nland on which the High School now sits. He later\r\ntransferred this land to the Niagara Board of\r\nTrustees for common and grammar schools.\r\nThis building was built in 1875 and was the first\r\nhigh school in Town. It operated as such from\r\n1875 to 1947. The Society has been in possession\r\nof the building since 1949. It was built as a single-storey rectangular brick building with a medium\r\npitch gable roof. The features of the building are\r\nderived from Gothic and Italianate Vernacular\r\nstyles.\r\nThe wood trim, brackets and bargeboard on\r\nthe building are machine made and are mostly\r\noriginal.'),
(2, 2, 'Courtyard', 'This building was constructed in\r\n1971 – 73 to join Memorial Hall with\r\nthe High School Building.'),
(3, 3, 'Corbelled Arch Porch', 'The columns on the porch are\r\nspanned by a corbelled arch.');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `role` varchar(64) NOT NULL DEFAULT 'EMPLOYEE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `name`, `password`, `role`) VALUES
(1, 'test', '$2a$10$JVguxe7e7xmB74Nfq9qPxeulgDlS1ztPkR7qfUJEgg.pBpRowNo7a', 'EMPLOYEE'),
(2, 'John', '$2a$10$JVguxe7e7xmB74Nfq9qPxeulgDlS1ztPkR7qfUJEgg.pBpRowNo7a', 'OWNER');

-- --------------------------------------------------------

--
-- Table structure for table `waypoint`
--

CREATE TABLE `waypoint` (
  `wid` int NOT NULL,
  `pid` int NOT NULL,
  `toPid` int NOT NULL,
  `px` double DEFAULT NULL,
  `py` double DEFAULT NULL,
  `pz` double DEFAULT NULL,
  `rx` double DEFAULT NULL,
  `ry` double DEFAULT NULL,
  `rz` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `waypoint`
--

INSERT INTO `waypoint` (`wid`, `pid`, `toPid`, `px`, `py`, `pz`, `rx`, `ry`, `rz`) VALUES
(1, 1, 2, -15, 0, -6, 0, 1.5, 0),
(2, 2, 1, 13, -1, 3, 0, -2, 0),
(3, 3, 1, 14, -1, 0, 0, -1.8, 0),
(4, 3, 6, -12, -1, 0, 0, 1.5, 0),
(5, 1, 4, 16, -1, 3, 0, -2, 0),
(6, 4, 1, -11, 0, -10, 0, 0, 0),
(7, 4, 5, 9, 0, -12, 0, 0, 0),
(8, 5, 4, -11, 0, -12, 0, 0, 0),
(9, 6, 3, 7, -1, -5, 0, -1, 0),
(10, 1, 3, -13, -1, 5, 0, -4, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artifact`
--
ALTER TABLE `artifact`
  ADD PRIMARY KEY (`oid`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `hotspot`
--
ALTER TABLE `hotspot`
  ADD PRIMARY KEY (`hid`),
  ADD KEY `oid` (`oid`),
  ADD KEY `pid` (`pid`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`lid`);

--
-- Indexes for table `panoview`
--
ALTER TABLE `panoview`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `sid` (`sid`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`sid`),
  ADD KEY `lid` (`lid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `uid` (`uid`);

--
-- Indexes for table `waypoint`
--
ALTER TABLE `waypoint`
  ADD PRIMARY KEY (`wid`),
  ADD KEY `pid` (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artifact`
--
ALTER TABLE `artifact`
  ADD CONSTRAINT `artifact_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `section` (`sid`) ON DELETE CASCADE;

--
-- Constraints for table `hotspot`
--
ALTER TABLE `hotspot`
  ADD CONSTRAINT `hotspot_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `artifact` (`oid`) ON DELETE CASCADE,
  ADD CONSTRAINT `hotspot_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `panoview` (`pid`) ON DELETE CASCADE;

--
-- Constraints for table `panoview`
--
ALTER TABLE `panoview`
  ADD CONSTRAINT `panoview_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `section` (`sid`) ON DELETE CASCADE;

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `section_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `location` (`lid`) ON DELETE CASCADE;

--
-- Constraints for table `waypoint`
--
ALTER TABLE `waypoint`
  ADD CONSTRAINT `waypoint_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `panoview` (`pid`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
