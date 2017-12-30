(defproject recipe-api "0.2.0"
  :description "an api for storing recipe links"
  :url "https://github.com/rubygeek/clojure-recipe-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [org.clojure/java.jdbc  "0.7.5"]
                 [org.postgresql/postgresql "42.1.4"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.1.0"]
                 [liberator "0.15.1"]
                 [compojure "1.6.0"]
                 [ring "1.6.3"]]

  :plugins [[lein-ring "0.12.2"]
            [lein-environ "1.1.0"]]

  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [com.stuartsierra/component.repl "0.2.0"]]
                   :source-paths ["dev"]}}
 
   :ring {:handler recipe-api.core/handler
         :nrepl { :start? true :port 5000}})
