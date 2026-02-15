CREATE DATABASE IF NOT EXISTS ip_labs;
USE ip_labs;

CREATE TABLE IF NOT EXISTS admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

INSERT INTO admin (username, password) VALUES ('admin', 'pass123');

CREATE TABLE IF NOT EXISTS complaints (
    complaint_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    category VARCHAR(100),
    description TEXT,
    status VARCHAR(20) DEFAULT 'Pending',
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
