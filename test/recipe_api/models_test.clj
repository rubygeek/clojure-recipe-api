(ns recipe-api.models-test
  (:require [clojure.test :refer :all]
            [environ.core :refer [env]]
            [recipe-api.models :as m]
            [clojure.java.jdbc :as sql]))

(def test-dbspec { :dbtype   "postgresql"
                   :dbname   (:database-name env)
                   :host     "127.0.0.1"
                   :user     (:database-user env)
                   :password (:database-pass env) })

(defn empty-db []
  (sql/query test-dbspec 
             "truncate recipes"))


(with-redefs [m/dbspec test-dbspec]
  (let [data {:name "cookies"} 
        added (m/add-recipe data)
        total (m/count-recipes)]
    (is (= (:name data) (:name added)))
    (is (= 1 total))))
   



