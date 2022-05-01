(ns shouter.web
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [hiccup.page :as h]))

(defn index []
  (h/html5
    [:head
     [:title "Hello World"]]
    [:body
     [:div {:id "content"} "Hello World"]]))

(defroutes routes
           (GET "/" [] (index)))

(defn -main []
  (ring/run-jetty #'routes {:port  8080
                            :join? false}))
