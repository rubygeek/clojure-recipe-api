(ns recipe-api.core
  (:require
   [clojure.data.json :as json]
   [recipe-api.util :as util]
   [liberator.core :refer [resource defresource]]
    [recipe-api.models :refer [add-recipe all-recipes recipe-entity delete-recipe]]
    [compojure.core :refer [defroutes GET ANY]]))


(defresource all-recipes-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :handle-ok (fn [_] (json/write-str (all-recipes))))

(defresource recipe-resource [id]
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :exists?
  (fn [_]
    (if-let [rec (recipe-entity id)]
      [true {:recipe rec}]
      [false {:message "Recipe not found"}]))
  :delete!
  (fn [{{recipe-id :id} :recipe}] (delete-recipe recipe-id))
  :handle-ok (fn [{recipe :recipe}] (json/write-str recipe)))

(defroutes app
  (ANY "/recipes" [] all-recipes-resource)
  (ANY "/recipe/:id" [id] (recipe-resource (Integer/parseInt id))))

(def handler
  (-> app ))



