(ns recipe-api.util
  (:require [korma.db :refer [postgres]]
            [recipe-api.models :refer [recipe]])
  (:use korma.core))

(def dbcon (postgres {:db "recipe-api" :user "api" :password "api"}))

(defn delete-test-data []
  (delete recipe (where {:name [like "TEST%"]})))


