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
  
  (testing "Create recipe results in all four ingredients and hydration"
    (let [recipe (recipe/create-recipe 500 75)]
      (is (contains? recipe :flour))
      (is (contains? recipe :hydration))
      (is (contains? recipe :water))
      (is (contains? recipe :levain))
      (is (contains? recipe :salt))))
  
  (testing "Hydration calculates the ratio of flour to water in the recipe"
    (let [dough {:flour 500 :water 300}]
      (is (= (recipe/hydration dough) 60))))
  
  (testing "Flour is calculated correctly from total weight and hydration"
    (let [total 1000]
      (is (= (recipe/get-flour-from-totals total 1.00) 500))
      (is (= (recipe/get-flour-from-totals total 0.75) 571))
      (is (= (recipe/get-flour-from-totals total 0.45) 689)))))