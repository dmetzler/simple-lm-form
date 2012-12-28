# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table saveo_form (
  id                        bigint not null,
  created                   timestamp,
  updated                   timestamp,
  saveo_id                  varchar(255),
  product_id                varchar(255),
  age                       varchar(255),
  gender                    varchar(255),
  diy_level                 varchar(255),
  global_product_rating     integer,
  recommend_product         boolean,
  product_avistitle         varchar(255),
  product_avis              varchar(255),
  product_has_positive_note boolean,
  product_positive_note     varchar(255),
  product_has_negative_note boolean,
  product_negative_note     varchar(255),
  product_quality           integer,
  product_usage             integer,
  product_notice            integer,
  product_montage           integer,
  product_maintenance       integer,
  product_security          integer,
  global_service_rating     integer,
  recommend_service         boolean,
  service_avistitle         varchar(255),
  service_avis              varchar(255),
  service_has_positive_note boolean,
  service_positive_note     varchar(255),
  service_has_negative_note boolean,
  service_negative_note     varchar(255),
  service_respect           integer,
  accueil                   integer,
  ecoute                    integer,
  confiance                 integer,
  tech_skills               integer,
  waiting_time              integer,
  constraint pk_saveo_form primary key (id))
;

create sequence saveo_form_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists saveo_form;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists saveo_form_seq;

