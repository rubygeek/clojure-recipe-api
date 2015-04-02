(ns recipe-api.core
  (:require
   [clojure.data.json :as json]
   [recipe-api.util :as util]
   [liberator.core :refer [resource defresource]]
   [liberator.dev :refer [wrap-trace]]
   [recipe-api.models :refer [add-recipe all-recipes
                              recipe-entity delete-recipe update-recipe]]
    [compojure.core :refer [defroutes GET ANY]]))


(defresource all-recipes-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :handle-ok (fn [_] (json/write-str (all-recipes))))

(defn recipe-update-malformed?
  [{{method :request-method} :request :as ctx}]
  (if (= :put method)
    (let [recipe-data (util/parse-json-body ctx)]
      (cond
       (empty? recipe-data)
       [true {:message "No new values provided."}]
       :else [false {:recipe-data recipe-data}])))
  false)

(defresource recipe-resource [id]
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :put :delete]
  :exists?
  (fn [_]
    (if-let [rec (recipe-entity id)]
      [true {:recipe rec}]
      [false {:message "Recipe not found"}]))
  :malformed? recipe-update-malformed?
  :conflict? false
  :delete!
  (fn [{{recipe-id :id} :recipe}] (delete-recipe recipe-id))
  :can-put-to-missing? false
  :put!
  (fn [{recipe :recipe-data}] (update-recipe (assoc recipe :id id)))
  :handle-ok (fn [{recipe :recipe}] (json/write-str recipe)))

(defroutes app
  (ANY "/recipes" [] all-recipes-resource)
  (ANY "/recipe/:id" [id] (recipe-resource (Integer/parseInt id))))

(def handler
  (-> app
      (wrap-trace :header :ui)))



