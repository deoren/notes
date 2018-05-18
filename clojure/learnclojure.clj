;; LightTable 0.8.1: http://lighttable.com/, https://github.com/LightTable/LightTable
;; Unofficial "LightTable Square Fix" https://github.com/ArchI3Chris/LightTable-Square-Fix
;;
;; Ubuntu 18.04
;; OpenJDK 8 (https://codeyarns.com/2015/12/01/how-to-set-default-java-version-in-ubuntu/)
;;
;; https://www.safaribooksonline.com/library/view/learning-clojure/9781771373623/

;; Scratch notes from going through the Learning Clojure video series available
;; on safaribooksonline.com

(ns learnclojure)

(def x "Hello Clojure")

(let [x "Steve"]
  (print "Hello, " x))


(print x)

(str x)

(if (empty? x)
  "X is empty"
  "X is not empty")


(if nil
  "Yes" "No")

(if (empty? x)
  nil
    (do
      (println "Ok")
      :ok))


(let [x ""]
  (if (empty? x)
    (do
      (println "Nope")
    )
      (do
        (println "Ok")
        :ok)
    )
  )


(if-not (empty? x)
  (do
    (println "Ok")
    :ok))

; Not 100% sure what I'm seeing here. Is the ':ok' keyword being used to
; lookup a result?
when (not (empty? x)) :ok)
(when (empty? x) :ok)
