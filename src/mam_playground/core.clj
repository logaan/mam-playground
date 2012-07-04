(ns mam-playground.core
  (:use midje.sweet
        [mam-playground constructors containers world reader]))

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

