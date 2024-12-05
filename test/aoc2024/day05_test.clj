(ns aoc2024.day05-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day05 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(def example-input "47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47")

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 143 (part1 day example-input)))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 123 (part2 day example-input)))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
