CREATE ROLE api LOGIN
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE SEQUENCE recipes_id_seq;

GRANT USAGE, SELECT ON SEQUENCE recipes_id_seq TO api;

CREATE TABLE recipes
(
  id integer NOT NULL DEFAULT nextval('recipes_id_seq'::regclass),
  name text,
  url text,
  source text
);

ALTER TABLE recipes
  OWNER TO api;

