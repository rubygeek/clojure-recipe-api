(ns recipe-api.core
  (:require
    [clojure.data.json :as json]
    [recipe-api.util :as util]
    [liberator.core :refer [resource defresource]]
    [liberator.dev :refer [wrap-trace]]
    [liberator.representation :refer [as-response]]
    [recipe-api.models :refer [add-recipe all-recipes
                               delete-recipe get-recipe-by-id update-recipe]]
    [compojure.core :refer [defroutes GET ANY]]))

(defn recipe-request-malformed?
  [{{method :request-method} :request :as ctx}]
  (if (= :post method)
    (let [recipe-data (util/parse-json-body ctx)]
      (if (empty? (:name recipe-data))
       
        [true {:message "Recipe name missing or empty"}]
        [false {:recipe-data recipe-data}]))
     false))

(defn recipe-update-malformed?
  [{{method :request-method} :request :as ctx}]
  (if (= :put method)
    (let [recipe-data (util/parse-json-body ctx)]
      (cond (empty? recipe-data)
        [true {:message "No new values provided."}]
        :else [false {:recipe-data recipe-data}])))
      false)

(defresource all-recipes-resource [_]
   :as-response (fn [d ctx]
                 (-> (as-response d ctx) ;; default implementation
                     (assoc-in [:headers "Access-Control-Allow-Origin"] "*")))
  :media-type-available? (fn [_] "application/json")
  :malformed? recipe-request-malformed?
  :allowed-methods [:get :post]
  :post! 
    (fn [{recipe :recipe-data}] (add-recipe recipe))
  :handle-ok (fn [_] (json/write-str (all-recipes))))

(defresource recipe-resource [id]
  :available-media-types ["application/json"]
  :allowed-methods [:get :put :delete]
  :exists?
  (fn [_]
    (if-let [rec (get-recipe-by-id id)]
      [true {:recipe rec}]
      [false {:message "Recipe not found"}]))
  :malformed? recipe-update-malformed?
  :conflict? false
  :delete!
    (fn [{{recipe-id :id} :recipe}] (delete-recipe recipe-id))
  :can-put-to-missing? false
  :put!
    (fn [{recipe :recipe-data}] (update-recipe (assoc recipe :id id)))
  :handle-ok (fn [{recipe :recipe}]
     (json/write-str recipe)))

(defroutes app
  (ANY "/recipes" [] all-recipes-resource)
  (ANY "/recipe/:id" [id]  (recipe-resource (Integer/parseInt id))))

(def handler
  (-> app
    (wrap-trace :header :ui)))





