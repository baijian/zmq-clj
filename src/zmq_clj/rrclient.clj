(ns zmq-clj.rrclient
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(defn -main []
  (let [context (make-context 1)]
    (println "Connecting to server...")
    (with-open [socket (doto (make-socket context +req+)
                         (connect "tcp://127.0.0.1:5000"))]
      (dotimes [i 10]
        (let [request "Hello"]
          (println "Sending hello" i "...")
          (send- socket (.getBytes request))
          (let [msg (recv socket)]
            (println "Received " (String. msg) " " i)))))))
