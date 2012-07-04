;; If you found yourself with a set of functions that apply onto the pcs, npcs,
;; rooms or artifacts then you'd shift the constructor and those functions off
;; into their own ns.
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
