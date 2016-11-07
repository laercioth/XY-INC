
---Crie um schema com o seguinte nome XY-INC

---E execute esse script para criação da tabela

DROP TABLE IF EXISTS `POI`;

CREATE TABLE `POI` (
  `ID` decimal(10,0) NOT NULL,
  `NAME_PLACE` varchar(80) DEFAULT NULL,
  `X_COORDINATE` decimal(25,0) DEFAULT NULL,
  `Y_COORDINATE` decimal(25,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


