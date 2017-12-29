(ns recipe-api.models-test
  (:require [clojure.test :refer :all]
            [recipe-api.models :as m]
            [clojure.java.jdbc :as sql]))

(def test-dbspec { :dbtype "postgresql"
                   :dbname "recipes-test"
                   :host "127.0.0.1"
                   :user  "api"
                   :password "api" })

(defn empty-db []
  (sql/query test-dbspec 
             "truncate recipes"))


(with-redefs [m/dbspec test-dbspec]
  (let [data {:name "cookies"} 
        added (m/add-recipe data)
        total (m/count-recipes)]
    (is (= (:name data) (:name added)))
    (is (= 1 total))))
   



