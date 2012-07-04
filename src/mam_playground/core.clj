; Each comment indicates a group of functions that should be split out into
; their own namespace
(ns mam-playground.core
  (:use midje.sweet
        [clojure.string :only [split]]))

; Object constructors
(defn pc [position inventory]
  {:position  position
   :inventory inventory})

(defn npc [name inventory]
  {:name      name
   :inventory inventory
   :speak     (fn [world] (str "Hi I'm " name))})

(defn room [inventory]
  {:inventory inventory})

(defn artifact [name]
  {:name name})

; Container methods
; A set of methods that'll work on anything with an inventory. This should be
; the only way things deal with inventories.
(defn pull [container object]
  (update-in container [:inventory] #(disj % object)))

(defn put [container object]
  (update-in container [:inventory] #(conj % object)))

(defn transfer [giver receiver object]
  [(pull giver object) (put receiver object)])

; World
; Nothing should interact with the world data directly. Everything should go
; through here.
(defn pc-room [{{position :position} :player rooms :rooms}]
  (rooms position))

; Utilities
(defn words [input]
  (split input #"\s"))

; Reader
; Figures out what the input means by mapping it to real objects and actions.
; Note: Feels like object and action are each doing too much
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

(defn do-command [input world]
  ((action input (object input world)) world))

; Aim to implement:
; - Try to move north
;   - "This door needs a key"
; - Speak to person
;   - "I'll trade you a candle for a key"
; - Pick up candle
; - Trade candle for key
; - Speak to person
;   - "Candles make great dildos"
; - Move north
;   - "Your key unlocked the door"

