/**
 * Author:  drozdov_d
 * Created: 14.02.2018
 */

create schema if not exists pst;
create schema if not exists dictionary;

create table if not exists pst.property (
    name        varchar,
    value       varchar,
    description varchar,
    last_mod    TIMESTAMP AS NOW()
);

create table if not exists pst.terminal_cashin (
    value       double,
    currency    varchar,
    amount      double,
    count       int,
    last_mod    TIMESTAMP AS NOW()
);

create table if not exists pst.terminal_property (
    name        varchar,
    value       varchar,
    description varchar,
    last_mod    TIMESTAMP AS NOW()
);

