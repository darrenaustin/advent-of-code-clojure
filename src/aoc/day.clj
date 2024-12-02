(ns aoc.day
  (:require [clojure.string :as string]
            [clojure.data.json :as json]))

;; TODO: add a way to override this with an env var.
(def input-repo-dir "../advent_of_code_input")

(defn input-file-name [year day]
  (format "%s/%04d/%02d_input.txt" input-repo-dir year day))

(defn day-input [{:keys [year day]}]
  (try
    (string/trimr (slurp (input-file-name year day)))
    (catch Exception _ "")))

(defn answer-file-name [year day]
  (format "%s/%04d/%02d_answer.json" input-repo-dir year day))

(defn day-answers [{:keys [year day]}]
  (try
    (json/read-str (slurp (answer-file-name year day)) :key-fn keyword)
    (catch Exception _ {})))

(defn part1
  ([day] (part1 day (day-input day)))
  ([day input]
   ((:part1 day) input)))

(defn part2
  ([day] (part2 day (day-input day)))
  ([day input]
   ((:part2 day) input)))

;; From https://stackoverflow.com/questions/62724497/how-can-i-record-time-for-function-call-in-clojure
(defmacro time-execution
  [& body]
  `(let [s# (new java.io.StringWriter)]
     (binding [*out* s#]
       (hash-map :return (time ~@body)
                 :time   (int
                          (Double/parseDouble
                           (.replaceAll (str s#) "[^0-9\\.]" "")))))))

(defn result [answer expected time]
  (let [correct (cond
                  (nil? expected) ""
                  (= answer expected) "correct, "
                  :else "INCORRECT, ")]
    (println (format "%s, %s%s ms" answer correct time))))

(defn execute [day]
  (let [{year :year day-num :day name :name :or {name ""} part1 :part1 part2 :part2} day
        input (day-input day)
        answers (day-answers day)]
    (println (format "%d Day %d: %s" year, day-num name))
    (let [p1 (time-execution (part1 input))]
      (print "  part 1: ")
      (result (:return p1) (:answer1 answers) (:time p1)))
    (let [p2 (time-execution (part2 input))]
      (print "  part 2: ")
      (result (:return p2) (:answer2 answers) (:time p2)))
    (println)))
