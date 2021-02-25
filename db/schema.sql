CREATE TABLE if not exists users(
                                   idU SERIAL PRIMARY KEY,
                                   name text,
                                   email text,
                                   password text
);

CREATE TABLE if not exists post(
                                    id SERIAL PRIMARY KEY,
                                    name text,
                                    description text,
                                    createDate text
);
CREATE TABLE if not exists photo (
                                     idP SERIAL PRIMARY KEY,
                                     title text
);

CREATE TABLE if not exists city (
                                     idCity SERIAL PRIMARY KEY,
                                     town text unique NOT NULL
);

CREATE TABLE if not exists candidate(
                                   idCan SERIAL PRIMARY KEY,
                                   name text,
                                   town_name text references city(town),
                                   resume text,
                                   photo_id int references photo(idP),
                                   createDate text
);

insert into city(town) VALUES ('Novosibirsk');
insert into city(town) VALUES ('Moscow');
insert into city(town) VALUES ('Saint-Petersburg');
insert into city(town) VALUES ('Kemerovo');

insert into post(name, description, createDate) VALUES ('Junior Java Job', 'superCodeMan!', '15:05:03  01.01.2021');
insert into post(name, description, createDate) VALUES ('Middle Java Job', 'superCodeMan!', '15:05:03  01.01.2021');
insert into post(name, description, createDate) VALUES ('Senior Java Job', 'superCodeMan!', '15:05:03  01.01.2021');

insert into candidate(name, town_name, resume, photo_id, createDate) VALUES ('Junior Java', 'Novosibirsk', 'bla-bla', 2, '15:05:03  01.01.2021');
insert into candidate(name, town_name, resume, photo_id, createDate) VALUES ('Middle Java', 'Moscow', 'bla-bla', 2, '15:05:03  01.01.2021');
insert into candidate(name, town_name, resume, photo_id, createDate) VALUES ('Senior Java', 'Saint-Petersburg', 'bla-bla', 2, '15:05:03  01.01.2021');

SELECT * FROM candidate t1 left join photo t2 on t2.idP = t1.photo_id;

update candidate set name = 'La-La-Da', photo_id = 2 where idCan = 6;
SELECT * FROM candidate t1
    left join photo t2 on t2.idP = t1.photo_id
    left join city t3 on t3.town = t1.town_name
where t1.idCan = 1;


INSERT INTO candidate(name, photo_id) VALUES ('Anton', 1);