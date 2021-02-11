(ns groceries)


(def grocery-articles
  [{:name "Flour"
    :weight 1000 ; grams
    :max-dimension 140 ; millimeters
    }
   {:name "Bread"
    :weight 350
    :max-dimension 250}
   {:name "Potatoes"
    :weight 2500
    :max-dimension 340}
   {:name "Pepper"
    :weight 85
    :max-dimension 90}
   {:name "Ice cream"
    :weight 450
    :max-dimension 200}
   {:name "Green beans"
    :weight 300
    :max-dimension 120}
   {:name "Olive oil"
    :weight 400
    :max-dimension 280}])


(defn article-stream
  [n]
  (repeatedly n #(rand-nth grocery-articles)))


(defn full-bag?
  [items]
  (let [weight (apply + (map :weight items))
        size (apply + (map :max-dimension items))]
    (or (> weight 3200)
        (> size 800))))


(defn- bag-sequences*
  [{:keys [current-bag bags] :as acc} [first-item & rest-items]]
  (cond
    (not first-item)
    (conj bags current-bag)

    (full-bag? (conj current-bag first-item))
    (recur {:current-bag [first-item] :bags (conj bags current-bag)} rest-items)

    :else
    (recur (update acc :current-bag conj first-item) rest-items)))


; (defn bag-sequences
; [stream]
; (bag-sequences* {:bags [] :current-bag []} stream))

(defn bag-sequences
  [stream]
  (loop [acc {:current-bag [] :bags []}
         [first-item & rest-items] stream]
    (let [{:keys [current-bag bags]} acc]
      (cond
        (not first-item)
        (conj bags current-bag)

        (full-bag? (conj current-bag first-item))
        (recur {:current-bag [first-item] :bags (conj bags current-bag)} rest-items)

        :else
        (recur (update acc :current-bag conj first-item) rest-items)))))
