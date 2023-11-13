CREATE SCHEMA IF NOT EXISTS PokemonApp;
Use PokemonApp;

DROP TABLE IF EXISTS WaterPokemon;
DROP TABLE IF EXISTS NormalPokemon;
DROP TABLE IF EXISTS GrassPokemon;
DROP TABLE IF EXISTS BattleHistory;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS PurchaseHistory;
DROP TABLE IF EXISTS Pokemons;
DROP TABLE IF EXISTS PokemonsHouse;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;

create table Users (
UserName varchar(255),
Password varchar(255) NOT NULL,
FirstName varchar(255) NOT NULL,
LastName varchar(255) NOT NULL,
CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName)
);

create table CreditCards(
CardNumber BIGINT,
Expiration date NOT NULL,
UserName varchar(255) NOT NULL,
CONSTRAINT pk_CreditCards_CardNumber PRIMARY KEY (CardNumber),
CONSTRAINT fk_CreditCards_UserName foreign key (UserName) 
    REFERENCES Users(UserName)
    ON UPDATE CASCADE ON DELETE CASCADE
);

create table PokemonsHouse(
HouseId int,
Description VARCHAR(1000),
CONSTRAINT pk_PokemonsHouse_HouseId PRIMARY KEY (HouseId)
);

create table Pokemons(
PokemonId int,
Name varchar(255) NOT NULL,
Attack INTEGER NOT NULL,
Defense INTEGER NOT NULL,
HouseId int default null,
CONSTRAINT pk_Pokemons_PokemonId PRIMARY KEY (PokemonId),
CONSTRAINT fk_Pokemon_HouseId foreign key (HouseId) 
    REFERENCES PokemonsHouse(HouseId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

create table PurchaseHistory(
PurchaseId int AUTO_INCREMENT,
Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
UserName VARCHAR(255)  default null,
PokemonId INTEGER default null,
Cost DOUBLE,
CONSTRAINT pk_PurchaseHistory_PurchaseId PRIMARY KEY (PurchaseId),
CONSTRAINT fk_PurchaseHistory_UserName foreign key (UserName) 
    REFERENCES Users(Username)
    ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_PurchaseHistory_PokemonId foreign key (PokemonId) 
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

create table  Recommendations(
RecommendationId int AUTO_INCREMENT,
UserName varchar(255) default null,
PokemonId INTEGER default null,
CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
CONSTRAINT fk_Recommendation_UserName foreign key (UserName) 
    REFERENCES Users(Username)
    ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_Recommendation_pokemonId foreign key (PokemonId) 
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

create table Reviews (
ReviewsId int AUTO_INCREMENT,
Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
Content VARCHAR(1024) NOT NULL,
Rating decimal(2,1) NOT NULL,
UserName varchar(255) ,
PokemonId int,
CONSTRAINT pk_Reviews_ReviewsId PRIMARY KEY (ReviewsId),
CONSTRAINT fk_Reviews_UserName foreign key (UserName) 
    REFERENCES Users(Username)
    ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_Reviews_PokemonId foreign key (PokemonId) 
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE SET NULL

);

create table BattleHistory (
BattleId int AUTO_INCREMENT,
Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
PokemonId1 int,
PokemonId2 int,
ResultWhether1wins2 boolean,
CONSTRAINT fk_BattleHistory_BattleId PRIMARY KEY (BattleId)
);

create table  GrassPokemon(
PokemonId int,
Against_Flying DOUBLE,
CONSTRAINT fk_GrassPokemon_PokemonId PRIMARY KEY (PokemonId),
CONSTRAINT pk_GrassPokemon_PokemonId foreign key (PokemonId)
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

create table  NormalPokemon(
PokemonId int,
Against_Ground DOUBLE,
CONSTRAINT fk_NormalPokemon_PokemonId PRIMARY KEY (PokemonId),
CONSTRAINT pk_NormalPokemon_PokemonId foreign key (PokemonId)
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

create table  WaterPokemon(
PokemonId int,
Against_Water DOUBLE,
CONSTRAINT fk_WaterPokemon_PokemonId PRIMARY KEY (PokemonId),
CONSTRAINT pk_WaterPokemon_PokemonId foreign key (PokemonId)
    REFERENCES Pokemons(PokemonId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


Use PokemonApp;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/BattleHistory.csv" INTO TABLE BattleHistory
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/PokemonHouse.csv" INTO TABLE PokemonsHouse
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/Pokemons.csv" INTO TABLE Pokemons
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/NormalPokemon.csv" INTO TABLE NormalPokemon
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/WaterPokemon.csv" INTO TABLE WaterPokemon
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/GrassPokemon.csv" INTO TABLE GrassPokemon
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/new_credit.csv" INTO TABLE CreditCards
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/new_PurchaseHistory.csv" INTO TABLE PurchaseHistory
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/new_Recommendation.csv" INTO TABLE Recommendations
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/Users.csv" INTO TABLE Users
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA LOCAL INFILE "/Users/mingyi/Desktop/CS5200/PM2_files/new_Reviews.csv" INTO TABLE Reviews
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

SELECT SUM(RowCount) AS TotalRows
FROM (
	SELECT COUNT(*) AS RowCount FROM PokemonsHouse
    UNION ALL
	SELECT COUNT(*) FROM BattleHistory
    UNION ALL
	SELECT COUNT(*) FROM Pokemons
    UNION ALL
	SELECT COUNT(*) FROM NormalPokemon
    UNION ALL
	SELECT COUNT(*) FROM GrassPokemon
    UNION ALL
	SELECT COUNT(*) FROM WaterPokemon
    UNION ALL
	SELECT COUNT(*) FROM CreditCards
    UNION ALL
	SELECT COUNT(*) FROM PurchaseHistory
    UNION ALL
	SELECT COUNT(*) FROM Recommendations
    UNION ALL
	SELECT COUNT(*) FROM Users
    UNION ALL
	SELECT COUNT(*) FROM Reviews
) AS SUBQUERY;