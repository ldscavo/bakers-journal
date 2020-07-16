(ns baking-diary.recipe)

(defn water [hydration ratio]
  (let [flour (ratio :flour)
        percent (/ hydration 100)]
    (assoc ratio
           :hydration hydration
           :water (int (* flour percent)))))

(defn levain [ratio]
  (let [flour (ratio :flour)]
    (assoc ratio :levain (int (* flour 0.2)))))

(defn salt [ratio]
  (let [flour (ratio :flour)]
    (assoc ratio :salt (int (* flour 0.02)))))

(defn create-recipe [flour hydration]
  (->> {:flour flour}
       (water hydration)
       (levain)
       (salt)))

(defn hydration [ratio]
  (let [flour (:flour ratio)
        water (:water ratio)]
    (->> (/ water flour)
         (* 100))))