(ns aoc.util.grid
  (:require
   [clojure.string :as str]))

(defn parse-grid [input]
  (let [lines (str/split-lines input)]
    (into {}
          (for [x (range (count (first lines)))
                y (range (count lines))]
            [[x y] (get-in lines [y x])]))))

(def origin [0 0])

(defn vec+ [a b] (map + a b))
(defn vec- [a b] (map - a b))
