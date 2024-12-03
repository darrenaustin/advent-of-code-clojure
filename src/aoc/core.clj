(ns aoc.core
  (:require
   [aoc.day :as day]
   [aoc2015.year :as year2015]
   [aoc2024.year :as year2024])
  (:gen-class))

(def days
  (concat year2015/days
          year2024/days))

(defn matching-day? [day filters]
  (some
   (fn [{:keys [year day-num]}]
     (and (= (:year day) year)
          (or (= (:day-num day) day-num)
              (nil? day-num))))
   filters))

(defn filter-days [filters days]
  (if (seq filters)
    (filter #(matching-day? % filters) days)
    days))

(defn parse-filter [s]
  (if-let [[_ year _ day-num] (re-find #"(\d+)(\.(\d+))?" s)]
    {:year (when year (Integer/parseInt year))
     :day-num (when day-num (Integer/parseInt day-num))}
    (throw (Exception. (format "Invalid year.day argument: %s" s)))))

(defn -main [& args]
  (let [filters (map parse-filter args)]
    (doseq [day (filter-days filters days)]
      (day/execute day))))
