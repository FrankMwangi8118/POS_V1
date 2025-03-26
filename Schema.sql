
CREATE TABLE IF NOT EXISTS tbl_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS tbl_stock (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_stock_price DECIMAL(10,2) GENERATED ALWAYS AS(quantity * price) STORED,
    add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE IF NOT EXISTS tbl_sales (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) GENERATED ALWAYS AS (price * quantity) STORED,
    sale_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS tbl_payment (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO tbl_users (username, password)
VALUES
    ('admin', 'admin123');
INSERT INTO tbl_stock (item_name, quantity, price)
VALUES
    ('Laptop', 10, 1000.00),
    ('Smartphone', 15, 500.00),
    ('Headphones', 30, 100.00),
    ('Tablet', 20, 300.00),
    ('Smartwatch', 25, 150.00),
    ('Monitor', 12, 250.00),
    ('Keyboard', 50, 50.00),
    ('Mouse', 40, 30.00),
    ('Speakers', 35, 120.00),
    ('Charger', 60, 20.00);
INSERT INTO tbl_sales (item_name, quantity, price)
VALUES
    ('Laptop', 1, 1000.00),
    ('Smartphone', 2, 500.00),
    ('Headphones', 5, 100.00),
    ('Tablet', 1, 300.00),
    ('Smartwatch', 3, 150.00),
    ('Monitor', 2, 250.00),
    ('Keyboard', 10, 50.00),
    ('Mouse', 8, 30.00),
    ('Speakers', 4, 120.00),
    ('Charger', 6, 20.00);
INSERT INTO tbl_payment (customer_name, amount)
VALUES
    ('Alice Johnson', 2000.00),
    ('Bob Smith', 1500.00),
    ('Charlie Brown', 1200.00),
    ('David Wilson', 1800.00),
    ('Eve Davis', 2500.00),
    ('Frank Miller', 3000.00),
    ('Grace Lee', 2200.00),
    ('Hannah Clark', 1700.00),
    ('Ian Walker', 2100.00),
    ('Julia Adams', 1600.00);