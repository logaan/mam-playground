(ns mam.data.character)

(defn character [name inventory]
  {:name      name
   :inventory inventory
   :speak     (fn [world] (str "Hi I'm " name))})
