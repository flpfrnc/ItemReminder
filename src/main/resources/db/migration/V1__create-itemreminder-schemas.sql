-- Create users Table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'USER' CHECK (role IN ('ADMIN', 'USER'))
);

-- Create places Table
CREATE TABLE places (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Create items Table
CREATE TABLE items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- Create Junction Table for Many-to-Many Relationship (Items and Places)
CREATE TABLE place_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    place_id INT,
    item_id INT,
    FOREIGN KEY (place_id) REFERENCES places(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);