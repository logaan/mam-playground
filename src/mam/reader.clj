; Note: Feels like object and action are each doing too much
(ns mam.reader
  (:use mam.data.world
        [clojure.string :only [split]]))

(defn words [input]
  (split input #"\s"))

(defn object [input world]
  "Finds an object that the user mentioned by looking in the player's current
  room."
  (let [name    (last (words input))
        objects (:inventory (pc-room world))]
    (first (filter #(= name (:name %)) objects))))

(def actions
  {"speak"    :speak
   "talk"     :speak
   "converse" :speak})

(defn action [input object]
  ((-> input words first actions) object))

