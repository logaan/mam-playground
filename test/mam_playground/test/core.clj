(ns mam-playground.test.core
  (:use [mam-playground core constructors]
        midje.sweet))

(fact "User input can result in actions with objects"
  (do-command "speak to Peter"
              {:player (pc [1,1] #{})
               :rooms  {[1,1] (room #{(npc "Peter" #{(artifact "key")})
                                      (artifact "candle")})}})
  => "Hi I'm Peter")

