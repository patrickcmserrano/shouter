(ns shouter.views.shouts
  (:require [shouter.views.layout]
            [hiccup.form :as form]
            [ring.util.anti-forgery]
            [hiccup.util]))

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