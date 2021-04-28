create table person (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp,
    cart_id                 bigint references cart (id)
);

create table role (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table persons_roles (
    person_id                 bigint not null references person (id),
    role_id                 bigint not null references role (id),
    primary key (person_id, role_id)
);

create table product (
    id                      bigserial primary key,
    name                   varchar(255),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table category (
    id                      bigserial primary key,
    title                   varchar(255),
    description             varchar(5000)
);

create table products_categories (
    product_id                bigint,
    category_id               bigint,
    foreign key (product_id)  references product (id),
    foreign key (category_id) references category (id)
);

create table orders (
    id                      bigserial primary key,
    person_id               bigint references person (id),
    price                   int,
    address                 varchar(255),
    status                  int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table order_items (
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references product (id),
    name                    varchar(255),
    quantity                int,
    price_per_product       int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table cart (
                       id                      bigserial primary key,
                       person_id               bigint references person (id),
                       price                   int,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

create table cart_items (
                             id                      bigserial primary key,
                             cart_id                 bigint references cart (id),
                             product_id              bigint references product (id),
                             name                    varchar(255),
                             quantity                int,
                             price_per_product       int,
                             price                   int,
                             created_at              timestamp default current_timestamp,
                             updated_at              timestamp default current_timestamp
);