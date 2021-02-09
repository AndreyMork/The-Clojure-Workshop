(def chapters
  {1 "01.Hello-REPL"
   2 "02.Data-Types-and-Immutability"
   3 "03.Functions-in-Depth"
   4 "04.Mapping-and-Filtering"})


(def extension ".clj")


(defn pad-number
  [n]
  (if (< n 10) (str "0" n) (str n)))


(defn build-activity-filename
  [chapter-number activity-number]
  (str "activity-" chapter-number "." (pad-number activity-number) extension))


(def build-filename-fns
  {:activity build-activity-filename})


(defn build-chapter-path
  [chapter-number file-type file-number]
  (let [chapter-name (chapters chapter-number)
        filename ((build-filename-fns file-type) chapter-number file-number)]
    (clojure.string/join "/" [chapter-name filename])))


(def chapter (comp load-file build-chapter-path))
