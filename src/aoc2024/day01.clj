(ns aoc2024.day01
  (:require
   [aoc.util.collection :as c]
   [aoc.util.math :as m]
   [aoc.util.string :as s]))

(defn part1 [input]
  (let [[left right]
        (->> input
             s/lines
             (map s/parse-nums)
             c/transpose
             (map sort))]
    (m/sum (map m/distance left right))))

(defn similarity [x freqs]
  (* x (get freqs x 0)))

(defn part2 [input]
  (let [[left right]
        (->> input
             s/lines
             (map s/parse-nums)
             c/transpose
             (map sort))
        freqs (frequencies right)]
    (m/sum (map #(similarity % freqs) left))))

(def day {:year 2024 :day 1,
          :name "Historian Hysteria"
          :part1 part1, :part2 part2})
