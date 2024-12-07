(ns aoc2024.day07-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day07 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(def example-input "190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20")

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 3749 (part1 day example-input)))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 11387 (part2 day example-input)))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
