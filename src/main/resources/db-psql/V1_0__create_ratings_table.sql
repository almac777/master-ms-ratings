create table ratings
(
    id                 bigint generated always as identity,
    completion_rating  bigint,
    objectivity_rating bigint,
    article_id         bigint,
    user_id            bigint
);
