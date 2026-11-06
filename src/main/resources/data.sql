-- Single line comments
delete from chat_user where user_id = 'user1';
/*
block comments
*/
insert into chat_user(user_id, nickname, user_mail, user_pwd)
values('user1', '테스트사용자', 'user1@hanmail.net', '1234');