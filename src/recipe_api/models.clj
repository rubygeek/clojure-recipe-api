(ns recipe-api.models
  (:import [java.util Date]
           [java.sql Timestamp])
  (:use korma.core korma.db))

(defdb db (postgres {:host "http://www.learnallthings.com" :db "recipes" :user "api" :password "api"}))

(defentity recipe
  (pk :id)
  (table :recipes)
  (entity-fields :id :name :url :source))

(defn current-time []
  (Timestamp. (.getTime (Date.))))
(current-time)

(defn add-recipe [data]
  (let [ {:keys [name source url]} data]
    (insert recipe (values {:name name :source source :url url :created-at (current-time)}))))

(defn add-recipes [datas]
  (map add-recipe datas))


(defn all-recipes []
  (select recipe))






  
