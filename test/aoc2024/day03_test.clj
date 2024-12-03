(ns aoc2024.day03-test
  (:require [aoc.day :refer [day-answers part1 part2]]
            [aoc2024.day03 :refer [day]]
            [clojure.test :refer [deftest is testing]]))

(deftest part1-test-example
  (testing "part 1 example"
    (is (= 161 (part1 day "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")))))

(deftest part1-test
  (testing "part1 correct answer"
    (let [answer (:answer1 (day-answers day))]
      (is (= answer (part1 day))))))

(deftest part2-test-example
  (testing "part 2 example"
    (is (= 48 (part2 day "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")))))

(deftest part2-test
  (testing "part2 correct answer"
    (let [answer (:answer2 (day-answers day))]
      (is (= answer (part2 day))))))
