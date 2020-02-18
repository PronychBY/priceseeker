CREATE DATABASE IF NOT EXISTS dbshopmonitor;
 
USE dbshopmonitor;

DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS price;

CREATE TABLE IF NOT EXISTS shops (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS goods (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
    
);

CREATE TABLE IF NOT EXISTS price (
    id INT AUTO_INCREMENT,
    idgoods INT,
    idshops INT,
    price decimal(15,2),
    date date,
    PRIMARY KEY (id),
    FOREIGN KEY (idshops) REFERENCES shops(id),
    FOREIGN KEY (idgoods) REFERENCES goods(id)
    
);


