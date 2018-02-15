BEGIN;

create table channel (
  id serial primary key,
  title varchar(256) not null,
  link varchar(512) not null,
  description text
);

grant all on channel to reader;
grant all on channel_id_seq to reader;

create table post (
  id serial primary key,
  title varchar(256) not null,
  link varchar(512) not null,
  description text,
  guid text,
  read boolean,
  idchannel int references channel(id)
);

grant all on post to reader;
grant all on post_id_seq to reader;

COMMIT;