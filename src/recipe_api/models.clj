(ns recipe-api.models
  (:import [java.util Date]
           [java.sql Timestamp])
  (:use korma.core korma.db))

(defdb db (postgres {:db "recipes" :user "api" :password "api"}))

(defentity recipe
  (pk :id)
  (table :recipes)
  (entity-fields :id :name :url :source))

(defn current-time []
  (Timestamp. (.getTime (Date.))))

(defn add-recipe [data]
  (let [ {:keys [source url]} data]
    (insert recipe (values {:name name :source source :url url :created-at (current-time)}))))

(defn all-recipes []
  (select recipe))






  
