(defn make-chapter
  [chapter-name]
  (fn [filename]
    (let [fullname (str chapter-name "/" filename)]
      (load-file fullname))))

(def chapter1 (make-chapter "01.Hello-REPL"))
(def chapter2 (make-chapter "02.Data-Types-and-Immutability"))
(def chapter3 (make-chapter "03.Functions-in-Depth"))
