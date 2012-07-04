(ns mam-playground.world)

(defn pc-room [{{position :position} :player rooms :rooms}]
  (rooms position))
