-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2018 at 07:22 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_beepbeep`
--

-- --------------------------------------------------------

--
-- Table structure for table `carrera`
--

CREATE TABLE `carrera` (
  `codCarrera` int(11) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codJugador1` int(11) NOT NULL,
  `tiempoJugador1` int(11) DEFAULT NULL,
  `codjugador2` int(11) DEFAULT NULL,
  `tiempoJugador2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carrera`
--

INSERT INTO `carrera` (`codCarrera`, `creation_date`, `codJugador1`, `tiempoJugador1`, `codjugador2`, `tiempoJugador2`) VALUES
(2, '2018-07-08 13:54:48', 3, 80, 1, 42),
(3, '2018-07-08 13:55:36', 4, 50, 1, 43),
(4, '2018-07-08 16:48:04', 1, 42, 4, 90),
(8, '2018-07-08 17:08:00', 1, 23, 4, 98),
(9, '2018-07-08 17:16:06', 1, 15, 4, 25);

-- --------------------------------------------------------

--
-- Table structure for table `inventario`
--

CREATE TABLE `inventario` (
  `codUsuario` int(11) NOT NULL,
  `codParte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventario`
--

INSERT INTO `inventario` (`codUsuario`, `codParte`) VALUES
(1, 4),
(1, 6),
(1, 7),
(1, 5),
(1, 9),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `parte`
--

CREATE TABLE `parte` (
  `codparte` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `precio` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `velocmodifier` int(11) NOT NULL,
  `accelmodifier` int(11) NOT NULL,
  `manmodifier` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parte`
--

INSERT INTO `parte` (`codparte`, `nombre`, `descripcion`, `precio`, `categoria`, `velocmodifier`, `accelmodifier`, `manmodifier`) VALUES
(1, 'Motor Estandar', 'Motor basico de un automovil', 0, 1, 0, 0, 0),
(2, 'Llantas Estandar', 'Llantas basicas de un automovil', 0, 2, 0, 0, 0),
(3, 'Aromatizante', 'Accesorio basico de un automovil', 0, 3, 0, 0, 0),
(4, 'Llantas Zheng', 'Llantas chinitas, no son de la mejor calidad pero son mejor que las llantas de principiante', 100, 2, 1, 0, 0),
(5, 'Motor 2.0', 'Motor con una potencia superior al estandar', 800, 1, 10, 3, 1),
(6, 'Cola de Pato', 'Accesorio que mejora la maniobrabilidad del vehiculo', 150, 3, 0, 0, 1),
(7, 'Motor V8', 'Bestia de motor! Necesito decir mas?', 2000, 1, 25, 8, -1),
(8, 'Nitro', 'Le da potencia a tu vehiculo, sacrificando maniobrabilidad', 1000, 3, 2, 5, -2),
(9, 'Llantas Tsuchiya', 'Marca Japonesa, buena pal drifting', 200, 2, 1, 0, 3),
(10, 'Llantas Parker', 'Llantas gringas, buenas para acelerar', 200, 2, 0, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE `rol` (
  `codRol` int(11) NOT NULL,
  `nombreRol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`codRol`, `nombreRol`) VALUES
(1, 'Administrador'),
(2, 'Usuario'),
(3, 'Otro Rol');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `codUsuario` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `codrol` int(11) NOT NULL DEFAULT '2',
  `codmotor` int(11) NOT NULL DEFAULT '1',
  `codllantas` int(11) NOT NULL DEFAULT '2',
  `codaccesorio` int(11) NOT NULL DEFAULT '3',
  `maxvelocidad` int(11) NOT NULL DEFAULT '0',
  `maniobrabilidad` int(11) NOT NULL DEFAULT '0',
  `aceleracion` int(11) NOT NULL DEFAULT '0',
  `dinero` int(11) NOT NULL DEFAULT '0',
  `puntos` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`codUsuario`, `username`, `password`, `codrol`, `codmotor`, `codllantas`, `codaccesorio`, `maxvelocidad`, `maniobrabilidad`, `aceleracion`, `dinero`, `puntos`) VALUES
(1, 'AMIRANDA', 'AMIRANDA', 2, 7, 9, 6, 0, 0, 0, 1850, 300),
(3, 'METEORO', 'METEORO', 2, 1, 2, 3, 0, 0, 0, 0, 0),
(4, 'TROOPER', 'TROOPER', 2, 1, 4, 3, 0, 0, 0, 100, 500),
(5, 'TurboMan', 'TurboMan', 2, 1, 2, 3, 0, 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`codCarrera`);

--
-- Indexes for table `parte`
--
ALTER TABLE `parte`
  ADD PRIMARY KEY (`codparte`);

--
-- Indexes for table `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`codRol`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carrera`
--
ALTER TABLE `carrera`
  MODIFY `codCarrera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `parte`
--
ALTER TABLE `parte`
  MODIFY `codparte` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rol`
--
ALTER TABLE `rol`
  MODIFY `codRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
