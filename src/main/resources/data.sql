insert into user (first_name, last_name, year_of_birth, login, password)
values ('Luka', 'Modric', 1985, 'lukam', '$2a$12$dr5y9Is2wyv6CbjvDNnOiOQEKRcbxtIMJpePoA69xX/89S41MdMG.');
insert into user (first_name, last_name, year_of_birth, login, password)
values ('Sergio', 'Ramos', 1986, 'sergior', '$2a$10$ut6NtRbQTFyS/v0bKvm6P.77AI14.tQQ6C4hjL5RD0ZA/X4.lysm6');
insert into user (first_name, last_name, year_of_birth, login, password)
values ('Marcelo', 'Vieira', 1988, 'marcelo', '$2a$12$/h3XkINV1cE3vN0QoWRQF.Ir5jZVk02DSUKogesMScz4bPYSWFZiW');
insert into role (role) values ('ADMIN'), ('USER');
insert into user_roles set user_id=(select id from user where login = 'lukam'),
    role_id = (select id from role where role = 'ADMIN');
insert into user_roles set user_id=(select id from user where login = 'sergior'),
    role_id = (select id from role where role = 'ADMIN');
insert into user_roles set user_id=(select id from user where login = 'sergior'),
    role_id = (select id from role where role = 'USER');
insert into user_roles set user_id=(select id from user where login = 'marcelo'),
    role_id = (select id from role where role = 'USER');