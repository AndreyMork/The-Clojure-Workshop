(defn max-value-by-status
  [field status users]
  (->> users (filter #(= (:status %) status)) (map field) (apply max 0)))


(defn min-value-by-status
  [field status users]
  (->> users (filter #(= (:status %) status)) (map field) (apply min 0)))
