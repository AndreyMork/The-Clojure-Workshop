(defn co2-estimate
  "Returns a (conservative) year's estimate of carbon dioxide parts per million in the atmosphere"
  [year]
  (let [base-year 2006
        base-co2 382
        diff-year (- year base-year)]
    (+ 382 (* diff-year 2))))
