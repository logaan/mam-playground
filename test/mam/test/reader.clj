(ns mam.test.reader
  (:use [mam.data player room artifact]
        mam.reader
        midje.sweet))

(fact "User input can be converted to actual objects"
  (object "take the key"
          {:player (player [1,1] #{})
           :rooms  {[1,1] (room #{(artifact "key")})}})
  => (artifact "key"))


(fact "Peter speaks"
  (action "converse with peter" {:speak "Hi"}) => "Hi")

