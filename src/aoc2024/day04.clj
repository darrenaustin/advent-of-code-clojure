;; https://adventofcode.com/2024/day/04

(ns aoc2024.day04
  (:require
   [aoc.util.math :as m]
   [clojure.string :as str]))

(defn parse-grid [input]
  (->> input
       str/split-lines
       (map vec)
       (into [])))

(defn grid-value [grid [x y]]
  (let [width (count (first grid))
        height (count grid)]
    (when (and (< -1 x width) (< -1 y height))
      (nth (nth grid x) y))))

;; cardinal directions
(def dirs
  (for [x [-1 0 1] y [-1 0 1] :when (not= 0 x y)]
    [x y]))

(def xmas (map-indexed (fn [idx letter] [idx letter]) "XMAS"))

(defn xmas-in-dir? [grid [x y] [dx dy]]
  (every?
   (fn [[idx letter]]
     (= (grid-value grid [(+ x (* idx dx)) (+ y (* idx dy))]) letter))
   xmas))

(defn count-xmas-at [grid pos]
  (count (filter #(xmas-in-dir? grid pos %) dirs)))

(defn xmas-in [grid]
  (m/sum
   (for [y (range 0 (count grid))
         x (range 0 (count (first grid)))]
     (count-xmas-at grid [x y]))))

(defn part1 [input]
  (xmas-in (parse-grid input)))

(defn x-mas-at? [grid pos]
  ;; assumes there is an 'A' in the grid at pos.
  (let [left-up(grid-value grid (mapv + pos [-1 -1]))
        left-down (grid-value grid (mapv + pos [-1 1]))
        right-up (grid-value grid (mapv + pos [1 -1]))
        right-down (grid-value grid (mapv + pos [1 1]))]
    (= #{\M \S} (set [left-up right-down]) (set [left-down right-up]))))

(defn x-mas-in [grid]
  (count
   (let [a-positions
         (for [y (range 0 (count grid))
               x (range 0 (count (first grid)))
               :let [pos [x y]]
               :when (= \A (grid-value grid pos))]
           pos)]
     (filter #(x-mas-at? grid %) a-positions))))

(defn part2 [input]
  (x-mas-in (parse-grid input)))

(def day {:year 2024 :day-num 4,
          :name "Ceres Search"
          :part1 part1, :part2 part2})

(comment
  (require '[aoc.day :as d])
  (part1 (d/day-input day))
  (part2 (d/day-input day))
  ;
  )
