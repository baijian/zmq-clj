(ns zmq-clj.rrserver
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(defn -main []
  (let [context (make-context 1)]
    (with-open [socket (doto (make-socket context +rep+)
                         (bind "tcp://*:5000"))]
      (while (not (.. Thread currentThread isInterrupted))
        (let [msg (recv socket)]
          (println "Receive: " (String. msg))
          (Thread/sleep 1000)
          (send- socket (.getBytes "World")))))))
