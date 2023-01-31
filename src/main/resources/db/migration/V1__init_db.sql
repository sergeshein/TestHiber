--USERS

DROP SEQUENCE IF EXISTS user_seq;
create sequence user_seq start 1 increment 1;
DROP TABLE IF EXISTS users CASCADE;
-- create table users (
--                        id int8 not null,
--                        archived boolean not null,
--                        email varchar(255),
--                        name varchar(255),
--                        password varchar(255),
--                        role varchar(255),
--                        primary key (id));
create table users (
    id bigint not null,
     archived boolean not null,
      email varchar(255),
       name varchar(255),
        password varchar(255),
         role varchar(255),
           primary key (id));

--BUCKET

DROP SEQUENCE IF EXISTS bucket_seq;
create sequence bucket_seq start 1 increment 1;

DROP TABLE IF EXISTS bucket CASCADE;
create table bucket (
    id bigint not null,
     user_id bigint,
      primary key (id));

--LINK BETWEEN BUCKET AND USER

alter table if exists bucket
    add constraint bucket_with_user
    foreign key (user_id) references users;


--CATEGORY

DROP SEQUENCE IF EXISTS category_seq;
create sequence category_seq start 1 increment 1;

DROP TABLE IF EXISTS category CASCADE;
create table category (
    id bigint not null,
     tittle varchar(255),
      primary key (id));

--PRODUCT
DROP SEQUENCE IF EXISTS product_seq;
create sequence product_seq start 1 increment 1;

DROP TABLE IF EXISTS products CASCADE;
create table products (
    id bigint not null,
     price numeric(38,2),
      tittle varchar(255),
       primary key (id));

--CATEGORY AND PRODUCT
DROP TABLE IF EXISTS products_categories CASCADE;
create table products_categories (
    product_id bigint not null,
     category_id bigint not null);

alter table if exists products_categories
    add constraint products_categories_with_category
    foreign key (category_id) references products;

alter table if exists products_categories
    add constraint products_categories_with_product
    foreign key (product_id) references products;

--PRODUCT IN BUCKET
DROP TABLE IF EXISTS buckets_products CASCADE;
create table buckets_products (
    bucket_id bigint not null,
     product_id bigint not null);

alter table if exists buckets_products
    add constraint buckets_products_with_products
    foreign key (product_id) references products;

alter table if exists buckets_products
    add constraint buckets_products_with_bucket
    foreign key (bucket_id) references bucket;


--ORDERS
DROP SEQUENCE IF EXISTS order_seq;
create sequence order_seq start 1 increment 1;

DROP TABLE IF EXISTS orders CASCADE;
create table orders (
    id bigint not null,
     address varchar(255),
      created timestamp(6),
       status varchar(255),
        sum numeric(38,2),
         updated timestamp(6),
          user_id bigint,
           primary key (id));

alter table if exists orders
    add constraint osders_with_users
    foreign key (user_id) references users;


--ORDER DETAILS
DROP SEQUENCE IF EXISTS orders_details_seq;
create sequence orders_details_seq start 1 increment 1;

DROP TABLE IF EXISTS orders_details CASCADE;
create table orders_details (
    id bigint not null,
    amount numeric(38,2),
    price numeric(38,2),
    order_id bigint,
    product_id bigint,
    primary key (id));

-- alter table if exists orders_details
--     add constraint order_details_with_orders
--     foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint order_details_with_orders
    foreign key (order_id) references orders;

-- alter table if exists orders_details
--     add constraint order_details_with_products
--     foreign key (product_id) references products;
alter table if exists orders_details
    add constraint order_details_with_products
    foreign key (product_id) references products;

create table orders_order_details (
    order_id bigint not null,
     order_details_id bigint not null);

