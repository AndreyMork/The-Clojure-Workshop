; 1
(def mapjet-booking
  {:id 8773
   :customer-name "Alice Smith"
   :catering-notes "Vegetarian on Sundays"
   :flights [{:from {:lat 48.9615
                     :lon 2.4372
                     :name "Paris Le Bourget Airport"},
              :to {:lat 37.742
                   :lon -25.6976
                   :name "Ponta Delgada Airport"}},
             {:from {:lat 37.742
                     :lon -25.6976
                     :name "Ponta Delgada Airport"},
              :to {:lat 48.9615
                   :lon 2.4372
                   :name "Paris Le Bourget Airport"}}]})

; 2
(let [{:keys [customer-name flights]} mapjet-booking]
  (println (str customer-name " booked " (count flights) " flights.")))

; 3
; (defn print-mapjet-flight
;   [flight]
;   (let [{:keys [from to]} flight
;         {lat-from :lat lon-from :lon} from
;         {lat-to :lat lon-to :lon} to]
;     (println (str "Flying from: Lat " lat-from " Lon " lon-from " Flying to: Lat " lat-to " Lon " lon-to))))

; 4
(defn print-mapjet-flight
  [flight]
  (let [{{lat-from :lat lon-from :lon} :from
         {lat-to :lat lon-to :lon} :to} flight]
    (println (str "Flying from: Lat " lat-from " Lon " lon-from " Flying to: Lat " lat-to " Lon " lon-to))))

; 5
(defn print-mapjet-booking
  [booking]
  (let [{:keys [customer-name flights]} booking]
    (println (str customer-name " booked " (count flights) " flights."))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-mapjet-flight flight1)) flights
      (when flight2 (print-mapjet-flight flight2))
      (when flight3 (print-mapjet-flight flight3)))))
