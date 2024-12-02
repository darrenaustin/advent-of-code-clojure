(ns aoc.util.string
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]))

(defn lines [str]
  (str/split str #"\n"))

(defn parse-nums [str]
  (map edn/read-string (re-seq #"\d+" str)))
