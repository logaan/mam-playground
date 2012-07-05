;; The event system is a way of untangling events and actions. This allows us
;; to have multiple user inputs trigger the same event (typing 'n' is the same
;; as typing 'north'). Events may have several pieces of code listening out for
;; them, usefully one of these listeners may be sensible default behavior
;; (moving through door vs moving into wall).
;;
;; Events (maps) are stored in a queue (a vector). The `event-handler` is one
;; function that is passed to `event-loop`. The `event-handler` will be called
;; with a `state` object and a single `event`.
;;
;; An event may be triggered by a user. Further events may be added to the
;; queue by the `event-handler`.
;;
;; I expect the `event-handler` will be a function that is composed of many
;; other functions. You can think of these smaller functions as individual
;; event listeners. They individually decide whether they want to transform the
;; state or add more events to the queue.
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

(defn event-loop [event-handler state [event & event-queue]]
  (if (nil? event) state
    (let [[new-state new-events] (event-handler state event)]
      (recur event-handler new-state (concat new-events event-queue)))))

