; 1
(def my-todo (list "Feed the cat" "Clean the bathroom" "Save the world"))

; 2
(cons "Go to work" my-todo)
; ("Go to work" "Feed the cat" "Clean the bathroom" "Save the world")

; 3
(conj my-todo "Go to work")
; ("Go to work" "Feed the cat" "Clean the bathroom" "Save the world")

; 4
(conj my-todo "Go to work" "Wash my socks")
; ("Wash my socks" "Go to work" "Feed the cat" "Clean the bathroom" "Save the world")

; 5
(first my-todo)
; "Feed the cat"

; 6
(rest my-todo)
; ("Clean the bathroom" "Save the world")

; 7
(nth my-todo 2)
; "Save the world"
