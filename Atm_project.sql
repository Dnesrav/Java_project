create table Atm_details
(
Username varchar(45),
AccNum int,
mobileNum int,
balance int,
pin int,
bank varchar(15));

 
insert into atm_details values("Dhinesh",637472674,959796564,25000,1234,"HDFC");
insert into atm_details values("Ramesh",646538874,934533464,45000,1434,"YesBank");
insert into atm_details values("Ganesh",639487574,955643264,85000,1834,"SBI");
insert into atm_details values("Ravi",637238474,959565434,15000,1294,"ICICI");
insert into atm_details values("Kumar",637344574,954346564,5000,1236,"IndianBank");

select * from atm_details;

create table transactions (accnt_number int,
date_of_transaction  datetime default Now() , 
mode varchar(45),amount int, balance int);

select * from transactions order by date_of_transaction desc;

drop table transaction


