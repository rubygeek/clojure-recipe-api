# install restclient.el
# docs: https://github.com/pashky/restclient.el
#
GET http://localhost:3000/recipes
#
#
POST http://localhost:3000/recipes
Content-Type: application/json
{
  "name": "TEST uber",
  "url": "http://www.uber",
  "source": "charlie"
}
#
#
PUT http://localhost:3000/recipe/1
Content-Type: application/json
{ 
  "name" : "TEST newer name"
}
#
#
DELETE http://localhost:3000/recipe/1
#
#
GET http://localhost:3000/recipe/1