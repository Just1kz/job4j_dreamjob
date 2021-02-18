CREATE TABLE if not exists post(
                      id SERIAL PRIMARY KEY,
                      name text,
                      description text
);
CREATE TABLE if not exists photo (
                                     idP SERIAL PRIMARY KEY,
                                     title text
);

CREATE TABLE if not exists candidate(
                                   idC SERIAL PRIMARY KEY,
                                   name text,
                                   photo_id int references photo(idP)
);

insert into post(name, description) VALUES ('Junior Java Job', 'test1');
insert into post(name, description) VALUES ('Middle Java Job', 'test1');
insert into post(name, description) VALUES ('Senior Java Job', 'test1');

insert into photo(title) VALUES ('123');

insert into candidate(name, photo_id) VALUES ('Junior Java', 1);
insert into candidate(name, photo_id) VALUES ('Middle Java', 1);
insert into candidate(name, photo_id) VALUES ('Senior Java', 1);

SELECT * FROM candidate t1 left join photo t2 on t2.idP = t1.photo_id;

INSERT INTO candidate(name, photo_id) VALUES ('Anton', 1);