create database FastFoodCentral_database;


create table users(
user_id int PRIMARY KEY AUTO_INCREMENT,
username varchar(30),
password varchar(30),
UNIQUE KEY unique_username (username)
);



create table food(
order_id int PRIMARY KEY AUTO_INCREMENT,
item_name int(11),
price double(4,2)
);



create table delivery(
user_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(30),
phone_number varchar(30),
address varchar(200),
notes varchar(50)
);
