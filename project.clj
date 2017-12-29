(defproject recipe-api "0.2.0"
  :description "an api for storing recipe links"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [liberator "0.15.1"]
                 [compojure "1.6.0"]
                 [org.clojure/java.jdbc  "0.7.5"]
                 [org.postgresql/postgresql "42.1.4"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.1.0"]
                 [clj-time "0.14.2"]
                 [ring "1.6.3"]]
  :plugins [[lein-ring "0.12.2"]]
  :ring {:handler recipe-api.core/handler
         :nrepl { :start? true :port 5000}})
