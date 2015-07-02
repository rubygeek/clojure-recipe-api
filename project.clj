(defproject recipe-api "0.1.0-SNAPSHOT"
  :description "an api for storing recipe links"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [liberator "0.13"]
                 [compojure "1.1.3"]
                 [korma "0.3.0"]
                 [postgresql/postgresql "9.1-901-1.jdbc4"]
                 [org.clojure/data.json "0.2.5"]
                 [ring "1.2.1"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler recipe-api.core/handler
         :nrepl { :start? true :port 5000}}
  
  )


