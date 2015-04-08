insert into user(id,username,password,status,descn) values(1,'admin','admin',1,'管理员');
insert into user(id,username,password,status,descn) values(2,'user','user',1,'用户');

insert into role(id,name,descn) values(1,'ROLE_ADMIN','管理员角色');
insert into role(id,name,descn) values(2,'ROLE_USER','用户角色');

insert into user_role(user_id,role_id) values(1,1);
insert into user_role(user_id,role_id) values(1,2);
insert into user_role(user_id,role_id) values(2,2);