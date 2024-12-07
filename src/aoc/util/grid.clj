(ns aoc.util.grid)

(defprotocol GridFns
  (width [this])
  (height [this])
  (cell [this pos])
  (set-cell [this pos value]))

(defrecord Grid [width height values]
  GridFns
  (width [_] width)
  (height [_] height)
  (cell [_ [x y]] (get (get values x) y))
  (set-cell [_ [x y] value] (assoc-in values [y x] value)))
