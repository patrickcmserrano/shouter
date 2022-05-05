(ns shouter.models.shouts
  (:require [clojure.java.jdbc :as jdbc]))

(def postgres-port
  (or (System/getenv "DATABASE_URL")
      "postgresql://localhost:5432/shouter"))

(defn all []
  (into [] (jdbc/query postgres-port ["SELECT * FROM shouts order by id desc"])))

(defn create [shout]
  (jdbc/insert! postgres-port :shouts [:body] [shout]))