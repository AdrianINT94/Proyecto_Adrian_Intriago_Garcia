SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `profesores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `ciclo` varchar(255) DEFAULT NULL,
  `curso` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tutor_empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `empresa_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_empresa_tutor` (`empresa_id`),
  CONSTRAINT `FK_empresa_tutor` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `alumnos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `ciclo` varchar(255) DEFAULT NULL,
  `curso` int(11) NOT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `tutor_docente` varchar(255) DEFAULT NULL,
  `empresa_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_empresa_alumno` (`empresa_id`),
  CONSTRAINT `FK_empresa_alumno` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `empresa` (`id`, `nombre`, `direccion`, `telefono`, `responsable`) VALUES
(1, 'Futuver', 'Calle Velazquez', '652458135', 'Adrian');

INSERT INTO `profesores` (`id`, `nombre`, `email`, `contrasena`, `ciclo`, `curso`) VALUES
(1, 'Carlos García', 'carlos@educastur.es', NULL, 'DAW', 2),
(2, 'Laura Martínez', 'laura@educastur.es', NULL, 'DAW', 2);

INSERT INTO `tutor_empresa` (`id`, `nombre`, `email`, `contrasena`, `empresa_id`) VALUES
(1, 'Adrian Jefe', 'adrian@futuver.com', NULL, 1),
(2, 'Sara RRHH', 'sara@futuver.com', NULL, 1);

INSERT INTO `alumnos` (`id`, `nombre`, `email`, `contrasena`, `ciclo`, `curso`, `responsable`, `tutor_docente`, `empresa_id`) VALUES
(1, 'Daniel', 'wkj62135@educastur.es', NULL, 'DAW', 2, 'Adrian Jefe', 'Carlos García', 1);

COMMIT;
