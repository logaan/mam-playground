(ns mam.test.core
  (:use mam.core
        [mam.data artifact character player room world]
        midje.sweet))

(fact "User input can result in actions with objects"
  (do-command "speak to Peter"
              (world :player (player [1,1] #{})
                     :rooms  {[1,1] (room #{(character "Peter" #{(artifact "key")})
                                            (artifact "candle")})}))
  => "Hi I'm Peter")

