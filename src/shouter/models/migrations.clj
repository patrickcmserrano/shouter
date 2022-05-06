(ns shouter.models.migrations
  (:require [clojure.java.jdbc :as jdbc]
            [shouter.models.shouts :as models.shout]))


;;A ferramenta jdbc permite compor queries SQL como clojure data
;;Porém pode ser interessante salvarmos as queries SQL como SQL mesmo, e para isso usariamos a ferramenta HugSQL
;;https://www.hugsql.org/
(defn create-shouts-table []
  (jdbc/create-table-ddl
    :shouts
    [[:id :serial "PRIMARY KEY"]
     [:body :varchar "NOT NULL"]
     [:created_at :timestamp
      "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))


;;Checagem se o banco está atualizado.
;;Nessa arquitetura, ao criar qualquer novo relacionamento no banco, deveremos chegar aqui
(defn migrated? []
  (-> (jdbc/query models.shout/postgres-port
                  [(str "SELECT count(*) from information_schema.tables "
                        "WHERE table_name='shouts'")])
      first
      :count
      pos?))

;;E aqui vamos atualizando o esqueleto do banco necessário para init da aplicação
(defn migrate []
  (when-not (migrated?)
    (jdbc/db-do-commands models.shout/postgres-port (create-shouts-table))))

