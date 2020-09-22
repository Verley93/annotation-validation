DROP TABLE IF EXISTS food;

CREATE TABLE food (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(25),
    category VARCHAR(25),
    quantity INT,
    refrigerated BOOLEAN,
    PRIMARY KEY (id)
);