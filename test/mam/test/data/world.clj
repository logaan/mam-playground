(ns mam.test.data.world
  (:use mam.data.world
        midje.sweet))

(fact "The current room the player is in can be found"
  (pc-room {:player {:position [1,1]} :rooms {[1,1] :red-room}})
  => :red-room)

