(ns recipe-api.core
  (:require
   [clojure.data.json :as json]
   [liberator.core :refer [resource defresource]]
    [ring.middleware.params :refer [wrap-params]]
    [korma.core :refer
      [select insert values where delete update set-fields with join]]
    [recipe-api.models :refer [add-recipe all-recipes]]
    [compojure.core :refer [defroutes GET ANY]]))


(defn recipe-request-malformed?
  [{{method :request-method} :request :as ctx}]
  (if (= :post method)
    (let [recipe-data (util/parse-json-body ctx)]
      (if (empty? (:url recipe-data))
        [true {:message "Recipe url  missing or empty"}]
        [false {:recipe-data recipe-data}]))
    false))


(defresource all-recipes-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :malformed? recipe-request-malformed
  :post! (fn [ {recipe-data :recipe-data}]
          (add-recipe recipe-data))
  :handle-ok (fn [_] (json/write-str (all-recipes)))
  )

(defroutes app
  (ANY "/recipes" [] all-recipes-resource))

(def handler
  (-> app
      wrap-params))



