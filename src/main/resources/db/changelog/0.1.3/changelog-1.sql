--liquibase formatted sql

create table demo
(
    id       bigint,
    username varchar(15)

);

insert into demo
values (1, 'testUser1'),
       (2, 'testUser2');