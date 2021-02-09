(ns tennis
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [semantic-csv.core :as sc]))

; 4.12
(defn first-match
  [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         first)))


(defn five-matches
  [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (map #(select-keys % [:tourney_year_id
                               :winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won]))
         (take 5)
         doall)))


(defn five-matches-int-sets
  [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (map #(select-keys % [:tourney_year_id
                               :winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won]))
         (sc/cast-with {:winner_sets_won sc/->int
                        :loser_sets_won sc/->int})
         (take 5)
         doall)))

; 4.13
(defn federer-wins
  [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (filter #(= "Roger Federer" (:winner_name %)))
         (map #(select-keys % [:winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won
                               :winner_games_won
                               :loser_games_won
                               :tourney_year_id
                               :tourney_slug]))
         doall)))

; 4.14
(defn match-query
  [csv pred]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (sc/cast-with {:winner_sets_won sc/->int
                        :loser_sets_won sc/->int
                        :winner_games_won sc/->int
                        :loser_games_won sc/->int})
         (filter pred)
         (map #(select-keys % [:winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won
                               :winner_games_won
                               :loser_games_won
                               :tourney_year_id
                               :tourney_slug]))
         doall)))


(defn load-csv-file
  [file-path]
  (with-open [r (io/reader file-path)]
    (->> (csv/read-csv r)
         sc/mappify
         (sc/cast-with {:winner_sets_won sc/->int
                        :loser_sets_won sc/->int
                        :winner_games_won sc/->int
                        :loser_games_won sc/->int})
         (map #(select-keys % [:winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won
                               :winner_games_won
                               :loser_games_won
                               :tourney_year_id
                               :tourney_slug]))
         doall)))

; activity 4.02

(defn players-match?
  [player1 player2 {:keys [winner_name loser_name]}]
  (=
    (hash-set winner_name loser_name)
    #{player1 player2}))


(defn player-won?
  [player {:keys [winner_name]}]
  (= player winner_name))


(defn is-competitive-match?
  [{:keys [winner_sets_won loser_sets_won]}]
  (= 1 (- winner_sets_won loser_sets_won)))


(defn rivalry-data
  [csv-file-path player1 player2]
  (let
    [matches (match-query csv-file-path (partial players-match? player1 player2))
     player1-victories (filter (partial player-won? player1) matches)
     player2-victories (filter (partial player-won? player2) matches)]
    {:first-victory-player-1 (first player1-victories)
     :first-victory-player-2 (first player2-victories)
     :total-matches (count matches)
     :total-victories-player-1 (count player1-victories)
     :total-victories-player-2 (count player2-victories)
     :most-competitive-matches (filter is-competitive-match? matches)}))

