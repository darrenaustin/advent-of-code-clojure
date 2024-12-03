(ns aoc.core
  (:gen-class)
  (:require
   [aoc.day :as day]
   [aoc2015.year :as year2015]
   [aoc2024.year :as year2024]))

(def days
  (concat year2015/days
          year2024/days))

(defn -main [& _]
  ;; TODO - implement command line year and day filter arguments
  (doseq [day days]
    (day/execute day)))
