-- Create Users Table
CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Places Table
CREATE TABLE Places (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(id)
);

-- Create Items Table
CREATE TABLE Items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- Create Junction Table for Many-to-Many Relationship (Items and Places)
CREATE TABLE PlaceItems (
    id INT PRIMARY KEY AUTO_INCREMENT,
    placeID INT,
    itemID INT,
    FOREIGN KEY (placeID) REFERENCES Places(id),
    FOREIGN KEY (itemID) REFERENCES Items(id)
);
