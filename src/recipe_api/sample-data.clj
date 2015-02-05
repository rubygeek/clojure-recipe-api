(ns recipe-api.sample-data
  (:require [recipe-api.models :refer [add-recipe]]))

(defn load-sample-data []
  (do 
    (add-recipe {:name "TEST Hard Boiled Eggs" :url "www.hardboiledeggs.com" :source "Mom"})
    (add-recipe {:name "TEST Grilled Cheese" :url "www.grilledcheese.com" :source "Ultra Foods"})
    (add-recipe {:name "TEST Sliced Bread" :url "www.slicedbread.com" :source "Bread For You"})
    (add-recipe {:name "TEST Pizza" :url "www.pizza.com" :source "Good Recipes"})))
