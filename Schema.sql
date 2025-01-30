
CREATE TABLE IF NOT EXISTS tbl_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS tbl_stock (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
    total_stock_price DECIMAL(10,2) GENERATED ALWAYS AS(quantity * price) STORED
);



CREATE TABLE IF NOT EXISTS tbl_sales (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2) GENERATED ALWAYS AS (price * quantity) STORED,
    sale_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
);


CREATE TABLE IF NOT EXISTS tbl_payment (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
