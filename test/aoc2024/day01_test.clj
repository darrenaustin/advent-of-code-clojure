(ns aoc2024.day01-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day01 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(def example-input
  "3   4
4   3
2   5
1   3
3   9
3   3")

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 11 (part1 day example-input)))))

(deftest part1-test
  (testing "part1 correct answer")
  (let [answer (:answer1 (day-answers day))]
    (is (= answer (part1 day)))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 31 (part2 day example-input)))))

(deftest part2-test
  (testing "part2 correct answer")
  (let [answer (:answer2 (day-answers day))]
    (is (= answer (part2 day)))))
