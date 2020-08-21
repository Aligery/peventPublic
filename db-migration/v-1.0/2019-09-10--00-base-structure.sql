--liquibase formatted sql logicalFilePath:2019-09-10--00-base-structure.sql


--changeset lepa-id:base-structure
create table system_user
(
	id serial not null,
	login varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	constraint system_user_pkey PRIMARY KEY(id)
);
--rollback drop table system_user;


--changeset lepa-id:role_dictionary
create table dictionary_role
(
    id serial not null,
    role_name varchar(255),
    constraint dictinary_role_pkey PRIMARY KEY(id)
);
--rollback drop table dictionary_role


--changeset lepa-id:references-id-role
create table user_role
(
    user_id integer constraint user_role_user_id_fk references system_user(id),
    role_id integer constraint user_role_role_id_fk references dictionary_role(id),
    constraint user_role_pkey PRIMARY KEY (user_id, role_id)
);
--rollback drop table user_role;


--changeset lepa-id:lesson-table
create table lesson_table
(
    lesson_id serial not null,
    lesson_title varchar(255),
    constraint lesson_id_pkey PRIMARY KEY(lesson_id)
);
--rollback drop table lesson_table;


--changeset lepa-id:lesson_user
create table lesson_user
(
    user_id integer constraint lesson_user_user_id_fk references system_user(id),
    lesson_id integer constraint lesson_user_lesson_id_fk references lesson_table(lesson_id),
    date_of_mark timestamp(12),
    mark integer,
    constraint lesson_user_pkey PRIMARY KEY (user_id, lesson_id, date_of_mark)
);
--rollback drop table lesson_user;
