(ns aoc.util.collection)

(defn transpose [coll]
  (apply map vector coll))

(defn indexed [coll]
  (map vector (range (count coll)) coll))
