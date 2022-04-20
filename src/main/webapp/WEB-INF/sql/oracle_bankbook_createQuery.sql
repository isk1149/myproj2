create table bankbook (
	bankcode varchar2(6),
    accountnumber varchar2(10),
    accountholder varchar2(12) not null,
    createdate date not null,
    deposit number(15) not null,
    rate number(4,2) not null,
    overrate number(4,2) not null,
    CONSTRAINT bankbook_pk PRIMARY KEY (bankcode, accountnumber)
);

create table transactionhistory (
	bankcode varchar2(6),
    accountnumber varchar2(10),
    transactiondate date,
    transactionbankcode varchar2(6),
    transactionaccountnumber varchar2(10),
    transactionamount number(15) not null,
    transactiontype varchar2(6) not null,
    CONSTRAINT transactionhistory_pk PRIMARY KEY (bankcode, accountnumber, transactiondate, transactionbankcode, transactionaccountnumber)
);

create table bankbookinterest (
	bankcode varchar2(6),
    accountnumber varchar2(10),
    interestapplydaycount number(4) not null,
    interest number(15) not null,
    CONSTRAINT banbookinterest_pk PRIMARY KEY (bankcode, accountnumber)
);

create table interesthistory (
	bankcode varchar2(6),
    accountnumber varchar2(10),
    interestsupplydate date not null,
    interest number(15) not null,
    CONSTRAINT interesthistory_pk PRIMARY KEY (bankcode, accountnumber, interestpaymentdate)
);

create table member (
	id varchar2(12),
	name varchar2(12) not null,
	password varchar2(20) not null,
	createdate date not null,
	constraint member_pk primary key (id) 
);

create table accountinfo (
	bankcode varchar2(6),
	accountnumber varchar2(10),
	accountholder varchar2(12),
	main char(1),
	constraint accountinfo_pk primary key (bankcode, accountnumber, accountholder)
);

create table bankcode (
	bankcode varchar2(6),
	bankname varchar2(12) not null,
	constraint bankcode_pk primary key (bankcode)
);

select to_char(transactiondate, 'yyyy-mm-dd hh24:mi:ss') from transactionhistory;
insert into transactionhistory values('000001', '0000006747', to_date('20220403113006', 'YYYYMMDDHH24MISS'), '000001', '0000003402', 100000000, 'O');
insert into transactionhistory values('000001', '0000003402', to_date('20220403113006', 'YYYYMMDDHH24MISS'), '000001', '0000006747', 100000000, 'I');
to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')

select * from bankbook;
update bankbook set deposit = 100000000 where accountnumber = '0000003402';
select * from transactionhistory;
delete from transactionhistory where transactionaccountnumber = '0000000001';
select * from interesthistory;
delete from interesthistory where accountnumber = '0000003402';


update bankbook set deposit = 100000000 where accountnumber = '0000003402';
delete from transactionhistory where transactionaccountnumber = '0000000001' and accountnumber = '0000003402';
delete from interesthistory where accountnumber = '0000003402';

update bankbookinterest set interestapplydaycount = 0, interest = 0 where accountnumber = '0000003402';