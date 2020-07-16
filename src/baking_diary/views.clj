(ns baking-diary.views
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]
            [clojure.string :as str]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn format-title [title]
  (str
   (if (str/blank? title) "" (str title " - "))
   "Baker's Journal"))

(defn layout
  ([content] (layout "" content))
  ([title content]
   (html5
    [:head
     [:title (format-title title)]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0"}]
     [:link {:rel "stylesheet"
             :href "styles.css"}]]
    [:body
     [:div#content
      (html content)]])))

(defn recipe-list []
  (layout
   [:ul
    [:li
     [:div.recipe-card "Recipe"]]]))

(defn recipe-form
  ([] (recipe-form nil nil))
  ([flour water]
   [:div
    [:form {:method "post"}
     [:div
      [:label {:for "flour"} "Flour (g):"]
      [:br]
      [:input#flour {:type "number"
                     :name "flour"
                     :value flour}]]
     [:div
      [:label {:for "hydration"} "Hydration %:"]
      [:br]
      [:input#hydration {:type "number"
                         :name "hydration"
                         :value water}]]
     [:div [:input {:type "submit"
                    :value "Create Recipe"}]]
     (anti-forgery-field)]]))

(defn recipe-details [recipe]
  [:div.recipe-details
   [:p "Ingredients:"
    [:ul
     [:li "Flour: " (:flour recipe) "g"]
     [:li "Water: " (:water recipe) "g"]
     [:li "Levain: " (:levain recipe) "g"]
     [:li "Salt: " (:salt recipe) "g"]]]])

(defn recipe-page
  ([]
   (layout
    [:div
     (recipe-form)]))
  ([recipe]
   (layout
    [:div
     (recipe-form (:flour recipe) (:hydration recipe))
     (recipe-details recipe)])))