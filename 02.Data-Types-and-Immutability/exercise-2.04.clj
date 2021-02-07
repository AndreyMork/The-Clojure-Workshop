; 1
(get [:a :b :c] 0) ; :a
(get [:a :b :c] 2) ; :c
(get [:a :b :c] 10) ; nil

; 2
(def fibonacci [0 1 1 2 3 5 8])
(get fibonacci 6) ; 8

; 3
(fibonacci 6) ; 8

; 4
(conj fibonacci 13 21)
; [0 1 1 2 3 5 8 13 21]

; 5
(let [size (count fibonacci)
      last-number (last fibonacci)
      second-to-last-number (fibonacci (- size 2))]
  (conj fibonacci (+ last-number second-to-last-number)))
; [0 1 1 2 3 5 8 13]
