; 1
(def supported-currencies #{"Dollar",
                            "Japanes yen",
                            "Euro",
                            "Indian rupee",
                            "Brittish pound"})

; 2


(get supported-currencies "Dollar") ; "Dollar"
(get supported-currencies "Swiss franc") ; nil

; 3
(contains? supported-currencies "Dollar") ; true
(contains? supported-currencies "Swiss franc") ; false

; 4
(supported-currencies "Swiss franc") ; nil

; 5
; ("Dollar" supported-currencies)
; Execution error...

; 6
(conj supported-currencies "Monopoly Money")
; #{"Indian rupee" "Euro" "Dollar" "Brittish pound" "Monopoly Money" "Japanes yen"}

; 7
(conj supported-currencies "Monopoly Money" "Gold dragon" "Gil")
; #{"Gold dragon" "Indian rupee" "Euro" "Dollar" "Brittish pound" "Monopoly Money" "Japanes yen" "Gil"}

; 8
(disj supported-currencies "Dollar" "Brittish pound")
; #{"Indian rupee" "Euro" "Japanes yen"}
