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

После запуска кода, обновляете базу данны и на этом пустая база данных готова к работе.

Далее переходите в проекте в файл application.properties, в котором неоходимо поменять пароль к базе данных в поле

spring.datasource.password = "Ваш пароль без кавычек"

После этого проект готов к работе!
