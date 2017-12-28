(defproject recipe-api "0.2.0"
  :description "an api for storing recipe links"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [liberator "0.14.1"]
                 [compojure "1.5.2"]
                 [org.clojure/java.jdbc  "0.7.4"]
                 [org.postgresql/postgresql "42.1.4"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.1.0"]
                 [clj-time "0.14.2"]
                 [ring "1.5.1"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler recipe-api.core/handler
         :nrepl { :start? true :port 5000}})
