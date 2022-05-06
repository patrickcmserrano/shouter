(defproject shouter "0.0.2"
  :description "Shouter app"
  :url "https://github.com/patrickcmserrano/shouter"
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.3.4"]
                 [compojure "1.6.2"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-anti-forgery "1.3.0"]]
  :main ^:skip-aot shouter.server
  :uberjar-name "shouter-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler shouter.server/application
         :init    shouter.models.migration/migrate}
  :profiles {:dev     {:dependencies [[javax.servlet/servlet-api "2.5"]
                                      [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})