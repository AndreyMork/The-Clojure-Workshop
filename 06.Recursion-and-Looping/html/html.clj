(ns html
  (:require
    [clojure.string :as string]))


(defn quoted
  [value]
  (let [quotes "\""]
    (str quotes value quotes)))


(defn render-key-value-pair
  [[key value]]
  (let [key-name (name key)
        quoted-value (if (boolean? value) value (quoted value))]
    (string/join "=" [key-name quoted-value])))


(defn render-attributes
  [attributes]
  (string/join " " (map render-key-value-pair attributes)))


(defn has-attributes?
  [html]
  (map? (second html)))


(defn tag-is-singleton?
  [tag]
  (#{:img :meta :link :input :br} tag))


(defn open-tag
  ([tag] (open-tag tag {}))
  ([tag attributes]
   (let [rendered-attributes (render-attributes attributes)]

     (str "<" (name tag) (when-not (empty? rendered-attributes) " ") rendered-attributes ">"))))


(defn close-tag
  [tag]
  (str "</" (name tag) ">"))


(defn render-tag
  ([tag] (render-tag tag {} ""))
  ([tag content]
   (render-tag tag {} content))
  ([tag attributes content]
   (str (open-tag tag attributes) content (close-tag tag))))


(defn render-singleton-tag
  [tag attributes]
  (open-tag tag (or attributes {})))


(defn my-hiccup
  [html]
  (cond
    (not (coll? html))
    (str html)

    (empty? html)
    ""

    (tag-is-singleton? (first html))
    (render-singleton-tag (first html) (second html))

    (not (has-attributes? html))
    (my-hiccup (concat [(first html)] [{}] (rest html)))

    :else
    (let [[tag attributes & content] html
          rendered-content (map my-hiccup content)]
      (render-tag tag attributes (string/join rendered-content)))))
