;; https://adventofcode.com/2024/day/8
(ns aoc2024.day08
  (:require
   [aoc.day :as d]
   [clojure.string :as str]
   [clojure.math.combinatorics :as combo]))

(def input (d/day-input 2024 8))

;; TODO: cleanup the grid and the later traversals.

(defn grid-at
  ([grid [x y]] (grid-at grid x y))
  ([grid x y] (get (get grid y) x)))

(defn vec+ [[ax ay] [bx by]]
  [(+ ax bx) (+ ay by)])

(defn vec-
  ([[ax ay]] (vec- [0 0] [ax ay]))
  ([[ax ay] [bx by]]
   [(- ax bx) (- ay by)]))

(defn valid-pos? [[x y] [width height]]
  (and (< -1 x width)
       (< -1 y height)))

(defn find-antennas [grid]
  (let [{:keys [bounds grid]} grid
        [width height] bounds]
    (into {}
          (map (fn [[key value]]
                 [key (mapcat rest value)])
               (group-by first
                         (for [x (range width) y (range height)
                               :let [v (grid-at grid x y)]
                               :when (not= \. v)]
                           [v [x y]]))))))

(defn traverse-line [pos dir bounds limit]
  (let [points (take-while #(valid-pos? % bounds)
                           (iterate #(vec+ % dir) pos))]
    (if limit
      (take limit points)
      points)))

(defn antinodes [[a b] bounds]
  (let [v (vec- b a)
        a1 (vec- a v)
        a2 (vec+ b v)]
    (filter #(valid-pos? % bounds)
            [a1 a2])))

(defn antinodes-on-line [[a b] bounds & {:keys [limit] :or {limit nil}}]
  (let [v (vec- b a)]
    (concat (traverse-line a (vec- v) bounds limit)
            (traverse-line b v bounds limit))))

(defn parse-map [input]
  (let [grid (->> input
                  str/split-lines
                  (map vec)
                  (into []))
        width (count (first grid))
        height (count grid)]
    {:grid grid
     :bounds [width height]}))

(defn part1 [input]
  (let [g (parse-map input)
        bounds (:bounds g)
        antennas (find-antennas g)]
    (count
     (set
      (mapcat (fn [[_ ts]]
                (mapcat #(antinodes % bounds)
                        (combo/combinations ts 2)))
              antennas)))))

(defn part2 [input]
  (let [g (parse-map input)
        bounds (:bounds g)
        antennas (find-antennas g)]
    (count
     (set
      (mapcat (fn [[_ ts]]
                (mapcat #(antinodes-on-line % bounds)
                        (combo/combinations ts 2)))
              antennas)))))
