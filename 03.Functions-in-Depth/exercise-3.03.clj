; 1
(def weapon-damage
  {:fists 10.0
   :staff 35.0
   :sword 100.0
   :cast-iron-saucepan 150.0})

; 2
; (defn strike
;   ([target weapon]
;    (let [points (weapon weapon-damage)]
;      (if (= :gnomes (:camp target))
;        (update target :health + points)
;        (update target :health - points)))))

; 3
; (def enemy
;   {:name "Zulkaz" :health 250 :camp :trolls})

; 4
(def ally
  {:name "Carla" :health 80 :camp :gnomes})

; 5
; (defn strike
;   ([target weapon]
;    (let [points (weapon weapon-damage)]
;      (if (= :gnomes (:camp target))
;        (update target :health + points)
;        (let [armor (or (:armor target) 0)
;              damage (* points (- 1 armor))]
;          (update target :health - damage))))))

; 6
; (strike enemy :cast-iron-saucepan)

; 7
(def enemy
  {:name "Zulkaz" :health 250 :armor 0.8 :camp :trolls})

; 8
; (defn strike
;   ([{:keys [camp armor] :as target} weapon]
;    (let [points (weapon weapon-damage)]
;      (if (= :gnomes camp)
;        (update target :health + points)
;        (let [damage (* points (- 1 (or armor 0)))]
;          (update target :health - damage))))))

; 9
(defn strike
  ([{:keys [camp armor] :or {armor 0} :as target} weapon]
   (let [points (weapon weapon-damage)]
     (if (= :gnomes camp)
       (update target :health + points)
       (let [damage (* points (- 1 armor))]
         (update target :health - damage))))))
