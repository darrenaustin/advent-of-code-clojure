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
  ([grid x y] (and (<= 0 x (dec (:width grid)))
                   (<= 0 y (dec (:height grid))))))

(defn grid-positions [grid]
  (for [x (range (:width grid)) y (range (:height grid))]
    [x y]))

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

(defn walk-guard [floor visited]
  (let [guard (:guard floor)
        next-pos (map + guard (forward (:dir floor)))
        walked-off? (not (valid-pos? floor next-pos))
        blocked? (and (not walked-off?)
                      (= \# (grid-at (:grid floor) next-pos)))]
    (cond
      walked-off? visited
      blocked? (recur (assoc floor :dir (right-turn (:dir floor))) visited)
      :else (recur (assoc floor :guard next-pos) (conj visited next-pos)))))

(defn looping-guard? [floor]
  (loop [floor floor visited #{}]
    (let [{:keys [guard dir]} floor]
      (if (contains? visited {:pos guard :dir dir})
        true
        (let [next-pos (map + guard (forward dir))]
          (cond
            ;; Walked off edge.
            (not (valid-pos? floor next-pos)) false

            ;; Blocked, so turn right
            (= \# (grid-at (:grid floor) next-pos)) (recur (assoc floor :dir (right-turn dir))
                                                           visited)
            ;; Walk to the next position and add the current to visited
            :else (recur (assoc floor :guard next-pos)
                         (conj visited {:pos guard :dir dir}))))))))

(defn place-obstruction [floor pos]
  (assoc floor :grid (set-at (:grid floor) pos \#)))

(defn part1 [input]
  (let [floor (parse-map input)]
    (count (walk-guard floor #{(:guard floor)}))))

;; TODO: this is *way* too slow.
(defn part2 [input]
  (let [floor (parse-map input)
        guard (:guard floor)
        open-spaces (filter #(and (not= % guard)
                                  (= \. (grid-at (:grid floor) %)))
                            (grid-positions floor))]
    (count (filter #(looping-guard? (place-obstruction floor %))
                   open-spaces))))

(def day {:year 2024 :day-num 6,
          :name ""
          :part1 part1, :part2 part2})

(comment
  (require '[aoc.day :as d])
  (part1 (d/day-input day))
  (part2 (d/day-input day))
  ;
  )
