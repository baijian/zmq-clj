(ns zmq-clj.pubsub
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(def ^:dynamic *ctx* (make-context 1))

(defn -main []
  (future 
    (let [pub (make-socket *ctx* +pub+)]
      (bind pub "tcp://*:5000")
      (loop [msg 0]
        (send- pub (.getBytes (str "Pub " msg)))
        (Thread/sleep 1000)
        (recur (inc msg)))))
  (doseq [i (range 0 5)]
    (future 
      (let [sub (make-socket *ctx* +sub+)]
        (.subscribe sub (.getBytes ""))
        (connect sub "tcp://127.0.0.1:5000")
        (loop [msg (recv sub)]
          (println (str "Sub" i " recv: " (String. msg)))
          (recur (recv sub)))))))
