create database stu_db;

use stu_db;
create table student(
  sid int(10) not null,
  sname varchar(255) not null default '',
  sdob date not null,
  primary key (sid)
);
create table course(
  sid int(10) not null references student(sid),
  cid int(10) not null,
  cname varchar(255) not null default '',
  cteacher varchar(255) not null default '',
  primary key (cid)
);
//create table student_course(
//  sid int(10) not null references student(sid),
//  cid int(10) not null references course(cid),
//  marks decimal (5,2),
//  primary key(sid,cid)
//);