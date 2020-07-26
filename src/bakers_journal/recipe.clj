(ns bakers-journal.recipe)

(defn ->decimal [percent]
  (/ percent 100))

(defn percentage [base percentage]
  (let [percent (->decimal percentage)]
    (int (* base percent))))

(defn water [hydration ratio]
  (let [flour (ratio :flour)
        water (percentage flour hydration)]
    (assoc ratio
           :hydration hydration
           :water water)))

(defn levain [ratio]
  (let [flour (ratio :flour)
        levain (percentage flour 20)]
    (assoc ratio
           :levain levain)))

(defn salt [ratio]
  (let [flour (ratio :flour)
        salt (percentage flour 2)]
    (assoc ratio
           :salt salt)))

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