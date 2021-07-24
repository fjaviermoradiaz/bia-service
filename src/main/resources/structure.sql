drop table measure;
create table measure (
    id bigint auto_increment,
    timestamp TIMESTAMP NOT NULL,
    devicesn VARCHAR(250) NOT NULL,
    energy DOUBLE DEFAULT 0,
    created_at TIMESTAMP NOT NULL
);



