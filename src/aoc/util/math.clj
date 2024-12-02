(ns aoc.util.math)

(defn sum [l] (reduce + l))

(defn distance [x y]
  (abs (- x y)))
