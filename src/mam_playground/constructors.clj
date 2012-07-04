(ns mam-playground.constructors)

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
