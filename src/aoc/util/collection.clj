(ns aoc.util.collection)

(defn transpose [coll]
  (apply map vector coll))
