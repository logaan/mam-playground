(ns mam.containers)

(defn pull [container object]
  (update-in container [:inventory] #(disj % object)))

(defn put [container object]
  (update-in container [:inventory] #(conj % object)))

(defn transfer [giver receiver object]
  [(pull giver object) (put receiver object)])

