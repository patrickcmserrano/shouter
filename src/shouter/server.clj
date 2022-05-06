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

;;Definindo porta para subir o jetty
;;System/getenv seguindo padrão Heroku
;;https://devcenter.heroku.com/articles/config-vars
(def port
  (new Integer (or (System/getenv "PORT")
                   "8080")))

;;Aqui adicionamos novas rotas
(compojure.core/defroutes routes
                          controllers.shouts/routes
                          (compojure.route/not-found (view.layout/not-found)))


;;A aplication é onde adicionamos middlewares
;;Alimenta o jetty-server
;;O interessante aqui é a (wrap-defaults site-defaults) que adiciona o boilerplate relevante para subirmos uma aplicação browser oriented
(def application
  (-> routes
      (wrap-resource "/")
      (wrap-defaults site-defaults)))

;;Definir o server como atomo é um suggar para desenvolvimento no REPL
;;Assim podemos desligar e ligar o servidor na mesma instancia do REPL
(defonce server (atom nil))

(defn start []
  (prn (str "Starting server on port: " port))
  (reset! server (ring.adapter.jetty/run-jetty application {:port  port
                                                            ;; :join? false means that the evaluating thread won't wait for the
                                                            ;; server to finish (so that the repl doesn't seem to hang).
                                                            ;;http://www.learningclojure.com/2013/01/getting-started-with-ring.html
                                                            :join? false})))

(defn stop []
  (prn "Stopping server")
  (.stop @server)
  (reset! server nil))


;;main é a principal função que será especificada nas configurações de build/deploy para inicialização do software
;;O conceito de migrations será para subir/atualizar/inicializar o banco relacional postgresql
(defn -main []
  (shouter.models.migrations/migrate)
  (start))


;;A comment no final do namespace é um suggar para desenvolvimento via REPL
;;E mantém o histórico de desenvolvimento e fluxo de eval de funções dentro do git
(comment

  (stop)
  (start))
