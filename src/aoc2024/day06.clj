;; https://adventofcode.com/2024/day/06

(ns aoc2024.day06
  (:require
   [clojure.string :as str]))

(defn grid-at
  ([grid [x y]] (grid-at grid x y))
  ([grid x y] (get (get grid y) x)))

(defn set-at
  ([grid [x y] value] (set-at grid x y value))
  ([grid x y value] (assoc-in grid [y x] value)))

(defn valid-pos?
  ([grid [x y]] (valid-pos? grid x y))
  ([grid x y] (and (< -1 x (:width grid))
                   (< -1 y (:height grid)))))

(defn parse-map [input]
  (let [grid (->> input
                  str/split-lines
                  (map vec)
                  (into []))
        width (count (first grid))
        height (count grid)
        guard (first (for [x (range width) y (range height)
                           :when (= (grid-at grid x y) \^)]
                       [x y]))]
    {:grid (set-at grid guard \.)
     :width width
     :height height
     :guard guard
     :dir :up}))

(def right-turn
  {:up :right
   :right :down
   :down :left
   :left :up})

(def forward
  {:up [0 -1]
   :right [1 0]
   :down [0 1]
   :left [-1 0]})

(defn walk-guard [floor]
  (loop [guard (:guard floor) dir (:dir floor) visited #{(:guard floor)}]
    (let [next-pos (map + guard (forward dir))]
      (cond
        ;; Walked off edge
        (not (valid-pos? floor next-pos)) visited

        ;; Blocked, so turn right
        (= \# (grid-at (:grid floor) next-pos)) (recur guard (right-turn dir) visited)

        ;; Walk to the next position and add the current to visited
        :else (recur next-pos dir (conj visited next-pos))))))

(defn looping-guard? [floor]
  (loop [guard (:guard floor) dir (:dir floor) visited #{}]
    (if (contains? visited [guard dir])
      true
      (let [next-pos (map + guard (forward dir))]
        (cond
          ;; Walked off edge.
          (not (valid-pos? floor next-pos)) false

          ;; Blocked, so turn right
          (= \# (grid-at (:grid floor) next-pos)) (recur guard (right-turn dir) visited)

          ;; Walk to the next position and add the current to visited
          :else (recur next-pos dir (conj visited [guard dir])))))))

(defn place-obstruction [floor pos]
  (assoc floor :grid (set-at (:grid floor) pos \#)))

(defn part1 [input]
  (let [floor (parse-map input)]
    (count (walk-guard floor))))

;; TODO: this is *way* too slow.
;; Big performance suggestion from reddit discussion:
;; https://www.reddit.com/r/adventofcode/comments/1h7tovg/2024_day_6_solutions/
;; Only need to put obstacles in the path from part 1 as the guard
;; won't run into the others.
(defn part2 [input]
  (let [floor (parse-map input)
        guard (:guard floor)
        guard-path (disj (walk-guard floor) guard)]
    (count (filter #(looping-guard? (place-obstruction floor %))
                   guard-path))))

(def day {:year 2024 :day-num 6,
          :name "Guard Gallivant"
          :part1 part1, :part2 part2})

(comment
  (require '[aoc.day :as d])
  (part1 (d/day-input day))
  (part2 (d/day-input day))
  ;
  )
