(ns aoc2015.day03-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2015.day03 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 2 (part1 day ">")))
    (is (= 4 (part1 day "^>v<")))
    (is (= 2 (part1 day "^v^v^v^v^v")))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 3 (part2 day "^v")))
    (is (= 3 (part2 day "^>v<")))
    (is (= 11 (part2 day "^v^v^v^v^v")))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
