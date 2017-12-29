(ns recipe-api.models-test
  (:require [clojure.test :refer :all]
            [recipe-api.models :as m]
            [clojure.java.jdbc :as sql]))

(defn empty-db
  "Empty all records from db"
  []
  (sql/delete! m/dbspec 
               :recipes ["id >= ?" 1]))

(use-fixtures :each (fn [tests]
                      (empty-db)
                      (tests)))

(deftest recipe-added-increased-count
  (let [data {:name "cookies"} 
        added (m/add-recipe data)
        total (m/count-recipes)]
    
    (is (= (:name data) (:name added)))
    (is (= 1 total))))
   



