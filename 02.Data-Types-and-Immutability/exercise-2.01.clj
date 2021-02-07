(defn encode-letter
  [s offset]
  (let
   [code (Math/pow (+ offset (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace
     s
     #"\w"
     (fn [letter] (encode-letter letter number-of-words)))))

(defn decode-letter
  [code offset]
  (let [number (Integer/parseInt (subs code 1))
        letter (char (- (Math/sqrt number) offset))]
    (str letter)))

(defn decode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+" (fn [s] (decode-letter s number-of-words)))))
