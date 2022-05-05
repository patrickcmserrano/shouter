(ns shouter.controllers.shouts
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route]
            [shouter.models.shouts :as model.shouts]
            [shouter.views.shouts :as views.shouts]
            [ring.util.response]
            [clojure.string :as str]))



(defn create [shout]
  (when-not (str/blank? shout)
    (clojure.pprint/pprint shout)
    (model.shouts/create shout)
    (ring.util.response/redirect "/")))

(defroutes routes
           (GET "/" [] (views.shouts/index (model.shouts/all)))
           (POST "/" [shout] (create shout))
           (POST "/ping" request (ring.util.response/redirect "/")))
