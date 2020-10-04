; (defn meditate
;   "Return a transformed version of the string `string` based on the `calmness-level`"
;   [string calmness-level]
;   (println "Clojure Meditate v2.0")
;   (if (< calmness-level 4)
;     (str (clojure.string/upper-case string) ", I TELL YA!")
;     (if (<= 4 calmness-level 9)
;       (clojure.string/capitalize string)
;       (if (= 10 calmness-level)
;         (clojure.string/reverse string)))))

(defn meditate
  "Return a transformed version of the string `string` based on the `calmness-level`"
  [string calmness-level]
  (println "Clojure Meditate v2.0")
  (cond
    (< calmness-level 4) (str (clojure.string/upper-case string) ", I TELL YA!")
    (<= 4 calmness-level 9) (clojure.string/capitalize string)
    (= 10 calmness-level 10) (clojure.string/reverse string)))
