(ns tools.new-day
  (:require [aoc.day :as d]
            [clojure.java.io :as io]
            [tools.update-days :as upd])
  (:gen-class))

(defn create-src-year-dir [year]
  (let [dir (io/file (format "src/aoc%04d" year))]
    (when (not (.isDirectory dir))
      (.mkdirs dir))))

(defn create-day-src-file [year day-num]
  (create-src-year-dir year)
  ;; TODO: looking to a templating solution for this and the other generated files.
  (let [file (io/file (format "src/aoc%04d/day%02d.clj" year day-num))]
    (when (not (.exists file))
      (with-open [wrtr (io/writer file)]
        (.write wrtr (format ";; https://adventofcode.com/%04d/day/%02d\n" year day-num))
        (.write wrtr "\n")
        (.write wrtr (format "(ns aoc%04d.day%02d\n" year day-num))
        (.write wrtr "  (:require\n")
        (.write wrtr "   [aoc.util.math :as m]\n")
        (.write wrtr "   [aoc.util.string :as s]))\n")
        (.write wrtr "\n")
        (.write wrtr "(defn part1 [input])\n")
        (.write wrtr "\n")
        (.write wrtr "(defn part2 [input])\n")
        (.write wrtr "\n")
        (.write wrtr (format "(def day {:year %d :day-num %d,\n" year day-num))
        (.write wrtr "          :name \"\"\n")
        (.write wrtr "          :part1 part1, :part2 part2})\n")
        (.write wrtr "\n")
        (.write wrtr "(comment\n")
        (.write wrtr "  (require '[aoc.day :as d])\n")
        (.write wrtr "  (part1 (d/day-input day))\n")
        (.write wrtr "  (part2 (d/day-input day))\n")
        (.write wrtr "  ;\n")
        (.write wrtr "  )\n")))))

(defn create-test-year-dir [year]
  (let [dir (io/file (format "test/aoc%04d" year))]
    (when (not (.isDirectory dir))
      (.mkdirs dir))))

(defn create-day-test-file [year day-num]
  (create-test-year-dir year)
  (let [file (io/file (format "test/aoc%04d/day%02d_test.clj" year day-num))]
    (when (not (.exists file))
      (with-open [wrtr (io/writer file)]
        (.write wrtr (format "(ns aoc%04d.day%02d-test\n" year day-num))
        (.write wrtr "  (:require [aoc.day :refer [day-answers part1 part2]]\n")
        (.write wrtr (format "            [aoc%04d.day%02d :refer [day]]\n" year day-num))
        (.write wrtr "            [clojure.test :refer [deftest is testing]]))\n")
        (.write wrtr "\n")
        (.write wrtr "(deftest part1-test-example\n")
        (.write wrtr "  ;; TODO: implement\n")
        (.write wrtr "  (testing \"part 1 example\"\n")
        (.write wrtr "    (is (= nil (part1 day \"\")))))\n")
        (.write wrtr "\n")
        (.write wrtr "(deftest part2-test-example\n")
        (.write wrtr "  ;; TODO: implement\n")
        (.write wrtr "  (testing \"part 2 example\"\n")
        (.write wrtr "    (is (= nil (part2 day \"\")))))\n")
        (.write wrtr "\n")
        (.write wrtr "(deftest part1-test\n")
        (.write wrtr "  (testing \"part1 correct answer\"\n")
        (.write wrtr "    (let [answer (:answer1 (day-answers day))]\n")
        (.write wrtr "      (is (= answer (part1 day))))))\n")
        (.write wrtr "\n")
        (.write wrtr "(deftest part2-test\n")
        (.write wrtr "  (testing \"part2 correct answer\"\n")
        (.write wrtr "    (let [answer (:answer2 (day-answers day))]\n")
        (.write wrtr "      (is (= answer (part2 day))))))\n")))))

(defn create-input-files [year day-num]
  (let [dir (io/file (format "%s/%04d/" d/input-repo-dir, year))
        input-file (io/file (format "%s/%04d/%02d_input.txt" d/input-repo-dir, year day-num))
        answer-file (io/file (format "%s/%04d/%02d_answer.json" d/input-repo-dir, year day-num))]
    (when (not (.exists dir))
      (.mkdirs dir))
    (.createNewFile input-file)
    (with-open [wrtr (io/writer answer-file)]
      (.write wrtr "{}\n"))))

(defn create-new-day-files [{:keys [year day-num]}]
  (printf "Creating day %04d.%02d\n" year day-num)
  (create-day-src-file year day-num)
  (create-day-test-file year day-num)
  (create-input-files year day-num))

(defn parse-day-spec [s]
  (if-let [[_ year _ day-num] (re-find #"(\d+)(\.(\d+))?" s)]
    {:year (when year (Integer/parseInt year))
     :day-num (when day-num (Integer/parseInt day-num))}
    (throw (Exception. (format "Invalid year.day argument: %s" s)))))

(defn usage []
  (println "Usage:\n   clj -M -m tools.new-day YYYY.dd [YYYY.dd]...\n")
  (println "Which will create the necessary src and test files")
  (println "for each YYYY.dd day specification."))

(defn -main [& args]
  (if (seq args)
    (let [days (map parse-day-spec args)]
      (doseq [day days]
        (if (nil? (:day-num day))
        ;; generate the whole year
          (doseq [day-num (range 1 26)]
            (create-new-day-files (assoc day :day-num day-num)))
          (create-new-day-files day)))
      (upd/update-days-file))
    (usage)))
