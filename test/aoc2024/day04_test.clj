(ns aoc2024.day04-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day04 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(def example-input
  "MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX")

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 18 (part1 day example-input)))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 9 (part2 day example-input)))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
