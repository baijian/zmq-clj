(ns zmq-clj.pub
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(defn -main []
  (let [ctx (make-context 1)]
    (with-open [pubs (doto (make-socket ctx +pub+)
                      (bind "tcp://*:5000")
                      (bind "ipc://channel1.ipc"))]
      (while (not (.. Thread currentThread isInterrupted))
        (let [code 10001;(rand-int 10000)
              a (- (rand-int 500) 10)
              b (+ (rand-int 20) 1)]
          (println (str "pub..." code))
          (Thread/sleep 100)
          (send- pubs (format "%05d %d %d" code a b)))))))
