(ns shouter.models.migrations
  (:require [clojure.java.jdbc :as jdbc]
            [shouter.models.shouts :as models.shout]))


(defn create-shouts-table []
  (jdbc/create-table-ddl
    :shouts
    [[:id :serial "PRIMARY KEY"]
     [:body :varchar "NOT NULL"]
     [:created_at :timestamp
      "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))

(defn migrated? []
  (-> (jdbc/query models.shout/postgres-port
                  [(str "SELECT count(*) from information_schema.tables "
                        "WHERE table_name='shouts'")])
      first
      :count
      pos?))

(defn migrate []
  (when-not (migrated?)
    (jdbc/db-do-commands models.shout/postgres-port (create-shouts-table))))

