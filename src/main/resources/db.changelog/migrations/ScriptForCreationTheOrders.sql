create table netology.orders (
id serial primary key,
date timestamp not null default current_timestamp,
customer_id int references netology.customers(id) on delete cascade,
product_name varchar(100) not null,
amount decimal(10,2) not null
);