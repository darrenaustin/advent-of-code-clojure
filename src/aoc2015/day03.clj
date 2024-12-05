;; https://adventofcode.com/2015/day/03

(ns aoc2015.day03)

(def dirs {\^ [0 -1]
           \v [0 1]
           \> [1 0]
           \< [-1 0]})

(defn packages-delivered [input locations packages]
  (if (seq input)
    (let [location (map + (first locations) (dirs (first input)))]
      (recur (rest input)
             (conj (into [] (rest locations)) location)
             (conj packages location)))
    packages))

(defn part1 [input]
  (count (packages-delivered input [[0 0]] #{[0 0]})))

(defn part2 [input]
  (count (packages-delivered input [[0 0] [0 0]] #{[0 0]})))

(def day {:year 2015 :day-num 3,
          :name "Perfectly Spherical Houses in a Vacuum"
          :part1 part1, :part2 part2})

(comment
  (require '[aoc.day :as d])
  (part1 (d/day-input day))
  (part2 (d/day-input day))
  ;
  )
