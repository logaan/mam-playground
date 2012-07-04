;; The way this is heading it looks like we'll have something like:
;;
;;    (defn game-loop [state]
;;      (let [events             (read-input (prompt-user))
;;            [new-state output] (process-events event-processor/main state events)]
;;        (println output)
;;        (recur new-state)))
(ns mam-playground.core
  (:use midje.sweet
        [mam-playground constructors containers world reader]))

(defn do-command [input world]
  ((action input (object input world)) world))

