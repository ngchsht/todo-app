create table task (
    id serial primary key,
    title     varchar(100) not null,
    completed bool default false
);
