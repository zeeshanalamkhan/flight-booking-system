drop sequence hibernate_sequence;
create sequence hibernate_sequence start with 38 increment by 1;
INSERT INTO aircraft VALUES (3,'Boeing','Boeing 307',100);
INSERT INTO aircraft VALUES (4,'Boeing','Boeing 737',300);
INSERT INTO aircraft VALUES (16,'Airbus','Airbus A220',200);
INSERT INTO aircraft VALUES (20,'Airbus','Airbus A340',340);
INSERT INTO aircraft VALUES (23,'Airbus','Airbus A440',400);
INSERT INTO aircraft VALUES (36,'Boeing','Boeing 201',180);

INSERT INTO airport VALUES (1,'DAL','Dallas Love Field','Dallas','United States','Dallas');
INSERT INTO airport VALUES (2,'DCG','Dubai Creek SPB','Dubai','United Arab Emirates','Dubai');
INSERT INTO airport VALUES (15,'CID','Cedar Rapid Airport','IOWA','United States','Iowa');
INSERT INTO airport VALUES (19,'CHI','Chicago Airport','Chicago','United States','Illinois');
INSERT INTO airport VALUES (31,'CLN','California Airport','California','United States','California');
INSERT INTO airport VALUES (35,'TEX','Texas Airport','Texas','United States','Texas');

INSERT INTO flight VALUES (5,to_timestamp('2019-08-01','YYYY-MM-DD'),'10:30',to_timestamp('2019-08-01', 'YYYY-MM-DD'),'08:00',1200,'dallas-dubai100',3,1,2);
INSERT INTO flight VALUES (6,to_timestamp('2019-08-02', 'YYYY-MM-DD'),'00:20',to_timestamp('2019-08-02', 'YYYY-MM-DD'),'10:20',1600,'dubai-dalas110',3,2,1);
INSERT INTO flight VALUES (25,to_timestamp('2019-08-08', 'YYYY-MM-DD'),'09:00',to_timestamp('2019-08-08','YYYY-MM-DD'),'07:00',250,'chi-cedar01',3,19,15);
INSERT INTO flight VALUES (26,to_timestamp('2019-08-03','YYYY-MM-DD'),'16:00',to_timestamp('2019-08-03','YYYY-MM-DD'),'14:00',180,'ced-chi05',16,15,19);
INSERT INTO flight VALUES (33,to_timestamp('2019-08-16','YYYY-MM-DD'),'14:00',to_timestamp('2019-08-16','YYYY-MM-DD'),'00:00',500,'dal - cal',4,1,31);
INSERT INTO flight VALUES (37,to_timestamp('2019-08-31','YYYY-MM-DD'),'14:40',to_timestamp('2019-08-31','YYYY-MM-DD'),'08:00',650,'IA-TEX101',36,15,35);


INSERT INTO passenger VALUES (8,'1000 N 4th Street, MUM','john@mum.edu','John','Doe','78998787','6414513308',5);
INSERT INTO passenger VALUES (24,'1000 N 4th Street, MUM, MR#100','anna@gmail.com','Anna','Smith','02538467','6414513308',5);
INSERT INTO passenger VALUES (29,'1000 N 4th Street, MUM, MR#15','chibusi@gmail.com','Chibusi','Kelvin','45687997','6414513308',6);
INSERT INTO passenger VALUES (30,'1000 N 4th Street, MUM, MR#48','alimohammad.khan2008@gmail.com','Ali Mohammed','Khan','45687997','6414513308',5);
INSERT INTO passenger VALUES (34,'1000 N 4th Street, Maharishi University of Management','churn@mum.com','Chinedu','Urbanus','A20193885','23456789',33);



INSERT INTO roles VALUES (1,'ROLE_ADMIN');
INSERT INTO roles VALUES(2,'ROLE_AGENT');

INSERT INTO user1 VALUES (1,'john@gmail.com','John','Doe',NULL,'$2a$10$dRM33.Fy7SYDraG5vMagXOgIhsB6Tl40VI9pwMlNhB4yfLaZpQj.m','john');
INSERT INTO user1 VALUES (2,'mike@gmail.com','Mike','Jacson',NULL,'$2a$10$vukSIdxmmtLYcy/uNMBUHeyj/qbNPcaX8lqTbXGciJ9HxaLQOmRO.','mike');

INSERT INTO users_roles VALUES (1,1);
INSERT INTO users_roles VALUES(2,2);
