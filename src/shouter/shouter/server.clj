(ns shouter.server
  (:require [compojure.core]
            [compojure.route]
            [ring.adapter.jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [shouter.models.migrations]
            [ring.middleware.resource :refer [wrap-resource]]
            [shouter.controllers.shouts :as controllers.shouts]
            [shouter.views.layout :as view.layout])
  (:gen-class))

(def port
  (new Integer (or (System/getenv "PORT")
                   "8080")))

(compojure.core/defroutes routes
                          controllers.shouts/routes
                          (compojure.route/not-found (view.layout/not-found)))

(def application
  (-> routes
      (wrap-resource "/")
      (wrap-defaults site-defaults)))

(defonce server (atom nil))

(defn start []
  (prn (str "Starting server on port: " port))
  (reset! server (ring.adapter.jetty/run-jetty application {:port  port
                                                            :join? false})))

(defn stop []
  (prn "Stopping server")
  (.stop @server)
  (reset! server nil))

(defn -main []
  (shouter.models.migrations/migrate)
  (start))

(comment

  (stop)

  (start))
