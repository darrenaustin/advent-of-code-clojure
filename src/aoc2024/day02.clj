(ns aoc2024.day02
  (:require
   [aoc.util.math :as m]
   [aoc.util.string :as s]))

(defn parse-reports [input]
  (->> input
       s/lines
       (map s/parse-nums)))

(defn diffs [report]
  (map m/distance (butlast report) (rest report)))

(defn safe [report]
  (and (or (apply < report)
           (apply > report))
       (every? #(< 0 % 4) (diffs report))))

(defn part1 [input]
  (count (filter safe (parse-reports input))))

(defn missing-one [report]
  (let [report (vec report)]
    (loop [reports [] head [(first report)] tail (rest report)]
      (if (seq tail)
        (recur (conj reports (concat (butlast head) tail))
               (conj head (first tail))
               (rest tail))
        (conj reports (butlast head))))))

(defn safe-ish [report]
  (or (safe report)
      (some safe (missing-one report))))

(defn part2 [input]
  (count (filter safe-ish (parse-reports input))))

(def day {:year 2024 :day 2,
          :name "Red-Nosed Reports"
          :part1 part1, :part2 part2})
