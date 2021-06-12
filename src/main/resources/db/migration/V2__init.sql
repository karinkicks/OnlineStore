insert into role (id, name)
values
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

insert into person (id, username, password, email)
values
(1,'admin', '$2y$12$awPdDcbMhQiDB5WkdgTqdOOOcLn86CJVPRTLpo9i6e8SXyOyMeCAS', 'admin@gmail.com'),
(2,'user', '$2y$12$CFksEoJF.Y52u503m4azYOFOL10Rlh4DYhH7OdcYjD6lVJLSeDlLu', 'user@gmail.com');

insert into persons_roles (person_id, role_id)
values
(1, 1),
(2, 2);

insert into product (name, price)
values
('Banana', 50),
('Bread', 40),
('Meat', 500);


insert into category
(title, description) values
('food', 'some food products'),
('fruits', 'some fruit'),
('vegetables', 'some vegetables'),
('electronics', 'some electronics'),
('non-food', 'all non-food products');