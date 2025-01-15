CREATE DATABASE VoxPopuliDB;
USE VoxPopuliDB;

CREATE TABLE Users (
	userId INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(60),
	lastName VARCHAR(60),
	email VARCHAR(60) UNIQUE,
	username VARCHAR(60) UNIQUE,
	password VARCHAR(60)
);

CREATE TABLE Posts (
	postId INT PRIMARY KEY AUTO_INCREMENT,
	tittle VARCHAR(50),
	content VARCHAR(500),
	userId INT,
	createdDate DATETIME,
    FOREIGN KEY (userId) REFERENCES Users(userId)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Comments (
	commentId INT PRIMARY KEY AUTO_INCREMENT,
	content VARCHAR(500),
	userId INT,
    postId INT,
    createdDate DATETIME,
    FOREIGN KEY (userId) REFERENCES Users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (postId) REFERENCES Posts(postId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
