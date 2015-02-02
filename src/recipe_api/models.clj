(ns recipe-api.models
  (:import [java.util Date]
           [java.sql Timestamp])
  (:use korma.core korma.db))

(defdb db (postgres {:db "recipie-api" :user "api" :password "api"}))

(defentity recipe
  (pk :id)
  (table :recipes)
  (entity-fields :id :url :source))

(defn current-time []
  (Timestamp. (.getTime (Date.))))

(defn add-recipe [data]
  (let [ {:keys [source url]} data]
    (insert recipe (values {:source source :url url :created-at (current-time)}))))

(defn all-recipes []
  (select recipe))






  
