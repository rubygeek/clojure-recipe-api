(ns recipe-api.util
  (:require [korma.db :refer [postgres]]))

(def dbcon (postgres {:db "recipe-api" :user "api" :password "api"}))

