# recipe-api

A REST webservice to retrieve recipe source, name and url

Clojure
Postgresql

## Usage

create a database and user in postgresql:
`createdb recipes`


run the user creation and table schema:
`psql recipes <  create_recipes_table.sql`

Load sample data:
```clj
(load-test-data)
```


```sql
id |       name       |          url           |    source     |         created-at
----+------------------+------------------------+---------------+----------------------------
53 | Hard Boiled Eggs | www.hardboiledeggs.com | Mom           | 2015-02-07 08:35:43.349-06
54 | Grilled Cheese   | www.grilledcheese.com  | Ultra Foods   | 2015-02-07 08:35:43.377-06
55 | Sliced Bread     | www.slicedbread.com    | Bread for you | 2015-02-07 08:35:43.38-06
56 | Pizza            | www.pizza.com          | Good Recipes  | 2015-02-07 08:35:43.42-06
(4 rows)
```

Add your own recipes at the repl:
```clj
(add-recipe {:name "Rice & Chicken" :url "http://www.myrecipes.com/recipe/chicken-and-rice" :source "My Recipes"})
```
Delete all recipes with names starting with TEST
```clj
(delete-test-data)
```

run in development:
`lein ring server`

view the json for all recipes index:
`localhost:3000/recipes`
