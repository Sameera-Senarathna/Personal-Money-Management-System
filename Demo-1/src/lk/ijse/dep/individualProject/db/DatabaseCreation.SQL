DROP TABLE Transaction ;
DROP TABLE Account ;
DROP TABLE SubCategory ;
DROP TABLE Category ;


CREATE TABLE Category (
  cId INT NOT NULL UNIQUE AUTO_INCREMENT ,
  cName VARCHAR (100) NOT NULL,
  cDescription VARCHAR (255) NOT NULL,
  cType VARCHAR(10) NOT NULL,
  PRIMARY KEY (cId)
);

INSERT INTO Category VALUES (0,'Initial','Initial Account Values','Income');
INSERT INTO Category VALUES (0,'Transfer','For Transfer Operation. Internal Use Only','Transfer');

CREATE TABLE SubCategory (
  scId INT NOT NULL UNIQUE AUTO_INCREMENT,
  scName VARCHAR (100) NOT NULL,
  scDescription VARCHAR (255) NOT NULL,
  cId INT NOT NULL,
  PRIMARY KEY (scId),
  FOREIGN KEY (cId) REFERENCES Category (cId)
);

INSERT INTO SubCategory VALUES (0,'Initial','Initial Account Value',1);
INSERT INTO SubCategory VALUES (0,'Transfer','Internal Use Only',2);

CREATE TABLE Account (
  aId INT NOT NULL UNIQUE AUTO_INCREMENT,
  aName VARCHAR (100) NOT NULL,
  aDescription VARCHAR (255) NOT NULL,
  PRIMARY KEY (aId)
);

CREATE TABLE Transaction (
  tId INT NOT NULL UNIQUE AUTO_INCREMENT,
  tDate DATE NOT NULL ,
  tNote VARCHAR(255),
  tvalue INT NOT NULL,
  aId INT NOT NULL,
  scId INT NOT NULL,
  PRIMARY KEY (tId),
  FOREIGN KEY (aId) REFERENCES Account (aId),
  FOREIGN KEY (scId) REFERENCES SubCategory (scId)
);



