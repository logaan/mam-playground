;; The event processing system is a way of making it clean and easy to add
;; actions to events. A chain of events will be started by the user. As each
;; events is processed it may add its own events to the queue.
;;
;; Listeners can be quite clever about whether they should handle an events,
;; in mam I intend to use matchure to filter out events. But any kind of
;; function that can handle state and an event can be used to process them.
;;
;; An example:
;;
;; - User types 'n'
;;   - (event :walking :direction :north)
;;     - This event could be handled by a default listener that says 'There is
;;       no door to the north'.
;; - User types 'e'
;;   - (event :walking :direction :east)
;;     - Another listener this time could pick up this event, having looked at
;;       the world and seen that the user is in a room with a door to the east
;;       the listener could fire the next event.
;;   - (event :open-door :wall :east)
;;     - Here a listener could check that the user has the right key, and
;;       depending on the result either give an error message or fire the next
;;       event.
;;     - This is a good example of where having an event system with sensible
;;       defaults works really nicely. The default behavior for a door would be
;;       that it's unlocked and so a user can walk through unimpeded.
;;   - (event :enter-room :position [1 2])
;;     - This event would change the players position to match the room
;;       position and likely print out a description of the room. Here it would
;;       perhaps be nice for multiple listeners to fire, one with the state
;;       change and another with the description.
(ns event-processing.core)

(defn event [type & {:as data}]
  (assoc data :event type))

(defn event-loop [process-event state [event & pending-events]]
  (if (nil? event) state
    (let [[new-state new-events] (process-event state event)]
      (recur process-event new-state (concat new-events pending-events)))))

