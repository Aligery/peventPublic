--liquibase formatted sql logicalFilePath:2020-08-26--01-users-chats-events

--changeset ymezencev:users-chats-events

create table users (
  user_id serial,
  telegram_user_id integer,
  first_name varchar(255),
  last_name varchar(255),
  is_deleted bool not null default false,
  updated_at timestamp default now(),
  constraint pk_users primary key(user_id),
  constraint unq_telegram_user_id unique(telegram_user_id)
);

comment on table users is 'Пользователи';
comment on column users.user_id IS 'Идентификатор записи';
comment on column users.telegram_user_id IS 'Идентификатор пользователя в телеграме';
comment on column users.first_name IS 'Имя пользователя';
comment on column users.last_name IS 'Фамилия пользователя';
comment on column users.is_deleted is 'Признак удалённой записи';
comment on column users.updated_at is 'Время последнего апдейта';

--rollback drop table users;

--changeset ymezencev:chats
create table chats (
  chat_id serial,
  telegram_chat_id integer,
  chat_name varchar(255),
  is_deleted bool not null default false,
  updated_at timestamp default now(),
  constraint pk_chats primary key(chat_id),
  constraint unq_telegram_chat_id unique(telegram_chat_id)
);

comment on table chats is 'Чаты';
comment on column chats.chat_id IS 'Идентификатор записи';
comment on column chats.telegram_chat_id IS 'Идентификатор чата в телеграме';
comment on column chats.chat_name IS 'Название чата';
comment on column chats.is_deleted is 'Признак удалённой записи';
comment on column chats.updated_at is 'Время последнего апдейта';

--rollback drop table chats;

--changeset ymezencev:chat_users
create table chat_users (
  chat_user_id serial,
  chat_id integer not null,
  user_id integer not null,
  is_leader bool not null default false,
  is_deleted bool not null default false,
  updated_at timestamp default now(),
  constraint pk_chat_users primary key(chat_user_id),
  foreign key (chat_id) references chats (chat_id) on delete cascade,
  foreign key (user_id) references users (user_id) on delete cascade
);

comment on table chat_users is 'Пользователи в чатах';
comment on column chat_users.chat_user_id IS 'Идентификатор записи';
comment on column chat_users.chat_id IS 'Идентификатор чата';
comment on column chat_users.user_id IS 'Идентификатор пользователя';
comment on column chat_users.is_leader IS 'Признак является ли пользователь лидером чата. (1 - лидер, 0 - не лидер)';
comment on column chat_users.is_deleted is 'Признак удалённой записи';
comment on column chat_users.updated_at is 'Время последнего апдейта';

--rollback drop table chat_users;

--changeset ymezencev:event_type
create table event_type (
  event_type_id serial,
  event_type_name varchar(255),
  description text,
  constraint pk_event_type primary key(event_type_id)
);
comment on table event_type is 'Типы событий';
comment on column event_type.event_type_id IS 'Идентификатор записи';
comment on column event_type.event_type_name IS 'Название типа события';
comment on column event_type.description IS 'Описание';

--rollback drop table event_type;

--changeset ymezencev:events
create table events (
  event_id serial,
  event_type_id integer not null,
  ext_ident varchar(255),
  planned_at timestamp,
  event_name varchar(255),
  description text,
  url varchar(255),
  is_deleted bool not null default false,
  updated_at timestamp default now(),
  constraint pk_events primary key(event_id),
  constraint unq_ext_ident unique(ext_ident),
  foreign key (event_type_id) references event_type (event_type_id) on delete cascade
);

comment on table events is 'Основная таблица, куда загружаются все события';
comment on column events.event_id IS 'Идентификатор';
comment on column events.ext_ident IS 'Внешний идентификатор';
comment on column events.event_type_id IS 'Тип события';
comment on column events.planned_at IS 'Запланированная дата события';
comment on column events.event_name IS 'Название типа события';
comment on column events.description IS 'Описание';
comment on column events.url IS 'Ссылка';
comment on column events.is_deleted is 'Признак удалённой записи';
comment on column events.updated_at is 'Время последнего апдейта';

--rollback drop table events;

--changeset ymezencev:user_event_views
create table user_event_views (
  user_event_id serial,
  event_id integer,
  user_id integer,
  viewed_at timestamp,
  is_sent_to_chat bool not null default false,
  is_deleted bool not null default false,
  updated_at timestamp default now(),
  constraint pk_user_event_views primary key(user_event_id),
  foreign key (event_id) references events (event_id) on delete cascade,
  foreign key (user_id) references users (user_id) on delete cascade
);

comment on table user_event_views is 'Таблица просмотренных событий';
comment on column user_event_views.event_id IS 'Идентификатор события';
comment on column user_event_views.user_id IS 'Идентификатор пользователя';
comment on column user_event_views.viewed_at IS 'Время последнего просмотра';
comment on column user_event_views.is_sent_to_chat IS 'Было ли отправлено в чат (1 - отправлено, 0 - не отправлено)';
comment on column user_event_views.is_deleted is 'Признак удалённой записи';
comment on column user_event_views.updated_at is 'Время последнего апдейта';

--rollback drop table user_event_views;