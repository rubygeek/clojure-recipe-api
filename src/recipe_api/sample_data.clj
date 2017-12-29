(ns recipe-api.sample-data
  (:require [recipe-api.models :refer [add-recipe add-recipes]]))

(def sample-data
   [{:name "Hard Boiled Eggs" :url "www.hardboiledeggs.com" :source "Mom"},
    {:name "Grilled Cheese"   :url "www.grilledcheese.com"  :source "Ultra Foods"},
    {:name "Sliced Bread"     :url "www.slicedbread.com"    :source "Bread for you"},
    {:name "Pizza"            :url "www.pizza.com"          :source "Good Recipes"}])

(defn testify-data [data]
 (update data :name #(str "TEST " %)))

(defn testify-all-data [datas]
  (map testify-data datas))

(defn load-test-data []
  (add-recipes (testify-all-data sample-data)))
