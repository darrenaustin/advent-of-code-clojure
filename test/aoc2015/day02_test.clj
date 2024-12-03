(ns aoc2015.day02-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2015.day02 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(deftest part1-test-examples
  (testing "part 1 examples"
    (is (= 58 (part1 day "2x3x4")))
    (is (= 43 (part1 day "1x1x10")))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 34 (part2 day "2x3x4")))
    (is (= 14 (part2 day "1x1x10")))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))

