(ns elo
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.math.numeric-tower :as math]
    [semantic-csv.core :as sc]))


(def tennis-data-path "../data/match_scores_1991-2016_unindexed_csv.csv")


(defn load-csv
  [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (map #(select-keys % [:winner_name
                               :loser_name]))
         doall)))


(defn match-probability
  [player-1-rating player-2-rating]
  (/ 1
     (+ 1
        (math/expt 10
                   (/ (- player-2-rating player-1-rating)
                      400)))))


(def default-k-factor 32)
(def default-rating 400)


(defn recalculate-rating
  [{:keys [previous-rating expected-outcome real-outcome k-factor]}]
  (+ previous-rating (* k-factor (- real-outcome expected-outcome))))


(defn elo-world
  [file-path k-factor]
  (let
    [data (load-csv file-path)]
    (reduce
      (fn [{:keys [players] :as acc} {:keys [winner_name loser_name]}]
        (let [winner-rating (get players winner_name default-rating)
              loser-rating (get players loser_name default-rating)
              winner-prediction (match-probability winner-rating loser-rating)
              loser-prediction (- 1 winner-prediction)
              is-predictable-match (not= winner-prediction loser-prediction)
              prediction-was-right (> winner-prediction loser-prediction)
              new-winner-rating (recalculate-rating {:previous-rating winner-rating
                                                     :expected-outcome winner-prediction
                                                     :real-outcome 1
                                                     :k-factor k-factor})
              new-loser-rating (recalculate-rating {:previous-rating loser-rating
                                                    :expected-outcome loser-prediction
                                                    :real-outcome 0
                                                    :k-factor k-factor})]
          (->> acc
               (#(update % :total-matches inc))
               (#(update % :success-count (if prediction-was-right inc identity)))
               (#(update % :prediction-count (if is-predictable-match inc identity)))
               (#(assoc-in % [:players winner_name] new-winner-rating))
               (#(assoc-in % [:players loser_name] new-loser-rating)))))
      {:players {}
       :total-matches 0
       :success-count 0
       :prediction-count 0}
      data)))
