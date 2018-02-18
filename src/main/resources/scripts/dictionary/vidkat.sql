create schema if not exists dictionary;

create table if not exists dictionary.vidkat(
	id int primary key,
	kodstr  numeric(6),
	naimkat  varchar(180),
last_mod TIMESTAMP
) as select * from ( values 
	(1,1,'Издания Республики Беларусь','2018-02-16 00:00:00.0'),
	(2,2,'Издания стран СНГ','2018-02-16 00:00:00.0'),
	(3,3,'Издания органов научно-технической информации (НТИ)','2018-02-16 00:00:00.0'),
	(4,4,'Издания дальнего зарубежья','2018-02-16 00:00:00.0')) as V;
	commit;