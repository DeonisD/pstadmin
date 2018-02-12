/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Deonis
 * Created: 10.02.2018
 */

create table if not exists city (
    id int primary key,
    soato varchar,
    tip varchar,
    name varchar,
    obl varchar,
    raion varchar,
    sovet varchar,
    gni varchar,
    datel varchar,
    soaton varchar,
    datav varchar
);

create table if not exists cardbin (
    id int primary key,
    column1 varchar,
    column2 varchar,
    column3 varchar,
    column4 varchar
);