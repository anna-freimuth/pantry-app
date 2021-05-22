
CREATE TABLE IF NOT EXISTS item_types
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type_name varchar(200) NOT NULL
);


CREATE TABLE IF NOT EXISTS item
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type_id BIGINT NOT NULL,
    FOREIGN KEY (type_id) REFERENCES item_types (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    quantity INT NOT NULL ,
    add_date DATE,
    expiring_date DATE
);
