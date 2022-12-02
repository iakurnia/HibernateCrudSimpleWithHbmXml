-- create database
CREATE DATABASE project_hibernate;

-- create table
CREATE TABLE IF NOT EXISTS `person` (
    id varchar(20) NOT NULL,
    name varchar(20) NOT NULL,
    address varchar(20) NOT NULL,
    gender varchar(20) NOT NULL,
    PRIMARY KEY (id)
    )
