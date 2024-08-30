create table netology.CUSTOMERS (
id serial primary key,
name varchar(50) not null,
surname varchar(50) not null,
age int check (age >= 0),
phone_number varchar(12) not null
)

