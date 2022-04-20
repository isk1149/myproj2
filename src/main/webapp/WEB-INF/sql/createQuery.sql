create table myproj.stockinfo (
	stockcode varchar(6),
    basisyear varchar(4),
    sales int not null,
    operatingprofit int not null,
    netprofit int not null,
    eps int not null,
    roe decimal(5,2),
    primary key(stockcode, basisyear)
) engine=InnoDB default character set = utf8;

insert into stockinfo values('000660', '2017', 301094, 137213, 106422, 14617, 36.8);
insert into stockinfo values('000660', '2018', 404451, 208438, 155400, 21346, 38.53);
insert into stockinfo values('000660', '2019', 269907, 27192, 20091, 2755, 4.23);
insert into stockinfo values('000660', '2020', 319004, 50126, 47589, 6532, 9.53);
insert into stockinfo values('000660', '2021', 429978, 124103, 96162, 13190, 16.84);

insert into stockinfo values('005930', '2017', 2395754, 536450, 421867, 5421, 21.01);
insert into stockinfo values('005930', '2018', 2437714, 588867, 443449, 6024, 19.63);
insert into stockinfo values('005930', '2019', 2304009, 277685, 217389, 3166, 8.69);
insert into stockinfo values('005930', '2020', 2368070, 359939, 264078, 3841, 9.98);
insert into stockinfo values('005930', '2021', 2796048, 516339, 399074, 5777, 13.92);

insert into stockinfo values('058470', '2017', 1415, 492, 404, 2648, 18.82);
insert into stockinfo values('058470', '2018', 1504, 575, 486, 3191, 19.82);
insert into stockinfo values('058470', '2019', 1703, 641, 528, 3463, 18.75);
insert into stockinfo values('058470', '2020', 2013, 779, 554, 3633, 17.37);
insert into stockinfo values('058470', '2021', 2802, 1171, 1038, 6810, 27.5);


SELECT TIMESTAMPDIFF(year, '2017-01-01', '2018-01-01'); // 1












