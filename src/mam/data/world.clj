(ns mam.data.world)

(defn world [& {:as data}]
  data)

(defn pc-room [{{position :position} :player rooms :rooms}]
  (rooms position))
