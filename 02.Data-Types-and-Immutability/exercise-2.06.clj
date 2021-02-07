; 1
(def gemstone-db {:ruby {:name "Ruby"
                         :stock 120
                         :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
                         :properties {:dispersion 0.018
                                      :hardness 9.0
                                      :refractive-index [1.77 1.78]
                                      :color "Red"}}

                  :diamond {:name "Diamond"
                            :stock 10
                            :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579]
                            :properties {:dispersion 0.044
                                         :hardness 10
                                         :refractive-index [2.417 2.419]
                                         :color "Typically yellow, brown or gray to colorless"}}

                  :moissanite {:name "Moissanite"
                               :stock 45
                               :sales [7761 3220]
                               :properties {:dispersion 0.104
                                            :hardness 9.5
                                            :refractive-index [2.65 2.69]
                                            :color "Colorless, green, yellow"}}})

; 2


(get (get (get gemstone-db :ruby) :properties) :hardness) ; 9.0

; 3
(:hardness (:properties (:ruby gemstone-db))) ; 9.0

; 4
(get-in gemstone-db [:ruby :properties :hardness]) ; 9.0

; 5
(defn durability
  [db gemstone]
  (get-in db [gemstone :properties :hardness]))

; 6
(durability gemstone-db :ruby) ; 9.0
(durability gemstone-db :moissanite) ; 9.5

; 7
(assoc
 (:ruby gemstone-db)
 :properties
 {:color "Near colorless through pink through all shades of red to a deep crimson"})
; {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:color "Near colorless through pink through all shades of red to a deep crimson"}}

;8
(into {:a 1 :b 2} {:c 3})
;{:a 1, :b 2, :c 3}

;9
(update
 (:ruby gemstone-db)
 :properties
 into {:color "Near colorless through pink through all shades of red to a deep crimson"})
; {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Near colorless through pink through all shades of red to a deep crimson"}}

; 10
(assoc-in
 gemstone-db
 [:ruby :properties :color]
 "Near colorless through pink through all shades of red to a deep crimson")
; {:ruby {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Near colorless through pink through all shades of red to a deep crimson"}}, :diamond {:name "Diamond", :stock 10, :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579], :properties {:dispersion 0.044, :hardness 10, :refractive-index [2.417 2.419], :color "Typically yellow, brown or gray to colorless"}}, :moissanite {:name "Moissanite", :stock 45, :sales [7761 3220], :properties {:dispersion 0.104, :hardness 9.5, :refractive-index [2.65 2.69], :color "Colorless, green, yellow"}}}
; 11
(defn change-color
  [db gemstone new-color]
  (assoc-in
   gemstone-db
   [gemstone :properties :color]
   new-color))

; 12
(change-color gemstone-db :ruby "Some kind of red")
; {:ruby {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Some kind of red"}}, :diamond {:name "Diamond", :stock 10, :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579], :properties {:dispersion 0.044, :hardness 10, :refractive-index [2.417 2.419], :color "Typically yellow, brown or gray to colorless"}}, :moissanite {:name "Moissanite", :stock 45, :sales [7761 3220], :properties {:dispersion 0.104, :hardness 9.5, :refractive-index [2.65 2.69], :color "Colorless, green, yellow"}}}
; 13
(update-in gemstone-db [:diamond :stock] dec)
; {:ruby {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Red"}}, :diamond {:name "Diamond", :stock 9, :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579], :properties {:dispersion 0.044, :hardness 10, :refractive-index [2.417 2.419], :color "Typically yellow, brown or gray to colorless"}}, :moissanite {:name "Moissanite", :stock 45, :sales [7761 3220], :properties {:dispersion 0.104, :hardness 9.5, :refractive-index [2.65 2.69], :color "Colorless, green, yellow"}}}

; 14
(set! *print-level* 2)
(update-in gemstone-db [:diamond :stock] dec)
; {:ruby {:name "Ruby", :stock 120, :sales #, :properties #}, :diamond {:name "Diamond", :stock 9, :sales #, :properties #}, :moissanite {:name "Moissanite", :stock 45, :sales #, :properties #}}

; 15
(update-in gemstone-db [:diamond :sales] conj 999)
; {:ruby {:name "Ruby", :stock 120, :sales #, :properties #}, :diamond {:name "Diamond", :stock 10, :sales #, :properties #}, :moissanite {:name "Moissanite", :stock 45, :sales #, :properties #}}

; 16
(set! *print-level* nil)
(update-in gemstone-db [:diamond :sales] conj 999)
; {:ruby {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Red"}}, :diamond {:name "Diamond", :stock 10, :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579 999], :properties {:dispersion 0.044, :hardness 10, :refractive-index [2.417 2.419], :color "Typically yellow, brown or gray to colorless"}}, :moissanite {:name "Moissanite", :stock 45, :sales [7761 3220], :properties {:dispersion 0.104, :hardness 9.5, :refractive-index [2.65 2.69], :color "Colorless, green, yellow"}}}

; 17
(defn sell
  [db gemstone client-id]
  (let [clients-updated-db (update-in db [gemstone :sales] conj client-id)]
    (update-in clients-updated-db [gemstone :stock] dec)))

; 18
(sell gemstone-db :moissanite 123)
; {:ruby {:name "Ruby", :stock 120, :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712], :properties {:dispersion 0.018, :hardness 9.0, :refractive-index [1.77 1.78], :color "Red"}}, :diamond {:name "Diamond", :stock 10, :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579], :properties {:dispersion 0.044, :hardness 10, :refractive-index [2.417 2.419], :color "Typically yellow, brown or gray to colorless"}}, :moissanite {:name "Moissanite", :stock 44, :sales [7761 3220 123], :properties {:dispersion 0.104, :hardness 9.5, :refractive-index [2.65 2.69], :color "Colorless, green, yellow"}}}
