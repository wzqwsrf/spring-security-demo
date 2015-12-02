insert into users(id,username,password,status,descn) values(1,'admin','admin',1,'管理员');
insert into users(id,username,password,status,descn) values(2,'user','user',1,'用户');
insert into role(id,name,descn) values(1,'ROLE_ADMIN','管理员角色');
insert into role(id,name,descn) values(2,'ROLE_USER','用户角色');
insert into user_role(user_id,role_id) values(1,1);
insert into user_role(user_id,role_id) values(1,2);
insert into user_role(user_id,role_id) values(2,2);
insert into resc(id,name,res_type,res_string,priority,descn)
values(1,'','URL','/admin.jsp',1,'');
insert into resc(id,name,res_type,res_string,priority,descn)
values(2,'','URL','/**',2,'');
insert into resc_role(resc_id,role_id) values(1,1);
insert into resc_role(resc_id,role_id) values(2,1);
insert into resc_role(resc_id,role_id) values(2,2);