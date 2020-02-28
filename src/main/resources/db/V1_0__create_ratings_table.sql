create table ratings
(
    id                 bigint not null auto_increment,
    completion_rating  bigint,
    objectivity_rating bigint,
    article_id         bigint,
    user_id            bigint,
    primary key (id)
);
