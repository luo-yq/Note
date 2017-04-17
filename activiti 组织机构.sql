
drop table ACT_ID_USER;
drop table ACT_ID_INFO;
drop table ACT_ID_GROUP;
drop table ACT_ID_MEMBERSHIP;



create or replace view ACT_ID_USER as
  select '' id_, 0 rev_,'' first_,'' last_,'' email_,'' pwd_,'' picture_id_ from t_user;

create or replace view ACT_ID_INFO as
  select ''  ID_,0  REV_,''  USER_ID_,'userinfo'  TYPE_, ''  KEY_,''  VALUE_,''  PASSWORD_,''  PARENT_ID_
  from dual where 1!=1;
  
create or replace view ACT_ID_GROUP as
  select 'R_'|| id id_, 0 rev_,mc name_,'ROLE' type_ from t_group ;
  
create or replace view ACT_ID_MEMBERSHIP as
  select a.grbh user_id_,'R_'||a.groupid group_id_ from t_group_user a,t_user b where a.grbh=b.grbh and b.grzt = '01'
 
