


CREATE TABLE IF NOT EXISTS tbl_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL
);

INSERT INTO tbl_users (username, password)
VALUES ('admin', 'admin123');

CREATE TABLE IF NOT EXISTS tbl_stock (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_stock_price DECIMAL(10, 2) GENERATED ALWAYS AS (quantity * price) STORED
);

INSERT INTO tbl_stock (item_name, quantity, price)
VALUES
('wheelbarrow', 10, 100.00),
('hammer', 15, 150.00),
('saw', 20, 200.00),
('nails', 30, 50.00),
('panga', 25, 75.00),
('cement', 50, 25.00),
('paint', 60, 30.00),
('iron sheets', 40, 90.00),
('paper', 10, 120.00),
('machette', 5, 200.00);

CREATE TABLE IF NOT EXISTS tbl_sales (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) GENERATED ALWAYS AS (price * quantity) STORED,
    sale_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tbl_sales (item_name, quantity, price)
VALUES
('wheelbarrow', 2, 100.00),
('nails', 3, 150.00),
('hammer', 1, 200.00),
('saw', 5, 50.00),
('panga', 4, 75.00),
('machette', 6, 25.00),
('paper', 7, 30.00),
('paint', 8, 90.00),
('iron sheets', 2, 120.00),
('cement', 1, 200.00);

CREATE TABLE IF NOT EXISTS tbl_payment (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tbl_payment (customer_name, amount)
VALUES
('mwas mwas', 200.00),
('mosi dev', 300.00),
('thank you', 400.00),
('frank mwangi', 500.00),
('Amos gitau', 600.00),
('flutter dart', 700.00),
('Spring java', 800.00),
('js react', 900.00),
('js vue', 1000.00),
('richard kimasmas', 1100.00);
