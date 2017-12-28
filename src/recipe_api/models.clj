(ns recipe-api.models
  (:require [environ.core      :refer [env]]
            [clojure.java.jdbc :as sql]
            [clj-time.core :as t]
            [clj-time.jdbc]))

(def dbspec { :dbtype "postgresql"
              :dbname "recipes2"
              :host "127.0.0.1"
              :user  "api"
              :password "api"})

(defn ^:private status
   "Return nice result"
   [result]
   (if (= 1 (first result))
    :success
    :failure))
  
(defn add-recipe [data]
  (let [ {:keys [name source url]} data]
    (sql/insert! dbspec :recipes { :name   name
                                   :source source
                                   :url    url} )))

(defn add-recipes [datas]
  (map add-recipe datas))

(defn all-recipes []
  (sql/query dbspec
             ["select * from recipes"]))

;; get one recipe
(defn recipe-entity [id]
  (first (sql/query dbspec ["select * from recipes where id = ?" id])))

;; alias recipe-entity method for now 
(def get-recipe-by-id recipe-entity)

(defn delete-recipe [id]
  (status (sql/delete! dbspec :recipes ["id = ?" id])))

(defn update-recipe [id rec]
  (status (sql/update! dbspec :recipes (select-keys rec [:name :source :url]) ["id = ?" id] )))
