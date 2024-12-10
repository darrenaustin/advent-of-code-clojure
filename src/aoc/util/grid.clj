(ns aoc.util.grid
  (:require
   [clojure.string :as str :refer [join]]))

(def origin [0 0])

(def dir-nw [-1 -1])
(def dir-n  [0 -1])
(def dir-ne [1 -1])
(def dir-e  [1 0])
(def dir-se [1 1])
(def dir-s  [0 1])
(def dir-sw [-1 1])
(def dir-w  [-1 0])

(def dir-up dir-n)
(def dir-down dir-s)
(def dir-left dir-w)
(def dir-right dir-e)

(def cardinal-dirs
  [dir-nw dir-n dir-ne
   dir-w        dir-e
   dir-sw dir-s dir-se])

(def orthoginal-dirs
  [dir-up dir-right dir-down dir-left])

(defn vec+ [a b] (mapv + a b))
(defn vec- [a b] (mapv - a b))
(defn vec-n* [n a] (mapv #(* n %) a))

(defn orthoginal-from [pos]
  (map (partial vec+ pos) orthoginal-dirs))

(defn cardinal-from [pos]
  (map (partial vec+ pos) cardinal-dirs))

(defn parse-grid
  ([input] (parse-grid input identity))
  ([input value-fn]
   (let [lines (str/split-lines input)]
     (into {}
           (for [x (range (count (first lines)))
                 y (range (count lines))]
             [[x y] (value-fn (get-in lines [y x]))])))))

(defn bounds [grid]
  (reduce (fn [[min-loc max-loc] [loc _]]
            [(map min min-loc loc) (map max max-loc loc)])
          [[Integer/MAX_VALUE Integer/MAX_VALUE]
           [Integer/MIN_VALUE Integer/MIN_VALUE]]
          grid))

(defn grid->str-vec
  ([grid] (grid->str-vec grid \space))
  ([grid empty-value]
   (let [[[min-x min-y] [max-x max-y]] (bounds grid)]
     (into [] (for [y (range min-y (inc max-y))]
                (apply str (for [x (range min-x (inc max-x))]
                             (grid [x y] empty-value))))))))

(defn grid->str
  ([grid]
   (join "\n" (grid->str-vec grid)))
  ([grid empty-value]
   (join "\n" (grid->str-vec grid empty-value))))

(defn locs-where [grid pred]
  (map key (filter #(pred (val %)) grid)))

(defn loc-where [grid pred]
  (first (locs-where grid pred)))
