(def favorite-fruit
  {:name "Kiwi",
   :color "Green",
   :kcal_per_100g 61,
   :distinguish_mark "Hairy"})


(get favorite-fruit :name) ; "Kiwi"
(get favorite-fruit :color) ; "Green"

(get favorite-fruit :taste) ; nil
(get favorite-fruit :taste "Very good 8/10") ; "Very good 8/10"

(favorite-fruit :color) ; "Green"
(:color favorite-fruit) ; "Green"

(:shape favorite-fruit "egg-like") ; "egg-like"
(favorite-fruit :shape "egg-like") ; "egg-like"

(assoc favorite-fruit :shape "egg")
; {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy", :shape "egg"}

(assoc favorite-fruit :color "Brown")
; {:name "Kiwi", :color "Brown", :kcal_per_100g 61, :distinguish_mark "Hairy"}

(assoc favorite-fruit :yearly_production_in_tonnes {:china 2025000,
                                                    :italy 541000,
                                                    :new_zealand 412000,
                                                    :iran 311000,
                                                    :chile 225000})


(assoc favorite-fruit :kcal_per_100g (- (:kcal_per_100g favorite-fruit) 1))
; {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}
(update favorite-fruit :kcal_per_100g dec)
; {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}

(update favorite-fruit :kcal_per_100g - 10)
; {:name "Kiwi", :color "Green", :kcal_per_100g 51, :distinguish_mark "Hairy"}

(dissoc favorite-fruit :distinguish_mark)
; {:name "Kiwi", :color "Green", :kcal_per_100g 61}

(dissoc favorite-fruit :kcal_per_100g :color)
; {:name "Kiwi", :distinguish_mark "Hairy"}
