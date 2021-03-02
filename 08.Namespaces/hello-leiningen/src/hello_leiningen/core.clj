(ns hello-leiningen.core
  (:gen-class)
  (:require
    [java-time :as time]
    [clojure.string :as string]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (time/local-time)))
