(ns recipe-api.util
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

#_(defn delete-test-data []
  (delete recipe (where {:name [like "TEST%"]})))


(defn body-as-string [ctx]
  (if-let [body (get-in ctx [:request :body])]
    (condp instance? body
      java.lang.String body
      (slurp (io/reader body)))))

(defn keywordify [dict]
  (into {}
        (map (fn [[k v]] [(keyword k) v]) dict)))

(defn parse-json-body [context]
  (if-let [body (body-as-string context)]
    (keywordify (json/read-str body))
        {}))
