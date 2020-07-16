(ns baking-diary.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.coercions :refer [as-int]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [baking-diary.recipe :as recipe]
            [baking-diary.views :as views]))

(defroutes app-routes
  (GET "/" []
    (views/recipe-page))
  
  (POST "/" [flour :<< as-int
             hydration :<< as-int]
    (let [recipe (recipe/create-recipe (int flour) (int hydration))]
      (views/recipe-page recipe)))
  
  (route/not-found
   "Could not find the requested page"))

(def app
  (wrap-defaults app-routes site-defaults))