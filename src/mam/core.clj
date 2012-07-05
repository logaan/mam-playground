;; The way this is heading it looks like we'll have something like:
;; 
;;     (defn game-loop [state]
;;       (let [events             (read-input (prompt-user))
;;             [new-state output] (process-events event-processor/main state events)]
;;         (println output)
;;         (recur new-state)))
(ns mam.core
  (:use [mam containers reader]
        [mam.data artifact character player room world]))

(defn do-command [input world]
  ((action input (object input world)) world))

