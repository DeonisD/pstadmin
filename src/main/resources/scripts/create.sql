/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Deonis
 * Created: 10.02.2018
 */

CREATE TABLE IF NOT EXISTS TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
merge INTO TEST VALUES (1, 'mkyong');

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