-- postgres数据库

-- 用户
create table users(
    id bigint,
    username varchar(50),
    password varchar(50),
    status INTEGER,
    descn varchar(200)
);
alter table users add constraint pk_user primary key(id);
-- alter table users alter column id bigint generated by default as identity(start with 1);

-- 角色
create table role(
    id bigint,
    name varchar(50),
    descn varchar(200)
);
alter table role add constraint pk_role primary key(id);
-- alter table role alter column id bigint generated by default as identity(start with 1);

-- 用户角色表
create table user_role(
    user_id bigint,
    role_id bigint
);

alter table user_role add constraint pk_user_role primary key(user_id, role_id);
alter table user_role add constraint fk_user_role_user foreign key(user_id) references
users(id);
alter table user_role add constraint fk_user_role_role foreign key(role_id) references
role(id);