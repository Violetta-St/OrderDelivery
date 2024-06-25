Создание и подключение базы данных.

Для создания и администрирования базы данных необходимо воспользоваться PgAdmin 4

#Важно! Заранее стоит запомнить пароль от баз данных, поскольку позже он нам пригодится.

Создайте базу данных Delivery и перейдите во вкладку Query Tool, нажав на базу данных правой кнопкой мыши. 

Далее вставляете данный SQL код и запускаете запрос: 


CREATE TABLE IF NOT EXISTS public.product_category

(

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

category_name character varying(150) COLLATE pg_catalog."default" NOT NULL,

description character varying(150) COLLATE pg_catalog."default" NOT NULL,

CONSTRAINT category_pkey PRIMARY KEY (id)

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_category

OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product

(

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

product_category_id integer NOT NULL,

product_name character varying(150) COLLATE pg_catalog."default" NOT NULL,

unit_price double precision NOT NULL,

in_stock integer NOT NULL,

in_order integer NOT NULL,

CONSTRAINT product_pkey PRIMARY KEY (id),

CONSTRAINT category_key FOREIGN KEY (category_id)

REFERENCES public.product_category (id) MATCH SIMPLE

ON UPDATE RESTRICT

ON DELETE CASCADE

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product

OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.orders

(

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

customer character varying(150) COLLATE pg_catalog."default" NOT NULL,

employee character varying(150) COLLATE pg_catalog."default" NOT NULL,

order_date character varying(150) COLLATE pg_catalog."default" NOT NULL,

ship_date character varying(150) COLLATE pg_catalog."default" NOT NULL,

ship_name character varying(150) COLLATE pg_catalog."default" NOT NULL,

ship_address character varying(150) COLLATE pg_catalog."default" NOT NULL,

CONSTRAINT order_pkey PRIMARY KEY (id)

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders

OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.order_detail

(

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

product_id integer NOT NULL,

order_id integer NOT NULL,

price double precision NOT NULL,

discount character varying(150) COLLATE pg_catalog."default" NOT NULL,

CONSTRAINT order_detail_pkey PRIMARY KEY (id),

CONSTRAINT order_key FOREIGN KEY (order_id)

REFERENCES public.orders (id) MATCH SIMPLE

ON UPDATE RESTRICT

ON DELETE CASCADE,

CONSTRAINT product_key FOREIGN KEY (product_id)

REFERENCES public.product (id) MATCH SIMPLE

ON UPDATE RESTRICT

ON DELETE CASCADE

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_detail

OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.user_model

(

id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),

username character varying(150) COLLATE pg_catalog."default" NOT NULL,

password text COLLATE pg_catalog."default" NOT NULL,

email character varying(150) COLLATE pg_catalog."default" NOT NULL,

CONSTRAINT user_model_pk PRIMARY KEY (id)

)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_model

OWNER to postgres;

INSERT INTO public.product_category(category_name, description)
VALUES ('Бытовая техника','Все для дома и дачи');
INSERT INTO public.product_category(category_name, description)
VALUES ('Смартфоны','Смартфоны и гаджеты');
INSERT INTO public.product_category(category_name, description)
VALUES ('ПК, ноутбуки и периферия','Обустрой свое рабочее место!');

INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (1, 'Стиральная машина Samsung', 28600, 12, 1);
INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (1, 'Духовой шкаф Indesit', 14900, 20, 1);
INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (2, 'Apple iPhone 15 Pro Max 256 Titanium', 143900, 17, 1);
INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (2, 'Samsung Galaxy S24 Ultra 512gb', 124900, 13, 1);
INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (3, 'Игровой Ноутбук Asus Predator', 119000, 12, 1);
INSERT INTO public.product(product_category_id, product_name, unit_price, in_stock, in_order)
VALUES (3, 'ПК Ardor Gaming Patrol I5 12400F RTX4060Ti', 105000, 3, 1);

INSERT INTO public.orders(customer, employee, order_date, ship_date, ship_name, ship_address)
VALUES ('Литвинова Оксана Сергеевна','Егоркина Анна Владиславовна','2024-06-10','2024-06-15','Курьерская служба','Малюгина 23/2');
INSERT INTO public.orders(customer, employee, order_date, ship_date, ship_name, ship_address)
VALUES ('Варфоломеев Егор Александрович','Егоркина Анна Владиславовна','2024-06-11','2024-06-12','Boxberry','Нансена 101/1');
INSERT INTO public.orders(customer, employee, order_date, ship_date, ship_name, ship_address)
VALUES ('Муратов Владимир Игоревич','Егоркина Анна Владиславовна','2024-06-12','2024-05-13','SDEK','Станиславского 119');

INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (1, 1, 28600, '10%');
INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (2, 1, 14900, '10%');
INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (3, 2, 143900, '5%');
INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (5, 2, 119000, '5%');
INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (4, 3, 124900, '5%');
INSERT INTO public.order_detail(product_id, orders_id, price, discount)
VALUES (6, 3, 105000, '5%');

После запуска кода, обновляете базу данны и на этом база данных с тестовыми данными готова к работе.
#Важно! Пользоваетль не создается автоматически! Его нужно зарегистрировать после запуска приложения.

Далее переходите в проекте в файл application.properties, в котором неоходимо поменять пароль к базе данных в поле

spring.datasource.password = "Ваш пароль без кавычек"

После этого проект готов к работе!
