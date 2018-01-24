create table denglu(
	usid int primary key,
	uname varchar2(50) not null unique,
	pwd varchar2(20),
	sex varchar2(4),
	tel varchar2(15),
	photo varchar2(1000)
);
create sequence seq_denglu_usid start with 10001;
select *from denglu;
select count(usid) from denglu
drop table denglu;
insert into denglu values(seq_denglu_usid.nextval,'jun','111','男','18397770461','');