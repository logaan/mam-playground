(ns event-processing.test.core
  (:use event-processing.core
        matchure
        midje.sweet))

(defn-match process-math
  ([(and ?total (> ? 10)) _] [total []])
  ([?total :+]  [(inc total) [:+]])
  ([?total :-]  [(dec total) [:+ :+]]))

(fact
  (event-loop process-math 4 [:-]) => 11)

