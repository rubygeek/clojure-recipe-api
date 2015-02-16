(ns recipe-api.core
  (:require
   [clojure.data.json :as json]
   [recipe-api.util :as util]
   [liberator.core :refer [resource defresource]]
    [recipe-api.models :refer [add-recipe all-recipes]]
    [compojure.core :refer [defroutes GET ANY]]))



(defresource all-recipes-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :handle-ok (fn [_] (json/write-str (all-recipes)))
  )

(defroutes app
  (ANY "/recipes" [] all-recipes-resource))

(def handler
  (-> app ))



