(ns event-processing.core)

(defn event-loop [process-event state [event & pending-events]]
  (if (nil? event) state
    (let [[new-state new-events] (process-event state event)]
      (recur process-event new-state (concat new-events pending-events)))))

