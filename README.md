# recipe-api

A REST webservice to retrieve recipe source, name and url

Clojure
Postgresql

## Usage

create a database:
`createdb recipes`

load the table schema into database:
`psql recipes <  create_recipes_table.sql`

Load sample data:
Load and execute src/recipe-api/sample-data.clj

run in development:
`lein ring server`

view the json for all recipes index:
`localhost:3000/recipes` 
