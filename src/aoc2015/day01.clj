(ns aoc2015.day01
  (:require
   [aoc.util.math :as m]))

(def floor-dirs {\( 1, \) -1})

(defn part1 [input]
  (m/sum (map floor-dirs input)))

(defn part2 [input]
  (reduce
   (fn [stops dir]
     (let [[floor idx] (peek stops)
           [floor idx] [(+ floor (floor-dirs dir)) (inc idx)]]
       (if (< floor 0)
         (reduced idx)
         (conj stops [floor idx]))))
   [[0 0]]
   input))

(def day {:year 2015 :day 1,
          :name "Not Quite Lisp"
          :part1 part1, :part2 part2})
