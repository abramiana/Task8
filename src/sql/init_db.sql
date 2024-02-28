-- 1.0 Створення БД 
create database if not exists MegaSoft;	
use init_db;

-- 1.2 Створення таблиці worker 
create table if not exists worker (
ID int primary key auto_increment,
NAME varchar(1000) not null check(length(NAME) >= 2 and length(NAME) <1000),
BRITHDAY date check(extract(year from BRITHDAY) > 1900),
LEVEL enum('Trainee','Junior','Middle','Senior'),
SALARY int check(SALARY >=100 and SALARY <=100000)
);

-- 1.2 Cтворення таблиці client
create table if not exists client  (
ID int primary key auto_increment,
 NAME varchar(1000) not null check(length(NAME) >= 2  and length(NAME) <= 1000)
);

-- 1.3 Створення таблиці project
create table if not exists project (
ID int primary key auto_increment,
CLIENT_ID int,
START_DATE date,
FINISH_DATE date
);

-- 1.4 Cтворення таблиці project_worker 
create table if not exists project_worker (
PROJECT_ID int,
WORKER_ID int,
primary key (PROJECT_ID, WORKER_ID),
foreign key (PROJECT_ID) references project(ID),
foreign key (WORKER_ID) references worker(ID)
);