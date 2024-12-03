(ns aoc2024.day02-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day02 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(def example-input "7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9")

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 2 (part1 day example-input)))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 4 (part2 day example-input)))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
