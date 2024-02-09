create table members (
    id SERIAL,
    name varchar(255),
    nameSecond varchar(255),
    tower varchar(255),
    apart varchar(255),
    coefficient float,
    canVote boolean,
    codeBar varchar(255),
    constraint members_pk primary key (id)
)