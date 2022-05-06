(ns shouter.controllers.shouts
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route]
            [shouter.models.shouts :as model.shouts]
            [shouter.views.shouts :as views.shouts]
            [ring.util.response]
            [clojure.string :as str]))


;;Função para criar uma shout no postgres
;;Essa transação poderia ser protegida em um try-catch
;;IO
(defn create [shout]
  (when-not (str/blank? shout)
    (model.shouts/create shout)
    (ring.util.response/redirect "/")))


;;Definição de rotas relevantes para o contexto shout
(defroutes routes
           (GET "/" [] (views.shouts/index (model.shouts/all)))
           (POST "/" [shout] (create shout)))
