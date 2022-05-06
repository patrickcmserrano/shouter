(ns shouter.views.shouts
  (:require [shouter.views.layout]
            [hiccup.form :as form]
            [ring.util.anti-forgery]
            [hiccup.util]))

;;Form usando helpers do hiccup para passar uma ring anti-forgery-token
;;https://docs.microsoft.com/en-us/aspnet/web-api/overview/security/preventing-cross-site-request-forgery-csrf-attacks
;;https://www.youtube.com/watch?v=vrjgD0azkCw
;;https://github.com/ring-clojure/ring-anti-forgery
;;https://stackoverflow.com/questions/4852768/how-does-antiforgerytoken-work
(defn shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (ring.util.anti-forgery/anti-forgery-field)
                 (form/label "shout" "What do you want to SHOUT?")
                 (form/text-area "shout")
                 (form/submit-button "SHOUT!"))])


(defn display-shouts [shouts]
  [:div {:class "shouts sixteen columns alpha omega"}
   (map
     (fn [shout]
       [:h2 {:class "shout"}
        (hiccup.util/escape-html (:body shout))])
     shouts)])

(defn index [shouts]
  (shouter.views.layout/common "SHOUTER"
                               (shout-form)
                               [:div {:class "clear"}]
                               (display-shouts shouts)))