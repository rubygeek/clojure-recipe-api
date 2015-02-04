# recipe-api

A REST webservice to retrieve recipe source, name and url

Clojure
Postgresql

## Usage

create a database:
`createdb recipes`

load the table schema into database:
`psql recipes <  create_recipes_table.sql`

run in development:
`lein ring server`
