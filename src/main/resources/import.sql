insert into node(name) values ('A');
insert into node(name) values ('B');
insert into node(name) values ('C');
insert into node(name) values ('D');
insert into node(name) values ('E');
insert into node(name) values ('F');

insert into paths(id, from_node, to_node, weight) values (1,'A','B', 10);
insert into paths(id, from_node, to_node, weight) values (2,'A','C', 15);

insert into paths(id, from_node, to_node, weight) values (3,'B','D', 12);
insert into paths(id, from_node, to_node, weight) values (4,'B','F', 15);

insert into paths(id, from_node, to_node, weight) values (5,'C','E', 10);

insert into paths(id, from_node, to_node, weight) values (6,'D','E', 2);
insert into paths(id, from_node, to_node, weight) values (7,'D','F', 1);

insert into paths(id, from_node, to_node, weight) values (8,'F','E', 5);

select * from node;
select * from paths;