(ns shouter.views.layout
  (:require [hiccup.page :as h]))


;;Head de HTML comum para a aplicação no geral
(defn common [title & body]
  (h/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
     [:title title]
     (h/include-css "/stylesheets/base.css"
                    "/stylesheets/skeleton.css"
                    "/stylesheets/screen.css")
     (h/include-css "http://fonts.googleapis.com/css?family=Sigmar+One&v1")]
    [:body
     [:div {:id "header"}
      [:h1 {:class "container"} "SHOUTER"]]
     [:div {:id    "content"
            :class "container"}
      body]]))

(defn not-found []
  (common "Page Not Found"
          [:div {:id "not-found"}
           "The page you requested could not be found."]))