(ns mam-playground.test.core
  (:use mam-playground.core
        midje.sweet))

(fact "Posessions can be moved from one container to another"
  (transfer {:inventory #{1 2}}
            {:inventory #{3 4}}
            2)
  => [{:inventory #{1}}
      {:inventory #{2 3 4}}])

(fact "The current room the player is in can be found"
  (pc-room {:player {:position [1,1]} :rooms {[1,1] :red-room}})
  => :red-room)

(fact "User input can be converted to actual objects"
  (object "take the key"
          {:player (pc [1,1] #{})
           :rooms  {[1,1] (room #{(artifact "key")})}})
  => (artifact "key"))


(fact "Peter speaks"
  (action "converse with peter" {:speak "Hi"}) => "Hi")

(fact "User input can result in actions with objects"
  (do-command "speak to Peter"
              {:player (pc [1,1] #{})
               :rooms  {[1,1] (room #{(npc "Peter" #{(artifact "key")})
                                      (artifact "candle")})}})
  => "Hi I'm Peter")

