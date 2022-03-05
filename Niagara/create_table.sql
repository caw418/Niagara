CREATE SEQUENCE customer_sequence
START 105
INCREMENT 1;

CREATE TABLE customers (
  customer_id int DEFAULT nextval('customer_sequence'),
  first_name varchar(50),
  last_name varchar(50),
  product varchar(50)
);

INSERT INTO customers (customer_id, first_name, last_name, product)
VALUES 
(100, 'Bob', 'Wagner', 'Pitchfork'),
(101, 'Annie', 'Stevens', 'White Ankle Socks'),
(102, 'Annie', 'Smith', 'Eyeliner'),
(103, 'Rob', 'Duncan', 'Basketball'),
(104, 'Will', 'Thompson', 'Nerf Gun');