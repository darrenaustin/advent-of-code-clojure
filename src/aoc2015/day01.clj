(ns aoc2015.day01
  (:require
   [aoc.util.math :as m]))

(def floor-dirs {\( 1, \) -1})

(defn part1 [input]
  (m/sum (map floor-dirs input)))

(defn part2 [input]
  ;; TODO: find a cleaner solution to this
  (second
   (first
    (filter (fn [[floor _]] (< floor 0))
            (reduce (fn [stops dir]
                      (let [[floor idx] (peek stops)]
                        (conj stops [(+ floor (floor-dirs dir)) (inc idx)])))
                    [[0 0]]
                    input)))))

(def day {:year 2015 :day 1,
          :name "Not Quite Lisp"
          :part1 part1, :part2 part2})
