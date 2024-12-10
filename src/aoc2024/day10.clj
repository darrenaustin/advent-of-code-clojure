;; https://adventofcode.com/2024/day/10
(ns aoc2024.day10
  (:require
   [aoc.day :as d]
   [aoc.util.grid :refer :all]
   [aoc.util.math :as m]
   [aoc.util.string :as s]))

(def input (d/day-input 2024 10))

(defn update-goal-from [grid goals n pos]
  (let [gs (goals pos)
        neighbors (filter #(and (contains? grid %)
                                (= (grid %) (dec n)))
                          (orthoginal-from pos))]
    (reduce (fn [goals neighbor]
              (update goals neighbor concat gs))
            goals
            neighbors)))

(defn summit-reachable-map [grid]
  (let [groups (into {} (mapv (fn [[k v]] [k (mapv first v)])
                              (group-by (fn [[_ v]] v) grid)))]
    (reduce (fn [gs n]
              (let [n-pos (groups n)]
                (reduce (fn [gs pos] (update-goal-from grid gs n pos))
                        gs
                        n-pos)))
            (into {} (map (fn [p] [p [p]]) (groups 9)))
            (range 9 0 -1))))

(defn part1 [input]
  (let [grid (parse-grid input s/digit)
        trailheads (locs-where grid #{0})
        reachable-summits (summit-reachable-map grid)]
    (m/sum (map count (map set (map reachable-summits trailheads))))))

(defn part2 [input]
    (let [grid (parse-grid input s/digit)
        trailheads (locs-where grid #{0})
        reachable-summits (summit-reachable-map grid)]
    (m/sum (map count (map reachable-summits trailheads)))))
