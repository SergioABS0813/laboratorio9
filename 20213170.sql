-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: lab_9
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `idcurso` int NOT NULL,
  `codigo` varchar(6) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idfacultad` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `fk_curso_facultad1_idx` (`idfacultad`),
  CONSTRAINT `fk_curso_facultad1` FOREIGN KEY (`idfacultad`) REFERENCES `facultad` (`idfacultad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'MAT178','Series y Transformadas',1,'2020-11-14 11:44:47','2023-11-25 08:43:05'),(2,'TEL1','PREM',2,'2023-11-25 10:11:17','2023-11-25 10:11:17'),(3,'IEE2','Control',1,'2020-11-14 11:44:47','2023-11-25 08:43:00'),(4,'TEL2','Ingenieria Web',2,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(5,'TEL3','Redes',2,'2023-11-25 10:12:09','2023-11-26 02:43:28'),(6,'TEL4','TCE',2,'2023-11-25 18:25:26','2023-11-25 18:25:26'),(7,'IEE3','Teoría de Comunicaciones 1',1,'2023-11-25 19:24:15','2023-11-25 23:44:57'),(8,'IEE25','Teoria de Comunicaciones 2',1,'2023-11-26 03:11:47','2023-11-26 03:11:56');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_has_docente`
--

DROP TABLE IF EXISTS `curso_has_docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_has_docente` (
  `idcurso` int NOT NULL,
  `iddocente` int NOT NULL,
  PRIMARY KEY (`idcurso`,`iddocente`),
  KEY `fk_curso_has_usuario_usuario1_idx` (`iddocente`),
  KEY `fk_curso_has_usuario_curso1_idx` (`idcurso`),
  CONSTRAINT `fk_curso_has_usuario_curso1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`),
  CONSTRAINT `fk_curso_has_usuario_usuario1` FOREIGN KEY (`iddocente`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_has_docente`
--

LOCK TABLES `curso_has_docente` WRITE;
/*!40000 ALTER TABLE `curso_has_docente` DISABLE KEYS */;
INSERT INTO `curso_has_docente` VALUES (1,12),(2,13),(3,14),(4,15),(5,16),(7,17),(8,18),(6,19);
/*!40000 ALTER TABLE `curso_has_docente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluaciones`
--

DROP TABLE IF EXISTS `evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluaciones` (
  `idevaluaciones` int NOT NULL,
  `nombre_estudiantes` varchar(45) NOT NULL,
  `codigo_estudiantes` varchar(45) NOT NULL,
  `correo_estudiantes` varchar(45) NOT NULL,
  `nota` int NOT NULL,
  `idcurso` int NOT NULL,
  `idsemestre` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime NOT NULL,
  PRIMARY KEY (`idevaluaciones`),
  KEY `fk_evaluaciones_curso1_idx` (`idcurso`),
  KEY `fk_evaluaciones_semestre1_idx` (`idsemestre`),
  CONSTRAINT `fk_evaluaciones_curso1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`),
  CONSTRAINT `fk_evaluaciones_semestre1` FOREIGN KEY (`idsemestre`) REFERENCES `semestre` (`idsemestre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluaciones`
--

LOCK TABLES `evaluaciones` WRITE;
/*!40000 ALTER TABLE `evaluaciones` DISABLE KEYS */;
INSERT INTO `evaluaciones` VALUES (1,'Sergio Bustamante','20213170','sergio.bustamante@gmail.com',15,1,232,'2020-11-14 11:44:47','2020-11-14 11:44:47'),(2,'SER','234245','holitas@gmail.com',16,2,232,'2023-11-25 20:24:11','2023-11-25 20:24:11'),(3,'holaa','2342022','hola@gmail.com',18,2,232,'2023-11-25 19:00:19','2023-11-25 19:00:19'),(4,'Alex Segovia','20210002','alex.segovia@gmail.com',16,1,231,'2020-11-14 11:44:47','2020-11-14 11:44:47'),(5,'Josh Yauri','20210003','josh.yauri@gmail.com',18,1,231,'2020-11-14 11:44:47','2020-11-14 11:44:47'),(6,'holita','23420888','hola@gmail.com',19,2,232,'2023-11-25 19:01:11','2023-11-25 19:01:11'),(7,'David Escobedo','20213156','david.escobedo@gmail.com',17,1,231,'2023-11-25 19:01:11','2023-11-25 19:01:11'),(8,'Ddee','202131666','david.esc@gmail.com',14,1,222,'2023-11-25 19:01:11','2023-11-25 19:01:11'),(9,'SERGITO','22222','holO@gmail.com',19,2,231,'2023-11-25 20:24:59','2023-11-25 20:24:59'),(10,'Jorge Sarutobi','20213456','jorge@gmail.com',20,2,231,'2023-11-25 20:25:46','2023-11-25 20:25:46'),(11,'Samuel Ayala','20167986','samu@gmail.com',20,2,231,'2023-11-25 20:26:09','2023-11-25 20:26:09'),(12,'Manuel Yarleque','20003245','myarle@gmail.com',19,2,230,'2023-11-25 20:26:09','2023-11-25 23:54:48'),(13,'Alvaro Burga','20001356','alvaro@gmail.com',15,2,230,'2023-11-25 20:29:34','2023-11-25 20:44:44'),(14,'Epifanio','20034567','alvarito@gmail.com',17,2,230,'2023-11-25 20:30:11','2023-11-25 23:38:19'),(15,'Diego Gonzales','20198756','diego.gonzales@gmail.com',14,2,230,'2023-11-26 02:07:01','2023-11-26 02:07:01'),(16,'Sebastian Bustamante','20231308','sebas.bustamante@gmail.com',20,3,230,'2023-11-26 03:20:45','2023-11-26 03:20:45'),(17,'Xiomara Vega','20210804','xiomara.vega@gmail.com',20,3,230,'2023-11-26 03:22:49','2023-11-26 03:22:49'),(18,'Edgar Espinoza','20197865','edgar.espinoza@gmail.com',19,3,231,'2023-11-26 03:25:41','2023-11-26 03:25:41'),(19,'Andre Salcedo','20213170','andre.salcedo@gmail.com',18,3,231,'2023-11-26 03:26:13','2023-11-26 03:26:13'),(20,'Micaela Vegas','20196543','mica.vegas@gmail.com',17,3,232,'2023-11-26 03:27:12','2023-11-26 03:27:12'),(21,'Alonzo Guevara','20187654','alonzo.guevar@gmail.com',20,4,232,'2023-11-26 03:29:28','2023-11-26 03:29:28'),(22,'Carlos Minaya','20236578','carlos.minay@gmail.com',18,4,232,'2023-11-26 03:29:56','2023-11-26 03:29:56'),(23,'Julio Carrion','20167534','julio.carrion@gmail.com',20,4,230,'2023-11-26 03:32:45','2023-11-26 03:32:45'),(24,'Pedro Bustamante','20176548','pedro.bustamante@gmail.com',16,4,230,'2023-11-26 03:33:19','2023-11-26 03:33:19');
/*!40000 ALTER TABLE `evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultad`
--

DROP TABLE IF EXISTS `facultad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facultad` (
  `idfacultad` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `iduniversidad` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime NOT NULL,
  PRIMARY KEY (`idfacultad`),
  KEY `fk_facultad_universidad_idx` (`iduniversidad`),
  CONSTRAINT `fk_facultad_universidad` FOREIGN KEY (`iduniversidad`) REFERENCES `universidad` (`iduniversidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultad`
--

LOCK TABLES `facultad` WRITE;
/*!40000 ALTER TABLE `facultad` DISABLE KEYS */;
INSERT INTO `facultad` VALUES (1,'Electronica',1,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(2,'Telecomunicaciones',1,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(3,'Electrica',2,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(4,'Mecatronica',2,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(5,'Medicina',3,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(6,'Artes',3,'2020-11-14 11:44:47','2021-01-01 11:44:47');
/*!40000 ALTER TABLE `facultad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultad_has_decano`
--

DROP TABLE IF EXISTS `facultad_has_decano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facultad_has_decano` (
  `idfacultad` int NOT NULL,
  `iddecano` int NOT NULL,
  PRIMARY KEY (`idfacultad`,`iddecano`),
  KEY `fk_facultad_has_usuario_usuario1_idx` (`iddecano`),
  KEY `fk_facultad_has_usuario_facultad1_idx` (`idfacultad`),
  CONSTRAINT `fk_facultad_has_usuario_facultad1` FOREIGN KEY (`idfacultad`) REFERENCES `facultad` (`idfacultad`),
  CONSTRAINT `fk_facultad_has_usuario_usuario1` FOREIGN KEY (`iddecano`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultad_has_decano`
--

LOCK TABLES `facultad_has_decano` WRITE;
/*!40000 ALTER TABLE `facultad_has_decano` DISABLE KEYS */;
INSERT INTO `facultad_has_decano` VALUES (1,6),(2,7),(3,8),(4,9),(5,10),(6,11);
/*!40000 ALTER TABLE `facultad_has_decano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'administrador'),(2,'rector'),(3,'decano'),(4,'docente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semestre` (
  `idsemestre` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idadmistrador` int NOT NULL,
  `habilitado` tinyint NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime NOT NULL,
  PRIMARY KEY (`idsemestre`,`idadmistrador`),
  KEY `fk_semestre_usuario1_idx` (`idadmistrador`),
  CONSTRAINT `fk_semestre_usuario1` FOREIGN KEY (`idadmistrador`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semestre`
--

LOCK TABLES `semestre` WRITE;
/*!40000 ALTER TABLE `semestre` DISABLE KEYS */;
INSERT INTO `semestre` VALUES (221,'2022-1',1,0,'2022-01-14 11:44:47','2022-02-14 11:44:47'),(222,'2022-2',1,0,'2022-07-14 11:44:47','2022-08-14 11:44:47'),(230,'2023-0',1,1,'2022-11-14 11:44:47','2022-12-14 11:44:47'),(231,'2023-1',1,0,'2023-01-14 11:44:47','2023-02-14 11:44:47'),(232,'2023-2',1,0,'2023-07-14 11:44:47','2023-08-14 11:44:47'),(240,'2024-0',1,0,'2023-11-14 11:44:47','2023-12-14 11:44:47');
/*!40000 ALTER TABLE `semestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidad`
--

DROP TABLE IF EXISTS `universidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `universidad` (
  `iduniversidad` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `logo_url` varchar(45) DEFAULT NULL,
  `idadministrador` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime NOT NULL,
  PRIMARY KEY (`iduniversidad`),
  KEY `fk_universidad_usuario1_idx` (`idadministrador`),
  CONSTRAINT `fk_universidad_usuario1` FOREIGN KEY (`idadministrador`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad`
--

LOCK TABLES `universidad` WRITE;
/*!40000 ALTER TABLE `universidad` DISABLE KEYS */;
INSERT INTO `universidad` VALUES (1,'PUCP',NULL,1,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(2,'UNI',NULL,1,'2020-11-14 11:44:47','2021-01-01 11:44:47'),(3,'UNMSM',NULL,1,'2020-11-14 11:44:47','2021-01-01 11:44:47');
/*!40000 ALTER TABLE `universidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidad_has_rector`
--

DROP TABLE IF EXISTS `universidad_has_rector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `universidad_has_rector` (
  `iduniversidad` int NOT NULL,
  `idrector` int NOT NULL,
  PRIMARY KEY (`iduniversidad`,`idrector`),
  KEY `fk_universidad_has_usuario_usuario1_idx` (`idrector`),
  KEY `fk_universidad_has_usuario_universidad1_idx` (`iduniversidad`),
  CONSTRAINT `fk_universidad_has_usuario_universidad1` FOREIGN KEY (`iduniversidad`) REFERENCES `universidad` (`iduniversidad`),
  CONSTRAINT `fk_universidad_has_usuario_usuario1` FOREIGN KEY (`idrector`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad_has_rector`
--

LOCK TABLES `universidad_has_rector` WRITE;
/*!40000 ALTER TABLE `universidad_has_rector` DISABLE KEYS */;
INSERT INTO `universidad_has_rector` VALUES (1,2),(2,3),(3,4);
/*!40000 ALTER TABLE `universidad_has_rector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `idrol` int NOT NULL,
  `ultimo_ingreso` datetime DEFAULT NULL,
  `cantidad_ingresos` int NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `fecha_edicion` datetime DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_rol1_idx` (`idrol`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin@gmail.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',1,'2023-01-14 11:44:47',10,'2020-11-14 11:44:47','2023-10-14 11:44:47'),(2,'rector1','rector1@gmail.com','ce722f33142d690bb972ad379fe7f6391900026941ae9cbebaec976fbd8d365b',2,'2023-02-14 11:44:47',13,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(3,'rector2','rector2@gmail.com','ce722f33142d690bb972ad379fe7f6391900026941ae9cbebaec976fbd8d365b',2,'2023-02-14 11:44:47',11,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(4,'rector3','rector3@gmail.com','ce722f33142d690bb972ad379fe7f6391900026941ae9cbebaec976fbd8d365b',2,'2023-02-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(5,'rector4','rector4@gmail.com','ce722f33142d690bb972ad379fe7f6391900026941ae9cbebaec976fbd8d365b',2,'2023-02-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(6,'Francisco Villacorta','decano1pucp@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-11-26 03:18:12',39,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(7,'Alonzo Cucho','decano2pucp@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-11-26 02:51:03',12,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(8,'Gabriel Talledo','decano3uni@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-02-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(9,'Barack Obama','decano4uni@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-02-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(10,'Carlos Blancas','decano5unmsm@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-04-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(11,'Brandon Tacuri','decano6unmsm@gmail.com','bb49d93a07afa73df0d7b84ab5d978aa0fc0de2e152aa3a467b7c05dd30ea3a4',3,'2023-04-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(12,'Gumercindo Bartra','docente1@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 03:28:48',8,'2022-11-14 11:44:47','2023-11-25 14:24:17'),(13,'Norberto Chau','docente2@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 03:19:18',63,'2022-11-14 11:44:47','2023-11-25 10:07:58'),(14,'Cesar Santivañez','docente3@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 03:28:43',10,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(15,'Stuardo Lucho','docente4@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 03:53:49',14,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(16,'Stefano Romero','docente5@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-04-14 11:44:47',7,'2022-11-14 11:44:47','2023-10-14 11:44:47'),(17,'decano6','decano6@gmail.com','ef395bbd0a8c1af1a97b47014e95c6247f47666c4f4f769c03efe5ffbc4debd7',4,'2023-11-25 14:35:13',0,'2023-11-25 14:35:13','2023-11-26 02:10:58'),(18,'docente18','docente18@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-25 14:42:09',0,'2023-11-25 14:42:09','2023-11-25 14:42:28'),(19,'docente19','docente19@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-25 18:23:26',1,'2023-11-25 18:22:11','2023-11-25 18:23:09'),(20,'docente20','docente20@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 02:46:11',0,'2023-11-26 02:46:11','2023-11-26 02:46:11'),(21,'docente21','docente21@gmail,com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 02:46:37',0,'2023-11-26 02:46:37','2023-11-26 02:46:37'),(22,'docente22','docente22@gmail,com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 02:47:23',0,'2023-11-26 02:47:23','2023-11-26 02:47:23'),(23,'docente23','docente23@gmail,com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 02:47:36',0,'2023-11-26 02:47:36','2023-11-26 02:47:36'),(24,'docente30','docente30@gmail.com','a53dfa154cd75757b02f8d3a56959a5c85fc276d670758ea7c550f85f95159e5',4,'2023-11-26 03:11:09',0,'2023-11-26 03:11:09','2023-11-26 03:11:09');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26  4:04:57
