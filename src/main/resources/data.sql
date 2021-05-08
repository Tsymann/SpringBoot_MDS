INSERT INTO role (id, name) VALUES (1, 'seller');
INSERT INTO role (id, name) VALUES (2, 'buyer');
INSERT INTO user (id,firstname,lastname,wallet,role_id) VALUES (1,'Billy','Dupond',100,1);
INSERT INTO user (id,firstname,lastname,wallet,role_id) VALUES (2,'Bobby','Dubois',100,1);
INSERT INTO user (id,firstname,lastname,wallet,role_id) VALUES (3,'Patrick','Durand',100,2);
INSERT INTO user (id,firstname,lastname,wallet,role_id) VALUES (4,'Jacko','Duchateau',100,2);
INSERT INTO book (id,name,nb_page,price,user_id,on_sale) VALUES (1,'martine va a la plage',28,3,1, true);
INSERT INTO book (id,name,nb_page,price,user_id,on_sale) VALUES (2,'le journal de mickey',128,7,2, true);
INSERT INTO book (id,name,nb_page,price,user_id,on_sale) VALUES (3,'le livre rouge',15,12,3, true);
INSERT INTO book (id,name,nb_page,price,user_id,on_sale) VALUES (4,'la bible',9999,130,4, false);
INSERT INTO book (id,name,nb_page,price,user_id,on_sale) VALUES (5,'le java pour les nuls',245,1,4, false);


