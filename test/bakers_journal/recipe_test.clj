(ns bakers-journal.recipe-test
  (:require [clojure.test :refer [deftest testing is]]
            [bakers-journal.recipe :as recipe]))

(deftest test-recipe
  (let [dough {:flour 500}]
    
    (testing "Hydration is calculated correctly"
      (let [dough-with-water (recipe/water 75 dough)]
        (is (= (:hydration dough-with-water) 75))
        (is (= (:water dough-with-water) 375))))
    
    (testing "Levain is calculated correctly"
      (let [dough-with-levain (recipe/levain dough)]
        (is (= (:levain dough-with-levain) 100))))
    
    (testing "Salt is calculated correctly"
      (let [dough-with-salt (recipe/salt dough)]
        (is (= (:salt dough-with-salt) 10)))))
  
  (testing "Create recipe function results in all four ingredients"
    (let [recipe (recipe/create-recipe 500 75)]
      (is (contains? recipe :flour))
      (is (contains? recipe :water))
      (is (contains? recipe :levain))
      (is (contains? recipe :salt)))))