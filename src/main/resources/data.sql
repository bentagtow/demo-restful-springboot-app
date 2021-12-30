insert into user
values (10001, sysdate(), 'AB');
insert into user
values (10002, sysdate(), 'Jill');
insert into user
values (10003, sysdate(), 'Jam');

-- first field: id
-- second field: desc
-- third description: user id
insert into post
values (11001, 'my first post!', 10001);
insert into post
values (11002, 'my second post from user 10001!', 10001);