(ns mam-playground.test.containers
  (:use mam-playground.containers
        midje.sweet))

(fact "Posessions can be moved from one container to another"
  (transfer {:inventory #{1 2}}
            {:inventory #{3 4}}
            2)
  => [{:inventory #{1}}
      {:inventory #{2 3 4}}])

