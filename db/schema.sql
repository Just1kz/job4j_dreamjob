CREATE TABLE if not exists post(
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT
);
CREATE TABLE if not exists candidate(
                                   id SERIAL PRIMARY KEY,
                                   name TEXT
);

insert into post(name, description) VALUES ('Junior Java Job', 'test1');
insert into post(name, description) VALUES ('Middle Java Job', 'test1');
insert into post(name, description) VALUES ('Senior Java Job', 'test1');

insert into candidate(name) VALUES ('Junior Java');
insert into candidate(name) VALUES ('Middle Java');
insert into candidate(name) VALUES ('Senior Java');