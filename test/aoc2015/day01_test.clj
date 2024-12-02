(ns aoc2015.day01-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2015.day01 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(deftest part1-test-examples
  (testing "part 1 examples"
    (is (= 0 (part1 day "(())")))
    (is (= 0 (part1 day "()()")))
    (is (= 3 (part1 day "(((")))
    (is (= 3 (part1 day "(()(()(")))
    (is (= 3 (part1 day "))(((((")))
    (is (= -1 (part1 day "())")))
    (is (= -1 (part1 day "))(")))
    (is (= -3 (part1 day ")))")))
    (is (= -3 (part1 day ")())())")))))

(deftest part1-test
  (testing "part1 correct answer")
  (let [answer (:answer1 (day-answers day))]
    (is (= answer (part1 day)))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 1 (part2 day ")")))
    (is (= 5 (part2 day "()())")))))

(deftest part2-test
  (testing "part2 correct answer")
  (let [answer (:answer2 (day-answers day))]
    (is (= answer (part2 day)))))

