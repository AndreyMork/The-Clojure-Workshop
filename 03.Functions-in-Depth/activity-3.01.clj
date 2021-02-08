(def walking-speed 4)
(def driving-speed 70)

(def paris {:lat 48.856483 :lon 2.352413})
(def bordeaux {:lat 44.834999 :lon -0.575490})
(def london {:lat 51.507351 :lon -0.127758})
(def manchester {:lat 53.480759 :lon -2.242631})

(defn compute-distance
  [{lat-from :lat lon-from :lon}
   {lat-to :lat lon-to :lon}]
  (let [squared #(Math/pow % 2)
        deglen 110.25
        lat-diff (- lat-to lat-from)
        lon-diff (- lon-to lon-from)
        subterm (* lon-diff (Math/cos lat-to))
        x (squared lat-diff)
        y (squared subterm)]
    (* deglen (Math/sqrt (+ x y)))))

(defmulti itinerary :transport)

(defmethod itinerary
  :walking
  [{:keys [from to]}]
  (let [distance (compute-distance from to)
        duration (/ distance walking-speed)
        cost 0]
    {:distance distance
     :duration duration
     :cost cost}))

(def vehicle-cost-fns
  {:sporche (partial * 0.12 1.5)
   :tayato (partial * 0.07 1.5)
   :sleta (partial * 0.2 0.1)})

(defmethod itinerary
  :driving
  [{:keys [from to vehicle]}]
  (let [distance (compute-distance from to)
        duration (/ distance driving-speed)
        cost ((vehicle-cost-fns vehicle) distance)]
    {:distance distance
     :duration duration
     :cost cost}))
